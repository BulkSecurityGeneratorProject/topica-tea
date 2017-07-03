package com.topica.tea.service;

import com.topica.tea.service.dto.EventLevelPriorityGroupDTO;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing EventLevelPriorityGroup.
 */
public interface EventLevelPriorityGroupService {

    /**
     * Save a eventLevelPriorityGroup.
     *
     * @param eventLevelPriorityGroupDTO the entity to save
     * @return the persisted entity
     */
    EventLevelPriorityGroupDTO save(EventLevelPriorityGroupDTO eventLevelPriorityGroupDTO);

    /**
     *  Get all the eventLevelPriorityGroups.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<EventLevelPriorityGroupDTO> findAll(Pageable pageable);

    /**
     *  Get the "id" eventLevelPriorityGroup.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    EventLevelPriorityGroupDTO findOne(Long id);

    /**
     *  Delete the "id" eventLevelPriorityGroup.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
    
    /**
     *  Delete the all eventLevelPriorityGroup.
     *
     *  @param id the id of the entity
     */
    void deleteAll();
    
    /**
     * Save all eventLevelPriorityGroup.
     *
     * @param eventLevelPriorityGroupDTO the entity to save
     * @return the persisted entity
     */
    List<EventLevelPriorityGroupDTO> saveAll(List<EventLevelPriorityGroupDTO> eventLevelPriorityGroupDTOs);
}
