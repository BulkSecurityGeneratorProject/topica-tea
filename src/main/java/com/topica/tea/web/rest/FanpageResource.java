package com.topica.tea.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.topica.tea.service.FanpageService;
import com.topica.tea.web.rest.util.HeaderUtil;
import com.topica.tea.web.rest.util.PaginationUtil;
import com.topica.tea.service.dto.FanpageDTO;
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
 * REST controller for managing Fanpage.
 */
@RestController
@RequestMapping("/api")
public class FanpageResource {

    private final Logger log = LoggerFactory.getLogger(FanpageResource.class);

    private static final String ENTITY_NAME = "fanpage";

    private final FanpageService fanpageService;

    public FanpageResource(FanpageService fanpageService) {
        this.fanpageService = fanpageService;
    }

    /**
     * POST  /fanpages : Create a new fanpage.
     *
     * @param fanpageDTO the fanpageDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new fanpageDTO, or with status 400 (Bad Request) if the fanpage has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/fanpages")
    @Timed
    public ResponseEntity<FanpageDTO> createFanpage(@RequestBody FanpageDTO fanpageDTO) throws URISyntaxException {
        log.debug("REST request to save Fanpage : {}", fanpageDTO);
        if (fanpageDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new fanpage cannot already have an ID")).body(null);
        }
        FanpageDTO result = fanpageService.save(fanpageDTO);
        return ResponseEntity.created(new URI("/api/fanpages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /fanpages : Updates an existing fanpage.
     *
     * @param fanpageDTO the fanpageDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated fanpageDTO,
     * or with status 400 (Bad Request) if the fanpageDTO is not valid,
     * or with status 500 (Internal Server Error) if the fanpageDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/fanpages")
    @Timed
    public ResponseEntity<FanpageDTO> updateFanpage(@RequestBody FanpageDTO fanpageDTO) throws URISyntaxException {
        log.debug("REST request to update Fanpage : {}", fanpageDTO);
        if (fanpageDTO.getId() == null) {
            return createFanpage(fanpageDTO);
        }
        FanpageDTO result = fanpageService.save(fanpageDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, fanpageDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /fanpages : get all the fanpages.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of fanpages in body
     */
    @GetMapping("/fanpages")
    @Timed
    public ResponseEntity<List<FanpageDTO>> getAllFanpages(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of Fanpages");
        Page<FanpageDTO> page = fanpageService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/fanpages");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /fanpages/:id : get the "id" fanpage.
     *
     * @param id the id of the fanpageDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the fanpageDTO, or with status 404 (Not Found)
     */
    @GetMapping("/fanpages/{id}")
    @Timed
    public ResponseEntity<FanpageDTO> getFanpage(@PathVariable Long id) {
        log.debug("REST request to get Fanpage : {}", id);
        FanpageDTO fanpageDTO = fanpageService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(fanpageDTO));
    }

    /**
     * DELETE  /fanpages/:id : delete the "id" fanpage.
     *
     * @param id the id of the fanpageDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/fanpages/{id}")
    @Timed
    public ResponseEntity<Void> deleteFanpage(@PathVariable Long id) {
        log.debug("REST request to delete Fanpage : {}", id);
        fanpageService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
