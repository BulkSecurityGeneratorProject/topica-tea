package com.topica.tea.service;

import com.topica.tea.service.dto.FanpageDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Fanpage.
 */
public interface FanpageService {

    /**
     * Save a fanpage.
     *
     * @param fanpageDTO the entity to save
     * @return the persisted entity
     */
    FanpageDTO save(FanpageDTO fanpageDTO);

    /**
     *  Get all the fanpages.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<FanpageDTO> findAll(Pageable pageable);

    /**
     *  Get the "id" fanpage.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    FanpageDTO findOne(Long id);

    /**
     *  Delete the "id" fanpage.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
