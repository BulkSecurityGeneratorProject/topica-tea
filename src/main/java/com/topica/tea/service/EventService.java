package com.topica.tea.service;

import com.topica.tea.service.dto.EventDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Event.
 */
public interface EventService {

    /**
     * Save a event.
     *
     * @param eventDTO the entity to save
     * @return the persisted entity
     */
    EventDTO save(EventDTO eventDTO);

    /**
     *  Get all the events.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<EventDTO> findAll(Pageable pageable);

    /**
     *  Get the "id" event.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    EventDTO findOne(Long id);

    /**
     *  Delete the "id" event.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
    
    /**
     * distribute a event.
     *
     * @param eventDTO the entity to distribute
     * @return the persisted entity
     */
    EventDTO distribute(Long eventId, Long writeId);
    
    /**
     * Editor a event.
     *
     * @param eventDTO the entity to editor
     * @return the persisted entity
     */
    EventDTO editor(EventDTO eventDTO);
    
    /**
     * updateStatus a event.
     *
     * @param eventDTO the entity to editor
     * @return the persisted entity
     */
    EventDTO updateStatus(Long id, String status);
    
    /**
     * getPublishEventByProductCode a event.
     *
     * @param eventDTO the entity to editor
     * @return the persisted entity
     */
    EventDTO getPublishInjectEventByProductCode(String productCode);
    
    EventDTO getPublishInjectEventByProductId(Long productId);
    
    EventDTO getPublishInjectEventByChannelProductId(Long productId);
}
