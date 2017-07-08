package com.topica.tea.service;

import com.topica.tea.service.dto.HtmlTemplateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing HtmlTemplate.
 */
public interface HtmlTemplateService {

    /**
     * Save a htmlTemplate.
     *
     * @param htmlTemplateDTO the entity to save
     * @return the persisted entity
     */
    HtmlTemplateDTO save(HtmlTemplateDTO htmlTemplateDTO);

    /**
     *  Get all the htmlTemplates.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<HtmlTemplateDTO> findAll(Pageable pageable);

    /**
     *  Get the "id" htmlTemplate.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    HtmlTemplateDTO findOne(Long id);

    /**
     *  Delete the "id" htmlTemplate.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
