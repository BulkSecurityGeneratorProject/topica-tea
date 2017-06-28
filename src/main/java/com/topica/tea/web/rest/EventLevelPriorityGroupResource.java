package com.topica.tea.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.topica.tea.service.EventLevelPriorityGroupService;
import com.topica.tea.web.rest.util.HeaderUtil;
import com.topica.tea.web.rest.util.PaginationUtil;
import com.topica.tea.service.dto.EventLevelPriorityGroupDTO;
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
 * REST controller for managing EventLevelPriorityGroup.
 */
@RestController
@RequestMapping("/api")
public class EventLevelPriorityGroupResource {

    private final Logger log = LoggerFactory.getLogger(EventLevelPriorityGroupResource.class);

    private static final String ENTITY_NAME = "eventLevelPriorityGroup";

    private final EventLevelPriorityGroupService eventLevelPriorityGroupService;

    public EventLevelPriorityGroupResource(EventLevelPriorityGroupService eventLevelPriorityGroupService) {
        this.eventLevelPriorityGroupService = eventLevelPriorityGroupService;
    }

    /**
     * POST  /event-level-priority-groups : Create a new eventLevelPriorityGroup.
     *
     * @param eventLevelPriorityGroupDTO the eventLevelPriorityGroupDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new eventLevelPriorityGroupDTO, or with status 400 (Bad Request) if the eventLevelPriorityGroup has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/event-level-priority-groups")
    @Timed
    public ResponseEntity<EventLevelPriorityGroupDTO> createEventLevelPriorityGroup(@Valid @RequestBody EventLevelPriorityGroupDTO eventLevelPriorityGroupDTO) throws URISyntaxException {
        log.debug("REST request to save EventLevelPriorityGroup : {}", eventLevelPriorityGroupDTO);
        if (eventLevelPriorityGroupDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new eventLevelPriorityGroup cannot already have an ID")).body(null);
        }
        EventLevelPriorityGroupDTO result = eventLevelPriorityGroupService.save(eventLevelPriorityGroupDTO);
        return ResponseEntity.created(new URI("/api/event-level-priority-groups/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /event-level-priority-groups : Updates an existing eventLevelPriorityGroup.
     *
     * @param eventLevelPriorityGroupDTO the eventLevelPriorityGroupDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated eventLevelPriorityGroupDTO,
     * or with status 400 (Bad Request) if the eventLevelPriorityGroupDTO is not valid,
     * or with status 500 (Internal Server Error) if the eventLevelPriorityGroupDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/event-level-priority-groups")
    @Timed
    public ResponseEntity<EventLevelPriorityGroupDTO> updateEventLevelPriorityGroup(@Valid @RequestBody EventLevelPriorityGroupDTO eventLevelPriorityGroupDTO) throws URISyntaxException {
        log.debug("REST request to update EventLevelPriorityGroup : {}", eventLevelPriorityGroupDTO);
        if (eventLevelPriorityGroupDTO.getId() == null) {
            return createEventLevelPriorityGroup(eventLevelPriorityGroupDTO);
        }
        EventLevelPriorityGroupDTO result = eventLevelPriorityGroupService.save(eventLevelPriorityGroupDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, eventLevelPriorityGroupDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /event-level-priority-groups : get all the eventLevelPriorityGroups.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of eventLevelPriorityGroups in body
     */
    @GetMapping("/event-level-priority-groups")
    @Timed
    public ResponseEntity<List<EventLevelPriorityGroupDTO>> getAllEventLevelPriorityGroups(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of EventLevelPriorityGroups");
        Page<EventLevelPriorityGroupDTO> page = eventLevelPriorityGroupService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/event-level-priority-groups");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /event-level-priority-groups/:id : get the "id" eventLevelPriorityGroup.
     *
     * @param id the id of the eventLevelPriorityGroupDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the eventLevelPriorityGroupDTO, or with status 404 (Not Found)
     */
    @GetMapping("/event-level-priority-groups/{id}")
    @Timed
    public ResponseEntity<EventLevelPriorityGroupDTO> getEventLevelPriorityGroup(@PathVariable Long id) {
        log.debug("REST request to get EventLevelPriorityGroup : {}", id);
        EventLevelPriorityGroupDTO eventLevelPriorityGroupDTO = eventLevelPriorityGroupService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(eventLevelPriorityGroupDTO));
    }

    /**
     * DELETE  /event-level-priority-groups/:id : delete the "id" eventLevelPriorityGroup.
     *
     * @param id the id of the eventLevelPriorityGroupDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/event-level-priority-groups/{id}")
    @Timed
    public ResponseEntity<Void> deleteEventLevelPriorityGroup(@PathVariable Long id) {
        log.debug("REST request to delete EventLevelPriorityGroup : {}", id);
        eventLevelPriorityGroupService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
