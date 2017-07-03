package com.topica.tea.service.impl;

import com.topica.tea.service.EventLevelPriorityGroupService;
import com.topica.tea.domain.EventLevelPriorityChannel;
import com.topica.tea.domain.EventLevelPriorityGroup;
import com.topica.tea.repository.EventLevelPriorityGroupRepository;
import com.topica.tea.service.dto.EventLevelPriorityChannelDTO;
import com.topica.tea.service.dto.EventLevelPriorityGroupDTO;
import com.topica.tea.service.mapper.EventLevelPriorityGroupMapper;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing EventLevelPriorityGroup.
 */
@Service
@Transactional
public class EventLevelPriorityGroupServiceImpl implements EventLevelPriorityGroupService{

    private final Logger log = LoggerFactory.getLogger(EventLevelPriorityGroupServiceImpl.class);

    private final EventLevelPriorityGroupRepository eventLevelPriorityGroupRepository;

    private final EventLevelPriorityGroupMapper eventLevelPriorityGroupMapper;

    public EventLevelPriorityGroupServiceImpl(EventLevelPriorityGroupRepository eventLevelPriorityGroupRepository, EventLevelPriorityGroupMapper eventLevelPriorityGroupMapper) {
        this.eventLevelPriorityGroupRepository = eventLevelPriorityGroupRepository;
        this.eventLevelPriorityGroupMapper = eventLevelPriorityGroupMapper;
    }

    /**
     * Save a eventLevelPriorityGroup.
     *
     * @param eventLevelPriorityGroupDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public EventLevelPriorityGroupDTO save(EventLevelPriorityGroupDTO eventLevelPriorityGroupDTO) {
        log.debug("Request to save EventLevelPriorityGroup : {}", eventLevelPriorityGroupDTO);
        EventLevelPriorityGroup eventLevelPriorityGroup = eventLevelPriorityGroupMapper.toEntity(eventLevelPriorityGroupDTO);
        eventLevelPriorityGroup = eventLevelPriorityGroupRepository.save(eventLevelPriorityGroup);
        return eventLevelPriorityGroupMapper.toDto(eventLevelPriorityGroup);
    }

    /**
     *  Get all the eventLevelPriorityGroups.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EventLevelPriorityGroupDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EventLevelPriorityGroups");
        return eventLevelPriorityGroupRepository.findAll(pageable)
            .map(eventLevelPriorityGroupMapper::toDto);
    }

    /**
     *  Get one eventLevelPriorityGroup by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public EventLevelPriorityGroupDTO findOne(Long id) {
        log.debug("Request to get EventLevelPriorityGroup : {}", id);
        EventLevelPriorityGroup eventLevelPriorityGroup = eventLevelPriorityGroupRepository.findOne(id);
        return eventLevelPriorityGroupMapper.toDto(eventLevelPriorityGroup);
    }

    /**
     *  Delete the  eventLevelPriorityGroup by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EventLevelPriorityGroup : {}", id);
        eventLevelPriorityGroupRepository.delete(id);
    }

	@Override
	public void deleteAll() {
		log.debug("Request to deleteAll EventLevelPriorityGroup");
		eventLevelPriorityGroupRepository.deleteAll();
	}

	@Override
	public List<EventLevelPriorityGroupDTO> saveAll(List<EventLevelPriorityGroupDTO> eventLevelPriorityGroupDTOs) {
		log.debug("Request to saveAll list EventLevelPriorityGroup : {}", eventLevelPriorityGroupDTOs);
		
        List<EventLevelPriorityGroup> eventLevelPriorityGroups = eventLevelPriorityGroupMapper.toEntity(eventLevelPriorityGroupDTOs);
        eventLevelPriorityGroups = eventLevelPriorityGroupRepository.save(eventLevelPriorityGroups);
        return eventLevelPriorityGroupMapper.toDto(eventLevelPriorityGroups);
	}
}
