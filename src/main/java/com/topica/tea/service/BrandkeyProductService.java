package com.topica.tea.service;

import com.topica.tea.service.dto.BrandkeyProductDTO;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing BrandkeyProduct.
 */
public interface BrandkeyProductService {

	/**
     * Save a list brandkeyProducts.
     *
     * @param List<brandkeyProductDTO> the entitys to save
     * @return the persisted entity
     */
	List<BrandkeyProductDTO> saveAll(List<BrandkeyProductDTO> brandkeyProductDTOs);
    
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
    
    /**
     *  Delete all brandkeyProducts.
     *
     */
    void deleteAll();
}
