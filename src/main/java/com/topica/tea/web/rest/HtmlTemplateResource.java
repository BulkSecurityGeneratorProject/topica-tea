package com.topica.tea.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.topica.tea.service.HtmlTemplateService;
import com.topica.tea.web.rest.util.HeaderUtil;
import com.topica.tea.web.rest.util.PaginationUtil;
import com.topica.tea.service.dto.HtmlTemplateDTO;
import io.swagger.annotations.ApiParam;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing HtmlTemplate.
 */
@RestController
@RequestMapping("/api")
public class HtmlTemplateResource {

    private final Logger log = LoggerFactory.getLogger(HtmlTemplateResource.class);

    private static final String ENTITY_NAME = "htmlTemplate";

    private final HtmlTemplateService htmlTemplateService;

    public HtmlTemplateResource(HtmlTemplateService htmlTemplateService) {
        this.htmlTemplateService = htmlTemplateService;
    }

    /**
     * POST  /html-templates : Create a new htmlTemplate.
     *
     * @param htmlTemplateDTO the htmlTemplateDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new htmlTemplateDTO, or with status 400 (Bad Request) if the htmlTemplate has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/html-templates")
    @Timed
    public ResponseEntity<HtmlTemplateDTO> createHtmlTemplate(@RequestBody HtmlTemplateDTO htmlTemplateDTO) throws URISyntaxException {
        log.debug("REST request to save HtmlTemplate : {}", htmlTemplateDTO);
        if (htmlTemplateDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new htmlTemplate cannot already have an ID")).body(null);
        }
        HtmlTemplateDTO result = htmlTemplateService.save(htmlTemplateDTO);
        return ResponseEntity.created(new URI("/api/html-templates/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /html-templates : Updates an existing htmlTemplate.
     *
     * @param htmlTemplateDTO the htmlTemplateDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated htmlTemplateDTO,
     * or with status 400 (Bad Request) if the htmlTemplateDTO is not valid,
     * or with status 500 (Internal Server Error) if the htmlTemplateDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/html-templates")
    @Timed
    public ResponseEntity<HtmlTemplateDTO> updateHtmlTemplate(@RequestBody HtmlTemplateDTO htmlTemplateDTO) throws URISyntaxException {
        log.debug("REST request to update HtmlTemplate : {}", htmlTemplateDTO);
        if (htmlTemplateDTO.getId() == null) {
            return createHtmlTemplate(htmlTemplateDTO);
        }
        HtmlTemplateDTO result = htmlTemplateService.save(htmlTemplateDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, htmlTemplateDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /html-templates : get all the htmlTemplates.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of htmlTemplates in body
     */
    @GetMapping("/html-templates")
    @Timed
    public ResponseEntity<List<HtmlTemplateDTO>> getAllHtmlTemplates(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of HtmlTemplates");
        Page<HtmlTemplateDTO> page = htmlTemplateService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/html-templates");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /html-templates/:id : get the "id" htmlTemplate.
     *
     * @param id the id of the htmlTemplateDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the htmlTemplateDTO, or with status 404 (Not Found)
     */
    @GetMapping("/html-templates/{id}")
    @Timed
    public ResponseEntity<HtmlTemplateDTO> getHtmlTemplate(@PathVariable Long id) {
        log.debug("REST request to get HtmlTemplate : {}", id);
        HtmlTemplateDTO htmlTemplateDTO = htmlTemplateService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(htmlTemplateDTO));
    }

    /**
     * DELETE  /html-templates/:id : delete the "id" htmlTemplate.
     *
     * @param id the id of the htmlTemplateDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/html-templates/{id}")
    @Timed
    public ResponseEntity<Void> deleteHtmlTemplate(@PathVariable Long id) {
        log.debug("REST request to delete HtmlTemplate : {}", id);
        htmlTemplateService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
