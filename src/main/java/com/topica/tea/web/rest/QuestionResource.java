package com.topica.tea.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.topica.tea.domain.User;
import com.topica.tea.domain.enumeration.EventLevel;
import com.topica.tea.security.SecurityUtils;
import com.topica.tea.service.EventService;
import com.topica.tea.service.MailService;
import com.topica.tea.service.QuestionService;
import com.topica.tea.service.TEAProcessEventService;
import com.topica.tea.service.UserService;
import com.topica.tea.web.rest.util.HeaderUtil;
import com.topica.tea.web.rest.util.PaginationUtil;
import com.topica.tea.service.dto.EventDTO;
import com.topica.tea.service.dto.QuestionDTO;
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
 * REST controller for managing Question.
 */
@RestController
@RequestMapping("/api")
public class QuestionResource {

    private final Logger log = LoggerFactory.getLogger(QuestionResource.class);

    private static final String ENTITY_NAME = "question";

    private final QuestionService questionService;

    private final UserService userService;
    
    private final EventService eventService;
    
    private final TEAProcessEventService teaProcessEventService;
    
    private final MailService mailService;
    
    
    public QuestionResource(QuestionService questionService, UserService userService
    		, EventService eventService
    		, TEAProcessEventService teaProcessEventService
    		, MailService mailService) {
        this.questionService = questionService;
        this.userService = userService;
        this.eventService = eventService;
        this.teaProcessEventService = teaProcessEventService;
        this.mailService = mailService;
    }

    /**
     * POST  /questions : Create a new question.
     *
     * @param questionDTO the questionDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new questionDTO, or with status 400 (Bad Request) if the question has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/questions")
    @Timed
    public ResponseEntity<QuestionDTO> createQuestion(@RequestBody QuestionDTO questionDTO) throws URISyntaxException {
    	log.debug("REST request to save Question : {}", questionDTO);
        // Find user login
        String username = SecurityUtils.getCurrentUserLogin();
        Optional<User> userOpt = userService.getUserWithAuthoritiesByLogin(username);
        User user = userOpt.get();
        
        if (questionDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new question cannot already have an ID")).body(null);
        }
        QuestionDTO result = questionService.save(questionDTO);
        
        // Create event
        EventDTO eventDTO = new EventDTO();
        eventDTO.setQuestion(result);
        eventDTO.setCreatedUserId(user.getId());
        eventDTO.setName("Event-" + result.getId());
        EventDTO classifyEventDto = teaProcessEventService.classify(eventDTO);
        
        // Save event
        EventDTO resultEventDto = eventService.save(classifyEventDto);
        
        // Send mail
        sendNotificationCreatedEventMail(user, resultEventDto);
                
        return ResponseEntity.created(new URI("/api/questions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    private void sendNotificationCreatedEventMail(User director, EventDTO eventDTO) {
    	// Check level
    	// E4 khong khech dai
    	if (EventLevel.E4.equals(eventDTO.getEventLevel())) {
    		mailService.sendNotificationCreatedEventFail(director);
    	} else {
    		mailService.sendNotificationCreatedEventSuccess(director);
    		// Get user coordinate
    		// Send mail to Director and Coordinator
            List<User> coordinators = userService.getAllUsersByAuthority("ROLE_COORDINATOR");
            if (null != coordinators && coordinators.size() > 0) {
            	for (User user : coordinators) {
            		mailService.sendNotificationCreatedEventSuccess(user);
				}
            }
    	}
    	
    }
    /**
     * PUT  /questions : Updates an existing question.
     *
     * @param questionDTO the questionDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated questionDTO,
     * or with status 400 (Bad Request) if the questionDTO is not valid,
     * or with status 500 (Internal Server Error) if the questionDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/questions")
    @Timed
    public ResponseEntity<QuestionDTO> updateQuestion(@RequestBody QuestionDTO questionDTO) throws URISyntaxException {
        log.debug("REST request to update Question : {}", questionDTO);
        if (questionDTO.getId() == null) {
            return createQuestion(questionDTO);
        }
        QuestionDTO result = questionService.save(questionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, questionDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /questions : get all the questions.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of questions in body
     */
    @GetMapping("/questions")
    @Timed
    public ResponseEntity<List<QuestionDTO>> getAllQuestions(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of Questions");
        Page<QuestionDTO> page = questionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/questions");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /questions/:id : get the "id" question.
     *
     * @param id the id of the questionDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the questionDTO, or with status 404 (Not Found)
     */
    @GetMapping("/questions/{id}")
    @Timed
    public ResponseEntity<QuestionDTO> getQuestion(@PathVariable Long id) {
        log.debug("REST request to get Question : {}", id);
        QuestionDTO questionDTO = questionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(questionDTO));
    }

    /**
     * DELETE  /questions/:id : delete the "id" question.
     *
     * @param id the id of the questionDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/questions/{id}")
    @Timed
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long id) {
        log.debug("REST request to delete Question : {}", id);
        questionService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
