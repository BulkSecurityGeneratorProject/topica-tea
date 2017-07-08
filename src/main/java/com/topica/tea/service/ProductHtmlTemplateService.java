package com.topica.tea.service;

import com.topica.tea.service.dto.ProductHtmlTemplateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing ProductHtmlTemplate.
 */
public interface ProductHtmlTemplateService {

    /**
     * Save a productHtmlTemplate.
     *
     * @param productHtmlTemplateDTO the entity to save
     * @return the persisted entity
     */
    ProductHtmlTemplateDTO save(ProductHtmlTemplateDTO productHtmlTemplateDTO);

    /**
     *  Get all the productHtmlTemplates.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<ProductHtmlTemplateDTO> findAll(Pageable pageable);

    /**
     *  Get the "id" productHtmlTemplate.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    ProductHtmlTemplateDTO findOne(Long id);

    /**
     *  Delete the "id" productHtmlTemplate.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
    
    ProductHtmlTemplateDTO findOneByProductIdAndHtmlTemplateId(Long productId, Long htmlTemplateId);
}
