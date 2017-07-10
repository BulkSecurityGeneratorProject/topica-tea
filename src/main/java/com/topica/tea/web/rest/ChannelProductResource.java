package com.topica.tea.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.topica.tea.service.ChannelProductService;
import com.topica.tea.web.rest.util.HeaderUtil;
import com.topica.tea.web.rest.util.PaginationUtil;
import com.topica.tea.service.dto.ChannelProductDTO;
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
 * REST controller for managing ChannelProduct.
 */
@RestController
@RequestMapping("/api")
public class ChannelProductResource {

    private final Logger log = LoggerFactory.getLogger(ChannelProductResource.class);

    private static final String ENTITY_NAME = "channelProduct";

    private final ChannelProductService channelProductService;

    public ChannelProductResource(ChannelProductService channelProductService) {
        this.channelProductService = channelProductService;
    }

    /**
     * POST  /channel-products : Create a new channelProduct.
     *
     * @param channelProductDTO the channelProductDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new channelProductDTO, or with status 400 (Bad Request) if the channelProduct has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/channel-products")
    @Timed
    public ResponseEntity<ChannelProductDTO> createChannelProduct(@Valid @RequestBody ChannelProductDTO channelProductDTO) throws URISyntaxException {
        log.debug("REST request to save ChannelProduct : {}", channelProductDTO);
        if (channelProductDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new channelProduct cannot already have an ID")).body(null);
        }
        ChannelProductDTO result = channelProductService.save(channelProductDTO);
        return ResponseEntity.created(new URI("/api/channel-products/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /channel-products : Updates an existing channelProduct.
     *
     * @param channelProductDTO the channelProductDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated channelProductDTO,
     * or with status 400 (Bad Request) if the channelProductDTO is not valid,
     * or with status 500 (Internal Server Error) if the channelProductDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/channel-products")
    @Timed
    public ResponseEntity<ChannelProductDTO> updateChannelProduct(@Valid @RequestBody ChannelProductDTO channelProductDTO) throws URISyntaxException {
        log.debug("REST request to update ChannelProduct : {}", channelProductDTO);
        if (channelProductDTO.getId() == null) {
            return createChannelProduct(channelProductDTO);
        }
        ChannelProductDTO result = channelProductService.save(channelProductDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, channelProductDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /channel-products : get all the channelProducts.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of channelProducts in body
     */
    @GetMapping("/channel-products")
    @Timed
    public ResponseEntity<List<ChannelProductDTO>> getAllChannelProducts(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of ChannelProducts");
        Page<ChannelProductDTO> page = channelProductService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/channel-products");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /channel-products/:id : get the "id" channelProduct.
     *
     * @param id the id of the channelProductDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the channelProductDTO, or with status 404 (Not Found)
     */
    @GetMapping("/channel-products/{id}")
    @Timed
    public ResponseEntity<ChannelProductDTO> getChannelProduct(@PathVariable Long id) {
        log.debug("REST request to get ChannelProduct : {}", id);
        ChannelProductDTO channelProductDTO = channelProductService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(channelProductDTO));
    }

    /**
     * DELETE  /channel-products/:id : delete the "id" channelProduct.
     *
     * @param id the id of the channelProductDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/channel-products/{id}")
    @Timed
    public ResponseEntity<Void> deleteChannelProduct(@PathVariable Long id) {
        log.debug("REST request to delete ChannelProduct : {}", id);
        channelProductService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
