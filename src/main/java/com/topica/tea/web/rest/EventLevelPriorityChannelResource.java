package com.topica.tea.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.topica.tea.service.EventLevelPriorityChannelService;
import com.topica.tea.web.rest.util.HeaderUtil;
import com.topica.tea.web.rest.util.PaginationUtil;
import com.topica.tea.service.dto.EventLevelPriorityChannelDTO;
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
 * REST controller for managing EventLevelPriorityChannel.
 */
@RestController
@RequestMapping("/api")
public class EventLevelPriorityChannelResource {

    private final Logger log = LoggerFactory.getLogger(EventLevelPriorityChannelResource.class);

    private static final String ENTITY_NAME = "eventLevelPriorityChannel";

    private final EventLevelPriorityChannelService eventLevelPriorityChannelService;

    public EventLevelPriorityChannelResource(EventLevelPriorityChannelService eventLevelPriorityChannelService) {
        this.eventLevelPriorityChannelService = eventLevelPriorityChannelService;
    }

    /**
     * POST  /event-level-priority-channels : Create a new eventLevelPriorityChannel.
     *
     * @param eventLevelPriorityChannelDTO the eventLevelPriorityChannelDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new eventLevelPriorityChannelDTO, or with status 400 (Bad Request) if the eventLevelPriorityChannel has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/event-level-priority-channels")
    @Timed
    public ResponseEntity<EventLevelPriorityChannelDTO> createEventLevelPriorityChannel(@RequestBody EventLevelPriorityChannelDTO eventLevelPriorityChannelDTO) throws URISyntaxException {
        log.debug("REST request to save EventLevelPriorityChannel : {}", eventLevelPriorityChannelDTO);
        if (eventLevelPriorityChannelDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new eventLevelPriorityChannel cannot already have an ID")).body(null);
        }
        EventLevelPriorityChannelDTO result = eventLevelPriorityChannelService.save(eventLevelPriorityChannelDTO);
        return ResponseEntity.created(new URI("/api/event-level-priority-channels/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /event-level-priority-channels : Updates an existing eventLevelPriorityChannel.
     *
     * @param eventLevelPriorityChannelDTO the eventLevelPriorityChannelDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated eventLevelPriorityChannelDTO,
     * or with status 400 (Bad Request) if the eventLevelPriorityChannelDTO is not valid,
     * or with status 500 (Internal Server Error) if the eventLevelPriorityChannelDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/event-level-priority-channels")
    @Timed
    public ResponseEntity<EventLevelPriorityChannelDTO> updateEventLevelPriorityChannel(@RequestBody EventLevelPriorityChannelDTO eventLevelPriorityChannelDTO) throws URISyntaxException {
        log.debug("REST request to update EventLevelPriorityChannel : {}", eventLevelPriorityChannelDTO);
        if (eventLevelPriorityChannelDTO.getId() == null) {
            return createEventLevelPriorityChannel(eventLevelPriorityChannelDTO);
        }
        EventLevelPriorityChannelDTO result = eventLevelPriorityChannelService.save(eventLevelPriorityChannelDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, eventLevelPriorityChannelDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /event-level-priority-channels : get all the eventLevelPriorityChannels.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of eventLevelPriorityChannels in body
     */
    @GetMapping("/event-level-priority-channels")
    @Timed
    public ResponseEntity<List<EventLevelPriorityChannelDTO>> getAllEventLevelPriorityChannels(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of EventLevelPriorityChannels");
        Page<EventLevelPriorityChannelDTO> page = eventLevelPriorityChannelService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/event-level-priority-channels");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /event-level-priority-channels/:id : get the "id" eventLevelPriorityChannel.
     *
     * @param id the id of the eventLevelPriorityChannelDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the eventLevelPriorityChannelDTO, or with status 404 (Not Found)
     */
    @GetMapping("/event-level-priority-channels/{id}")
    @Timed
    public ResponseEntity<EventLevelPriorityChannelDTO> getEventLevelPriorityChannel(@PathVariable Long id) {
        log.debug("REST request to get EventLevelPriorityChannel : {}", id);
        EventLevelPriorityChannelDTO eventLevelPriorityChannelDTO = eventLevelPriorityChannelService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(eventLevelPriorityChannelDTO));
    }

    /**
     * DELETE  /event-level-priority-channels/:id : delete the "id" eventLevelPriorityChannel.
     *
     * @param id the id of the eventLevelPriorityChannelDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/event-level-priority-channels/{id}")
    @Timed
    public ResponseEntity<Void> deleteEventLevelPriorityChannel(@PathVariable Long id) {
        log.debug("REST request to delete EventLevelPriorityChannel : {}", id);
        eventLevelPriorityChannelService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
