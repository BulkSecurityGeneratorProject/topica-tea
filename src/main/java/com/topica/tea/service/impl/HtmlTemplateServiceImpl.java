package com.topica.tea.service.impl;

import com.topica.tea.service.HtmlTemplateService;
import com.topica.tea.domain.HtmlTemplate;
import com.topica.tea.repository.HtmlTemplateRepository;
import com.topica.tea.service.dto.HtmlTemplateDTO;
import com.topica.tea.service.mapper.HtmlTemplateMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing HtmlTemplate.
 */
@Service
@Transactional
public class HtmlTemplateServiceImpl implements HtmlTemplateService{

    private final Logger log = LoggerFactory.getLogger(HtmlTemplateServiceImpl.class);

    private final HtmlTemplateRepository htmlTemplateRepository;

    private final HtmlTemplateMapper htmlTemplateMapper;

    public HtmlTemplateServiceImpl(HtmlTemplateRepository htmlTemplateRepository, HtmlTemplateMapper htmlTemplateMapper) {
        this.htmlTemplateRepository = htmlTemplateRepository;
        this.htmlTemplateMapper = htmlTemplateMapper;
    }

    /**
     * Save a htmlTemplate.
     *
     * @param htmlTemplateDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public HtmlTemplateDTO save(HtmlTemplateDTO htmlTemplateDTO) {
        log.debug("Request to save HtmlTemplate : {}", htmlTemplateDTO);
        HtmlTemplate htmlTemplate = htmlTemplateMapper.toEntity(htmlTemplateDTO);
        htmlTemplate = htmlTemplateRepository.save(htmlTemplate);
        return htmlTemplateMapper.toDto(htmlTemplate);
    }

    /**
     *  Get all the htmlTemplates.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<HtmlTemplateDTO> findAll(Pageable pageable) {
        log.debug("Request to get all HtmlTemplates");
        return htmlTemplateRepository.findAll(pageable)
            .map(htmlTemplateMapper::toDto);
    }

    /**
     *  Get one htmlTemplate by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public HtmlTemplateDTO findOne(Long id) {
        log.debug("Request to get HtmlTemplate : {}", id);
        HtmlTemplate htmlTemplate = htmlTemplateRepository.findOne(id);
        return htmlTemplateMapper.toDto(htmlTemplate);
    }

    /**
     *  Delete the  htmlTemplate by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete HtmlTemplate : {}", id);
        htmlTemplateRepository.delete(id);
    }
}
