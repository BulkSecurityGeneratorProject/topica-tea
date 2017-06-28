package com.topica.tea.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.topica.tea.service.ChannelGroupService;
import com.topica.tea.web.rest.util.HeaderUtil;
import com.topica.tea.web.rest.util.PaginationUtil;
import com.topica.tea.service.dto.ChannelGroupDTO;
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
 * REST controller for managing ChannelGroup.
 */
@RestController
@RequestMapping("/api")
public class ChannelGroupResource {

    private final Logger log = LoggerFactory.getLogger(ChannelGroupResource.class);

    private static final String ENTITY_NAME = "channelGroup";

    private final ChannelGroupService channelGroupService;

    public ChannelGroupResource(ChannelGroupService channelGroupService) {
        this.channelGroupService = channelGroupService;
    }

    /**
     * POST  /channel-groups : Create a new channelGroup.
     *
     * @param channelGroupDTO the channelGroupDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new channelGroupDTO, or with status 400 (Bad Request) if the channelGroup has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/channel-groups")
    @Timed
    public ResponseEntity<ChannelGroupDTO> createChannelGroup(@Valid @RequestBody ChannelGroupDTO channelGroupDTO) throws URISyntaxException {
        log.debug("REST request to save ChannelGroup : {}", channelGroupDTO);
        if (channelGroupDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new channelGroup cannot already have an ID")).body(null);
        }
        ChannelGroupDTO result = channelGroupService.save(channelGroupDTO);
        return ResponseEntity.created(new URI("/api/channel-groups/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /channel-groups : Updates an existing channelGroup.
     *
     * @param channelGroupDTO the channelGroupDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated channelGroupDTO,
     * or with status 400 (Bad Request) if the channelGroupDTO is not valid,
     * or with status 500 (Internal Server Error) if the channelGroupDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/channel-groups")
    @Timed
    public ResponseEntity<ChannelGroupDTO> updateChannelGroup(@Valid @RequestBody ChannelGroupDTO channelGroupDTO) throws URISyntaxException {
        log.debug("REST request to update ChannelGroup : {}", channelGroupDTO);
        if (channelGroupDTO.getId() == null) {
            return createChannelGroup(channelGroupDTO);
        }
        ChannelGroupDTO result = channelGroupService.save(channelGroupDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, channelGroupDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /channel-groups : get all the channelGroups.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of channelGroups in body
     */
    @GetMapping("/channel-groups")
    @Timed
    public ResponseEntity<List<ChannelGroupDTO>> getAllChannelGroups(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of ChannelGroups");
        Page<ChannelGroupDTO> page = channelGroupService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/channel-groups");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /channel-groups/:id : get the "id" channelGroup.
     *
     * @param id the id of the channelGroupDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the channelGroupDTO, or with status 404 (Not Found)
     */
    @GetMapping("/channel-groups/{id}")
    @Timed
    public ResponseEntity<ChannelGroupDTO> getChannelGroup(@PathVariable Long id) {
        log.debug("REST request to get ChannelGroup : {}", id);
        ChannelGroupDTO channelGroupDTO = channelGroupService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(channelGroupDTO));
    }

    /**
     * DELETE  /channel-groups/:id : delete the "id" channelGroup.
     *
     * @param id the id of the channelGroupDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/channel-groups/{id}")
    @Timed
    public ResponseEntity<Void> deleteChannelGroup(@PathVariable Long id) {
        log.debug("REST request to delete ChannelGroup : {}", id);
        channelGroupService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
