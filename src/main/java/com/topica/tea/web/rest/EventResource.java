package com.topica.tea.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.topica.tea.domain.enumeration.AmplifyType;
import com.topica.tea.domain.enumeration.EventStatus;
import com.topica.tea.service.ArticleService;
import com.topica.tea.service.ContentService;
import com.topica.tea.service.EventService;
import com.topica.tea.service.ProductHtmlTemplateService;
import com.topica.tea.web.rest.util.HeaderUtil;
import com.topica.tea.web.rest.util.PaginationUtil;
import com.topica.tea.service.dto.ArticleDTO;
import com.topica.tea.service.dto.ChannelProductDTO;
import com.topica.tea.service.dto.EventDTO;
import com.topica.tea.service.dto.ProductHtmlTemplateDTO;

import io.swagger.annotations.ApiParam;
import io.github.jhipster.web.util.ResponseUtil;

import org.apache.commons.lang3.StringUtils;
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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * REST controller for managing Event.
 */
@RestController
@RequestMapping("/api")
public class EventResource {

    private final Logger log = LoggerFactory.getLogger(EventResource.class);

    private static final String ENTITY_NAME = "event";

    private final EventService eventService;
    
    private final ArticleService articleService;
    
    private final ContentService contentService;
    
    private final ProductHtmlTemplateService productHtmlTemplateService;

    public EventResource(EventService eventService, ContentService contentService, ProductHtmlTemplateService productHtmlTemplateService, ArticleService articleService) {
        this.eventService = eventService;
        this.articleService = articleService;
        this.contentService = contentService;
        this.productHtmlTemplateService = productHtmlTemplateService;
    }
    
    /**
     * POST  /events : Create a new event.
     *
     * @param eventDTO the eventDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new eventDTO, or with status 400 (Bad Request) if the event has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/init-event-hot")
    @Timed
    public ResponseEntity<EventDTO> createInitHotEvent(@Valid @RequestBody EventDTO eventDTO) throws URISyntaxException {
        log.debug("REST request to save Hot Event : {}", eventDTO);
        if (eventDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new event cannot already have an ID")).body(null);
        }
        // Save article
        if (eventDTO.getArticle() != null) {
            ArticleDTO articleResultDTO = articleService.save(eventDTO.getArticle());
            eventDTO.setArticle(articleResultDTO);
        }
        
        // Update more info
        updateAmplifyTypeForHotEvent(eventDTO);
        
        // Set event publish
        eventDTO.setEventStatus(EventStatus.NOT_YET_ORDER);
        eventDTO.setIsHotEvent(true);
        EventDTO result = eventService.save(eventDTO);
        return ResponseEntity.created(new URI("/api/init-event-hot/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * POST  /events : Create a new event.
     *
     * @param eventDTO the eventDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new eventDTO, or with status 400 (Bad Request) if the event has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/event-hot")
    @Timed
    public ResponseEntity<EventDTO> editorHotEvent(@Valid @RequestBody EventDTO eventDTO) throws URISyntaxException {
        log.debug("REST request to save Hot Event : {}", eventDTO);
        if (eventDTO.getId() == null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new event cannot already have an empty ID")).body(null);
        }
        // Save article
        eventDTO.getArticle().setTitle("Article for Hot event " + eventDTO.getId());
        ArticleDTO articleResultDTO = articleService.save(eventDTO.getArticle());
        
        // Set event publish
        EventDTO eventDTOResult = eventService.findOne(eventDTO.getId());
        eventDTOResult.setEventStatus(EventStatus.WAIT_SEND_BOSS);
        eventDTOResult.setArticle(articleResultDTO);
        
        EventDTO result = eventService.save(eventDTOResult);
        return ResponseEntity.created(new URI("/api/event-hot/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }
    
    /**
     * POST  /events : Create a new event.
     *
     * @param eventDTO the eventDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new eventDTO, or with status 400 (Bad Request) if the event has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/events")
    @Timed
    public ResponseEntity<EventDTO> createEvent(@Valid @RequestBody EventDTO eventDTO) throws URISyntaxException {
        log.debug("REST request to save Event : {}", eventDTO);
        if (eventDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new event cannot already have an ID")).body(null);
        }
        EventDTO result = eventService.save(eventDTO);
        return ResponseEntity.created(new URI("/api/events/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /events : Updates an existing event.
     *
     * @param eventDTO the eventDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated eventDTO,
     * or with status 400 (Bad Request) if the eventDTO is not valid,
     * or with status 500 (Internal Server Error) if the eventDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/events")
    @Timed
    public ResponseEntity<EventDTO> updateEvent(@Valid @RequestBody EventDTO eventDTO) throws URISyntaxException {
        log.debug("REST request to update Event : {}", eventDTO);
        if (eventDTO.getId() == null) {
            return createEvent(eventDTO);
        }
        EventDTO result = eventService.save(eventDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, eventDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /events : get all the events.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of events in body
     */
    @GetMapping("/events")
    @Timed
    public ResponseEntity<List<EventDTO>> getAllEvents(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of Events");
        Page<EventDTO> page = eventService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/events");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /events/:id : get the "id" event.
     *
     * @param id the id of the eventDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the eventDTO, or with status 404 (Not Found)
     */
    @GetMapping("/events/{id}")
    @Timed
    public ResponseEntity<EventDTO> getEvent(@PathVariable Long id) {
        log.debug("REST request to get Event : {}", id);
        EventDTO eventDTO = eventService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(eventDTO));
    }

    /**
     * DELETE  /events/:id : delete the "id" event.
     *
     * @param id the id of the eventDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/events/{id}")
    @Timed
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        log.debug("REST request to delete Event : {}", id);
        eventService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
    
    /**
     * GET  /events/:id : get the "id" event.
     *
     * @param id the id of the eventDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the eventDTO, or with status 404 (Not Found)
     */
    @GetMapping("/events/{id}/{userId}")
    @Timed
    public ResponseEntity<EventDTO> distributeEvent(@PathVariable Long id, @PathVariable Long userId) {
        log.debug("REST request to distribute Event : id {}, userId {}", id, userId);
        EventDTO eventDTO = eventService.distribute(id, userId);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(eventDTO));
    }
    
    /**
     * GET  /events/:id : get the "id" event.
     *
     * @param id the id of the eventDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the eventDTO, or with status 404 (Not Found)
     */
    @PostMapping("/events/editor")
    @Timed
    public ResponseEntity<EventDTO> editorEvent(@Valid @RequestBody EventDTO eventDTO) {
        log.debug("REST request to editor Event : {}", eventDTO);
        EventDTO result = eventService.editor(eventDTO);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
    }
    
    /**
     * GET  /events/:id : get the "id" event.
     *
     * @param id the id of the eventDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the eventDTO, or with status 404 (Not Found)
     */
    @GetMapping("/events/{id}/updateStatus/{status}/{type}")
    @Timed
    public ResponseEntity<EventDTO> updateStatus(@PathVariable Long id, @PathVariable String status, @PathVariable Long type) {
    	// Type : 0 director, 1: manager
        log.debug("REST request to updateStatus : id {}, status {}, type {}", id, status, type);
        EventDTO eventDTO = eventService.updateStatus(id, status);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(eventDTO));
    }
    
    /**
     * GET  /events/:id : get the event inject by ProductCode.
     *
     * @param id the id of the eventDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the eventDTO, or with status 404 (Not Found)
     */
    @GetMapping("/inject-event")
    @Timed
    //public ResponseEntity<EventDTO> getInjectEventByProductCode(@RequestParam(name="channelProductId", required = false) Long channelProductId,
    public String getInjectEventByProductCode(@RequestParam(name="channelProductId", required = false) Long channelProductId,	
    		@RequestParam(name="templateId", required = false) Long templateId) {
        log.debug("REST request to getInjectEventByProductCode : channelProductId {}, templateId {}", channelProductId, templateId);
        
        // Find relation between product and template
        ProductHtmlTemplateDTO ptDTO = productHtmlTemplateService.findOneByChannelProductIdAndHtmlTemplateId(channelProductId, templateId);
        if (null == ptDTO) {
        	return StringUtils.EMPTY;
        }
        
        EventDTO eventDTO = eventService.getPublishInjectEventByChannelProductId(channelProductId);
        
        if (null == eventDTO) {
        	return StringUtils.EMPTY;
        }
        
        // Process content
        if (null != eventDTO) {
        	String content = contentService.process(eventDTO, templateId);
            eventDTO.setContent(content);
        }
        
        return StringUtils.isNotEmpty(eventDTO.getContent()) ? eventDTO.getContent() : StringUtils.EMPTY;
    }
    
    private void updateAmplifyTypeForHotEvent(EventDTO eventDTO) {
    	Set<AmplifyType> setAmplifyTye = new HashSet<>();
//    	List<AmplifyType> lstAmplifyTye = new ArrayList<>();
    	
    	// Landing page, mail -> INJECT
    	// Fanpage -> SHARE
    	for (ChannelProductDTO channel : eventDTO.getChannelProducts()) {
			if (StringUtils.equals(channel.getAdsType().getName(), "Landing page") 
					|| StringUtils.equals(channel.getAdsType().getName(), "Email") ) {
				setAmplifyTye.add(AmplifyType.INJECT);
			} else if (StringUtils.equals(channel.getAdsType().getName(), "Fanpage") ) {
				setAmplifyTye.add(AmplifyType.SHARE); 
			}
		}
    	
    	eventDTO.setAmplifyType(new ArrayList<>(setAmplifyTye));
    	
    }
    
}
