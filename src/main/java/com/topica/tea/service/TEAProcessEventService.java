package com.topica.tea.service;

import com.topica.tea.service.dto.EventDTO;

/**
 * Service Interface for managing Event.
 */
public interface TEAProcessEventService {

    /**
     * classify a event.
     *
     * @param eventDTO the entity to classify
     * @return the persisted entity
     */
    EventDTO classify(EventDTO eventDTO);
}
