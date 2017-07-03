package com.topica.tea.service;

import com.topica.tea.service.dto.EventLevelPriorityChannelDTO;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing EventLevelPriorityChannel.
 */
public interface EventLevelPriorityChannelService {

    /**
     * Save a eventLevelPriorityChannel.
     *
     * @param eventLevelPriorityChannelDTO the entity to save
     * @return the persisted entity
     */
    EventLevelPriorityChannelDTO save(EventLevelPriorityChannelDTO eventLevelPriorityChannelDTO);

    /**
     *  Get all the eventLevelPriorityChannels.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<EventLevelPriorityChannelDTO> findAll(Pageable pageable);

    /**
     *  Get the "id" eventLevelPriorityChannel.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    EventLevelPriorityChannelDTO findOne(Long id);

    /**
     *  Delete the "id" eventLevelPriorityChannel.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
    
    /**
     *  Delete the "id" eventLevelPriorityChannel.
     *
     *  @param id the id of the entity
     */
    void deleteAll();
    
    /**
     * Save a eventLevelPriorityChannel.
     *
     * @param eventLevelPriorityChannelDTO the entity to save
     * @return the persisted entity
     */
    List<EventLevelPriorityChannelDTO> saveAll(List<EventLevelPriorityChannelDTO> eventLevelPriorityChannelDTOs);
}
