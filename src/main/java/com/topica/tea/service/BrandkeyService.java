package com.topica.tea.service;

import com.topica.tea.service.dto.BrandkeyDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Brandkey.
 */
public interface BrandkeyService {

    /**
     * Save a brandkey.
     *
     * @param brandkeyDTO the entity to save
     * @return the persisted entity
     */
    BrandkeyDTO save(BrandkeyDTO brandkeyDTO);

    /**
     *  Get all the brandkeys.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<BrandkeyDTO> findAll(Pageable pageable);

    /**
     *  Get the "id" brandkey.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    BrandkeyDTO findOne(Long id);

    /**
     *  Delete the "id" brandkey.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
