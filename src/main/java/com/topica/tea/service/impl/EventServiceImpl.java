package com.topica.tea.service.impl;

import com.topica.tea.service.EventService;
import com.topica.tea.domain.Article;
import com.topica.tea.domain.Event;
import com.topica.tea.domain.Product;
import com.topica.tea.domain.Question;
import com.topica.tea.domain.User;
import com.topica.tea.domain.enumeration.EventStatus;
import com.topica.tea.repository.ArticleRepository;
import com.topica.tea.repository.EventRepository;
import com.topica.tea.repository.ProductRepository;
import com.topica.tea.repository.UserRepository;
import com.topica.tea.service.dto.EventDTO;
import com.topica.tea.service.mapper.ArticleMapper;
import com.topica.tea.service.mapper.EventMapper;
import com.topica.tea.service.mapper.QuestionMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Event.
 */
@Service
@Transactional
public class EventServiceImpl implements EventService{

    private final Logger log = LoggerFactory.getLogger(EventServiceImpl.class);

    private final EventRepository eventRepository;
    
    private final ProductRepository productRepository;

    private final UserRepository userRepository;
    
    private final ArticleRepository articleRepository;
    
    private final EventMapper eventMapper;
    
    private final QuestionMapper questionMapper;
    
    private final ArticleMapper articleMapper;

    public EventServiceImpl(EventRepository eventRepository, EventMapper eventMapper, QuestionMapper questionMapper
    		, UserRepository userRepository, ArticleRepository articleRepository,
    		ArticleMapper articleMapper,
    		ProductRepository productRepository) {
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
        this.articleMapper = articleMapper;
        this.questionMapper = questionMapper;
        this.userRepository = userRepository;
        this.articleRepository = articleRepository;
        this.productRepository = productRepository;
    }

    /**
     * Save a event.
     *
     * @param eventDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public EventDTO save(EventDTO eventDTO) {
        log.debug("Request to save Event : {}", eventDTO);
        Question question = questionMapper.toEntity(eventDTO.getQuestion());
        Article article = articleMapper.toEntity(eventDTO.getArticle());
        Event event = eventMapper.toEntity(eventDTO);
        
        // Set extra info
        event.setQuestion(question);
        event.setArticle(article);
        
        // Set user
        if (eventDTO.getCreatedUserId() != null && eventDTO.getCreatedUserId() > 0) {
        	User createdUser = userRepository.findOne(eventDTO.getCreatedUserId());
        	event.setCreatedUser(createdUser);
        }
        if (eventDTO.getApprovalUserId() != null && eventDTO.getApprovalUserId() > 0) {
        	User approvalUser = userRepository.findOne(eventDTO.getApprovalUserId());
        	event.setApprovalUser(approvalUser);
        }
        if (eventDTO.getManagerUserId() != null && eventDTO.getManagerUserId() > 0) {
        	User managerUser = userRepository.findOne(eventDTO.getManagerUserId());
        	event.setManagerUser(managerUser);
        }
        
        event = eventRepository.save(event);
        return eventMapper.toDto(event);
    }

    /**
     *  Get all the events.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EventDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Events");
        return eventRepository.findAll(pageable)
            .map(eventMapper::toDto);
    }

    /**
     *  Get one event by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public EventDTO findOne(Long id) {
        log.debug("Request to get Event : {}", id);
        Event event = eventRepository.findOne(id);
        return eventMapper.toDto(event);
    }

    /**
     *  Delete the  event by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Event : {}", id);
        eventRepository.delete(id);
    }

	@Override
	public EventDTO distribute(Long eventId, Long writerId) {
		Event event = eventRepository.findOne(eventId);
		User user = userRepository.findOne(writerId);
		
		// Update event
		if (event != null && user != null) {
			event.setEventStatus(EventStatus.EDITOR);
			event.setWriterUser(user);
			Event result = eventRepository.save(event);
			return eventMapper.toDto(result);
		}
		return null;
	}

	@Override
	public EventDTO editor(EventDTO eventDTO) {
		Event event = eventRepository.findOne(eventDTO.getId());
		
		if (event != null) {
			Article article = new Article();
			article.setTitle("Article for event: " + eventDTO.getId());
			article.setType(0);
			article.setContent1(eventDTO.getContent());
			Article articleResult = articleRepository.save(article);
			
			event.setArticle(articleResult);
			event.setEventStatus(EventStatus.WAIT_DIRECTOR_APPROVE);
			Event eventResult =  eventRepository.save(event);
			return eventMapper.toDto(eventResult);
		}
		return null;
	}

	@Override
	public EventDTO updateStatus(Long id, String status) {
		Event event = eventRepository.findOne(id);
		
		if (event != null) {
			EventStatus statusUpdate = EventStatus.valueOf(status);
			
			event.setEventStatus(statusUpdate);
			Event eventResult =  eventRepository.save(event);
			return eventMapper.toDto(eventResult);
		}
		return null;
	}

	@Override
	public EventDTO getPublishInjectEventByProductCode(String productCode) {
		Event event = eventRepository.findPublishInjectOneByProductCode(productCode);
		
		if (null == event) {
			return null;
		}
		return eventMapper.toDto(event);
	}
	
	@Override
	public EventDTO getPublishInjectEventByProductId(Long productId) {
		Event event = eventRepository.findPublishInjectOneByProductId(productId);
		
		if (null == event) {
			return null;
		}
		return eventMapper.toDto(event);
	}

	@Override
	public EventDTO getPublishInjectEventByChannelProductId(Long productId) {
		Event event = eventRepository.findPublishInjectOneByChannelProductId(productId);
		
		if (null == event) {
			return null;
		}
		return eventMapper.toDto(event);
	}
}
