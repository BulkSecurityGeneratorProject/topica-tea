package com.topica.tea.service;

import com.topica.tea.service.dto.AdsTypeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing AdsType.
 */
public interface AdsTypeService {

    /**
     * Save a adsType.
     *
     * @param adsTypeDTO the entity to save
     * @return the persisted entity
     */
    AdsTypeDTO save(AdsTypeDTO adsTypeDTO);

    /**
     *  Get all the adsTypes.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<AdsTypeDTO> findAll(Pageable pageable);

    /**
     *  Get the "id" adsType.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    AdsTypeDTO findOne(Long id);

    /**
     *  Delete the "id" adsType.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
