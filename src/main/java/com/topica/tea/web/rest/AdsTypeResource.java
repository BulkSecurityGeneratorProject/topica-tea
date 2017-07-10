package com.topica.tea.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.topica.tea.service.AdsTypeService;
import com.topica.tea.web.rest.util.HeaderUtil;
import com.topica.tea.web.rest.util.PaginationUtil;
import com.topica.tea.service.dto.AdsTypeDTO;
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

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing AdsType.
 */
@RestController
@RequestMapping("/api")
public class AdsTypeResource {

    private final Logger log = LoggerFactory.getLogger(AdsTypeResource.class);

    private static final String ENTITY_NAME = "adsType";

    private final AdsTypeService adsTypeService;

    public AdsTypeResource(AdsTypeService adsTypeService) {
        this.adsTypeService = adsTypeService;
    }

    /**
     * POST  /ads-types : Create a new adsType.
     *
     * @param adsTypeDTO the adsTypeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new adsTypeDTO, or with status 400 (Bad Request) if the adsType has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/ads-types")
    @Timed
    public ResponseEntity<AdsTypeDTO> createAdsType(@Valid @RequestBody AdsTypeDTO adsTypeDTO) throws URISyntaxException {
        log.debug("REST request to save AdsType : {}", adsTypeDTO);
        if (adsTypeDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new adsType cannot already have an ID")).body(null);
        }
        AdsTypeDTO result = adsTypeService.save(adsTypeDTO);
        return ResponseEntity.created(new URI("/api/ads-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /ads-types : Updates an existing adsType.
     *
     * @param adsTypeDTO the adsTypeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated adsTypeDTO,
     * or with status 400 (Bad Request) if the adsTypeDTO is not valid,
     * or with status 500 (Internal Server Error) if the adsTypeDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/ads-types")
    @Timed
    public ResponseEntity<AdsTypeDTO> updateAdsType(@Valid @RequestBody AdsTypeDTO adsTypeDTO) throws URISyntaxException {
        log.debug("REST request to update AdsType : {}", adsTypeDTO);
        if (adsTypeDTO.getId() == null) {
            return createAdsType(adsTypeDTO);
        }
        AdsTypeDTO result = adsTypeService.save(adsTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, adsTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ads-types : get all the adsTypes.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of adsTypes in body
     */
    @GetMapping("/ads-types")
    @Timed
    public ResponseEntity<List<AdsTypeDTO>> getAllAdsTypes(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of AdsTypes");
        Page<AdsTypeDTO> page = adsTypeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/ads-types");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /ads-types/:id : get the "id" adsType.
     *
     * @param id the id of the adsTypeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the adsTypeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/ads-types/{id}")
    @Timed
    public ResponseEntity<AdsTypeDTO> getAdsType(@PathVariable Long id) {
        log.debug("REST request to get AdsType : {}", id);
        AdsTypeDTO adsTypeDTO = adsTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(adsTypeDTO));
    }

    /**
     * DELETE  /ads-types/:id : delete the "id" adsType.
     *
     * @param id the id of the adsTypeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/ads-types/{id}")
    @Timed
    public ResponseEntity<Void> deleteAdsType(@PathVariable Long id) {
        log.debug("REST request to delete AdsType : {}", id);
        adsTypeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
