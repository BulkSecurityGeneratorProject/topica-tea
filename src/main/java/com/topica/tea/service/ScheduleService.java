package com.topica.tea.service;

import com.topica.tea.service.dto.ScheduleDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Schedule.
 */
public interface ScheduleService {

    /**
     * Save a schedule.
     *
     * @param scheduleDTO the entity to save
     * @return the persisted entity
     */
    ScheduleDTO save(ScheduleDTO scheduleDTO);

    /**
     *  Get all the schedules.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<ScheduleDTO> findAll(Pageable pageable);

    /**
     *  Get the "id" schedule.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    ScheduleDTO findOne(Long id);

    /**
     *  Delete the "id" schedule.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
