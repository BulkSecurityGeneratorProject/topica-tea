package com.topica.tea.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.topica.tea.domain.BrandkeyProduct;
import com.topica.tea.domain.ChannelGroup;
import com.topica.tea.domain.EventLevelPriorityGroup;
import com.topica.tea.domain.Product;
import com.topica.tea.domain.enumeration.EventLevel;
import com.topica.tea.domain.enumeration.EventStatus;
import com.topica.tea.domain.enumeration.PriorityGroup;
import com.topica.tea.repository.BrandkeyProductRepository;
import com.topica.tea.repository.ChannelGroupRepository;
import com.topica.tea.repository.EventLevelPriorityGroupRepository;
import com.topica.tea.repository.EventRepository;
import com.topica.tea.repository.ProductRepository;
import com.topica.tea.repository.UserRepository;
import com.topica.tea.service.TEAProcessEventService;
import com.topica.tea.service.dto.BrandkeyDTO;
import com.topica.tea.service.dto.EventDTO;
import com.topica.tea.service.dto.ProductDTO;
import com.topica.tea.service.dto.QuestionDTO;
import com.topica.tea.service.dto.ScheduleDTO;
import com.topica.tea.service.mapper.EventMapper;
import com.topica.tea.service.mapper.ProductMapper;
import com.topica.tea.service.mapper.QuestionMapper;



/**
 * Service Implementation for managing Event.
 */
@Service
@Transactional
public class TEAProcessEventServiceImpl implements TEAProcessEventService {

    private final Logger log = LoggerFactory.getLogger(TEAProcessEventServiceImpl.class);

    private final ChannelGroupRepository channelGroupRepository;
    
    private final EventRepository eventRepository;

    private final UserRepository userRepository;
    
    private final EventMapper eventMapper;
    
    private final QuestionMapper questionMapper;
    
    private final ProductMapper productMapper;
    
    private final EventLevelPriorityGroupRepository eventLevelPriorityGroupRepository;
    
    private final BrandkeyProductRepository brandkeyProductRepository;
    
    private final ProductRepository productRepository;

    public TEAProcessEventServiceImpl(EventRepository eventRepository, EventMapper eventMapper
    		, QuestionMapper questionMapper, UserRepository userRepository
    		, EventLevelPriorityGroupRepository eventLevelPriorityGroupRepository
    		, BrandkeyProductRepository brandkeyProductRepository
    		, ProductRepository productRepository
    		, ProductMapper productMapper
    		, ChannelGroupRepository channelGroupRepository) {
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
        this.questionMapper = questionMapper;
        this.userRepository = userRepository;
        this.channelGroupRepository = channelGroupRepository;
        this.eventLevelPriorityGroupRepository = eventLevelPriorityGroupRepository;
        this.brandkeyProductRepository = brandkeyProductRepository;
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

	@Override
	public EventDTO classify(EventDTO eventDTO) {
		log.debug("Request to classify Event : {}", eventDTO);
		// Extra data
		QuestionDTO question = eventDTO.getQuestion();
		eventDTO.setAmplifyType(question.getAmplifyType());
		
		// Rule #1: đánh ưu tiên nhóm kênh (E1, E2, E3, E4)
		EventLevel eventLevel = calculateEventLevel(eventDTO);
		eventDTO.setEventLevel(eventLevel);
		
		// Rule #2: Xác định sản phẩm (Product)
		Set<ProductDTO> productDTOs = calculateProduct(eventDTO);
		eventDTO.setProducts(productDTOs);
		
		// Rule #3: Xác định ưu tiên nhóm kênh (K0a, K0b, K1, K2, K3, K4)
		Set<PriorityGroup> priorityGroups = calculatePriorityChannelGroup(eventDTO);
		eventDTO.setPriorityGroup(priorityGroups);
		
		// Rule #4: Xét ưu tiên khi các sự kiện cùng diễn ra (xét theo Ex)
		
		// Rule #5: Hình thức khuếch đại (Share, Sponsor, Inject) ???
		
		// Rule #6: Lịch
		List<ScheduleDTO> schedules = calculateSchedule(eventDTO);
		
		// Set status
		if (EventLevel.E4.equals(eventDTO.getEventLevel())) {
			eventDTO.setEventStatus(EventStatus.INVALID);
		} else {
			eventDTO.setEventStatus(EventStatus.COORDINATE);
		}
		
		return eventDTO;
	}
	
	/**
	 * Xác định E1, E2, E3, E4
	 * Rule#1
	 * 
	 * @param eventDTO
	 * @return
	 */
	private EventLevel calculateEventLevel(EventDTO eventDTO) {
		log.debug("Request to calculateEventLevel");
		QuestionDTO question = eventDTO.getQuestion();
		
		// Vai trò
		Long roleId = question.getRoleId();
		ChannelGroup roleChannel = channelGroupRepository.getOne(roleId);
		
		// Khách mời
		Long inviteeId = question.getInviteeId();
		ChannelGroup inviteeChannel = channelGroupRepository.getOne(inviteeId);
		
		// Quy mô
		Long scaleId = question.getScaleId();
		ChannelGroup scaleChannel = channelGroupRepository.getOne(scaleId);
		
		// Calculate point
		Long sumPoint = roleChannel.getPoint() + inviteeChannel.getPoint() + scaleChannel.getPoint();
		log.debug("Calculate point: {}", sumPoint);
		//				>=100 	E1
		//				100>x>60	E2
		//				60=>x>30	E3
		//				x=<30	E4: Ko khuếch đại
		if (sumPoint >= 100) {
			return EventLevel.E1;
		} else if (sumPoint > 60 && sumPoint < 100) {
			return EventLevel.E2;
		} else if (sumPoint > 30 && sumPoint <= 60) {
			return EventLevel.E3;
		} else {
			return EventLevel.E4;
		}
	}
	
	/**
	 * Xác định E1, E2, E3, E4
	 * Rule#2
	 * 
	 * @param eventDTO
	 * @return
	 */
	private Set<ProductDTO> calculateProduct(EventDTO eventDTO) {
		Set<Product> lstProduct = new HashSet<>();
		Set<BrandkeyDTO> brandkeys = eventDTO.getQuestion().getBrandkeys();
		
		// check null
		if (brandkeys == null || brandkeys.size() == 0) {
			return null;
		}
		
		for (BrandkeyDTO brandkeyDTO : brandkeys) {
			List<BrandkeyProduct> tmpBrandkeyProduct = brandkeyProductRepository.findAllByBrandkeyId(brandkeyDTO.getId());
			if (tmpBrandkeyProduct != null && tmpBrandkeyProduct.size() > 0) {
				for (BrandkeyProduct brandkeyProduct : tmpBrandkeyProduct) {
					Product tmpProduct = productRepository.getOne(brandkeyProduct.getProduct_id());
					if (null != tmpProduct) {
						lstProduct.add(tmpProduct);
					}
				}
			}
		}
		
		if (null == lstProduct || lstProduct.size() == 0) {
			return null;
		}
		
		// Convert to DTO
		Set<ProductDTO> lstProductDTO = new HashSet<>();
		for (Product item : lstProduct) {
			ProductDTO tmp = productMapper.toDto(item);
			lstProductDTO.add(tmp);
		}
		
		return lstProductDTO;
	}
	
	/**
	 * Xác định (K0a, K0b, K1, K2, K3, K4)
	 * Rule#3
	 * 
	 * @param eventDTO
	 * @return
	 */
	private Set<PriorityGroup> calculatePriorityChannelGroup(EventDTO eventDTO) {
		Set<PriorityGroup> lstChannel = new HashSet<>();
		boolean isMeatContent = Boolean.TRUE.equals(eventDTO.getQuestion().isIsMeatContent());
		EventLevel eventLevel = eventDTO.getEventLevel();
		
		List<EventLevelPriorityGroup> eventLevelPriorityGroups = eventLevelPriorityGroupRepository.findAllByIsMeatContentAndEventLevel(isMeatContent, eventLevel);
		
		if (null != eventLevelPriorityGroups && eventLevelPriorityGroups.size() > 0) {
			for (EventLevelPriorityGroup eventLevelPriorityGroup : eventLevelPriorityGroups) {
				lstChannel.add(eventLevelPriorityGroup.getPriorityGroup());
			}
		}
		
		return lstChannel;
	}
	
	/**
	 * Xác định schedule
	 * Rule#6
	 * 
	 * @param eventDTO
	 * @return
	 */
	private List<ScheduleDTO> calculateSchedule(EventDTO eventDTO) {
		return null;
	}
}
