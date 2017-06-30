package com.topica.tea.service.impl;

import com.topica.tea.service.EventService;
import com.topica.tea.domain.Event;
import com.topica.tea.domain.Question;
import com.topica.tea.domain.enumeration.EventStatus;
import com.topica.tea.repository.EventRepository;
import com.topica.tea.service.dto.EventDTO;
import com.topica.tea.service.mapper.EventMapper;
import com.topica.tea.service.mapper.QuestionMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Event.
 */
@Service
@Transactional
public class EventServiceImpl implements EventService{

    private final Logger log = LoggerFactory.getLogger(EventServiceImpl.class);

    private final EventRepository eventRepository;

    private final EventMapper eventMapper;
    
    private final QuestionMapper questionMapper;

    public EventServiceImpl(EventRepository eventRepository, EventMapper eventMapper, QuestionMapper questionMapper) {
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
        this.questionMapper = questionMapper;
    }

    /**
     * Save a event.
     *
     * @param eventDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public EventDTO save(EventDTO eventDTO) {
        log.debug("Request to save Event : {}", eventDTO);
        Question question = questionMapper.toEntity(eventDTO.getQuestion());
        Event event = eventMapper.toEntity(eventDTO);
        
        // Set extra info
        event.setQuestion(question);
        event.setName("Event-" + question.getId());
        event.setEventStatus(EventStatus.NEW);
        
        event = eventRepository.save(event);
        return eventMapper.toDto(event);
    }

    /**
     *  Get all the events.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EventDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Events");
        return eventRepository.findAll(pageable)
            .map(eventMapper::toDto);
    }

    /**
     *  Get one event by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public EventDTO findOne(Long id) {
        log.debug("Request to get Event : {}", id);
        Event event = eventRepository.findOne(id);
        return eventMapper.toDto(event);
    }

    /**
     *  Delete the  event by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Event : {}", id);
        eventRepository.delete(id);
    }
}
