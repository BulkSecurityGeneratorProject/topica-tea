package com.topica.tea.service;

import com.topica.tea.service.dto.BrandkeyProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing BrandkeyProduct.
 */
public interface BrandkeyProductService {

    /**
     * Save a brandkeyProduct.
     *
     * @param brandkeyProductDTO the entity to save
     * @return the persisted entity
     */
    BrandkeyProductDTO save(BrandkeyProductDTO brandkeyProductDTO);

    /**
     *  Get all the brandkeyProducts.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<BrandkeyProductDTO> findAll(Pageable pageable);

    /**
     *  Get the "id" brandkeyProduct.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    BrandkeyProductDTO findOne(Long id);

    /**
     *  Delete the "id" brandkeyProduct.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
