package com.topica.tea.web.rest;

import com.topica.tea.TopicaEventAmplifyApp;

import com.topica.tea.domain.Event;
import com.topica.tea.repository.EventRepository;
import com.topica.tea.service.EventService;
import com.topica.tea.service.dto.EventDTO;
import com.topica.tea.service.mapper.EventMapper;
import com.topica.tea.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.util.List;

import static com.topica.tea.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.topica.tea.domain.enumeration.EventStatus;
import com.topica.tea.domain.enumeration.EventLevel;
import com.topica.tea.domain.enumeration.AmplifyType;
import com.topica.tea.domain.enumeration.PriorityGroup;
/**
 * Test class for the EventResource REST controller.
 *
 * @see EventResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TopicaEventAmplifyApp.class)
public class EventResourceIntTest {

    private static final EventStatus DEFAULT_EVENT_STATUS = EventStatus.NEW;
    private static final EventStatus UPDATED_EVENT_STATUS = EventStatus.PROCESS;

    private static final EventLevel DEFAULT_EVENT_LEVEL = EventLevel.E1;
    private static final EventLevel UPDATED_EVENT_LEVEL = EventLevel.E2;

    private static final AmplifyType DEFAULT_AMPLIFY_TYPE = AmplifyType.SHARE;
    private static final AmplifyType UPDATED_AMPLIFY_TYPE = AmplifyType.SPONSOR;

    private static final PriorityGroup DEFAULT_PRIORITY_GROUP = PriorityGroup.K0A;
    private static final PriorityGroup UPDATED_PRIORITY_GROUP = PriorityGroup.K0B;

    private static final ZonedDateTime DEFAULT_SCHEDULE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_SCHEDULE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventMapper eventMapper;

    @Autowired
    private EventService eventService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restEventMockMvc;

    private Event event;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        EventResource eventResource = new EventResource(eventService);
        this.restEventMockMvc = MockMvcBuilders.standaloneSetup(eventResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Event createEntity(EntityManager em) {
        Event event = new Event()
            .eventStatus(DEFAULT_EVENT_STATUS)
            .eventLevel(DEFAULT_EVENT_LEVEL)
            .amplifyType(DEFAULT_AMPLIFY_TYPE)
            .priorityGroup(DEFAULT_PRIORITY_GROUP)
            .schedule(DEFAULT_SCHEDULE);
        return event;
    }

    @Before
    public void initTest() {
        event = createEntity(em);
    }

    @Test
    @Transactional
    public void createEvent() throws Exception {
        int databaseSizeBeforeCreate = eventRepository.findAll().size();

        // Create the Event
        EventDTO eventDTO = eventMapper.toDto(event);
        restEventMockMvc.perform(post("/api/events")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(eventDTO)))
            .andExpect(status().isCreated());

        // Validate the Event in the database
        List<Event> eventList = eventRepository.findAll();
        assertThat(eventList).hasSize(databaseSizeBeforeCreate + 1);
        Event testEvent = eventList.get(eventList.size() - 1);
        assertThat(testEvent.getEventStatus()).isEqualTo(DEFAULT_EVENT_STATUS);
        assertThat(testEvent.getEventLevel()).isEqualTo(DEFAULT_EVENT_LEVEL);
        assertThat(testEvent.getAmplifyType()).isEqualTo(DEFAULT_AMPLIFY_TYPE);
        assertThat(testEvent.getPriorityGroup()).isEqualTo(DEFAULT_PRIORITY_GROUP);
        assertThat(testEvent.getSchedule()).isEqualTo(DEFAULT_SCHEDULE);
    }

    @Test
    @Transactional
    public void createEventWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = eventRepository.findAll().size();

        // Create the Event with an existing ID
        event.setId(1L);
        EventDTO eventDTO = eventMapper.toDto(event);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEventMockMvc.perform(post("/api/events")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(eventDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Event> eventList = eventRepository.findAll();
        assertThat(eventList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllEvents() throws Exception {
        // Initialize the database
        eventRepository.saveAndFlush(event);

        // Get all the eventList
        restEventMockMvc.perform(get("/api/events?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(event.getId().intValue())))
            .andExpect(jsonPath("$.[*].eventStatus").value(hasItem(DEFAULT_EVENT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].eventLevel").value(hasItem(DEFAULT_EVENT_LEVEL.toString())))
            .andExpect(jsonPath("$.[*].amplifyType").value(hasItem(DEFAULT_AMPLIFY_TYPE.toString())))
            .andExpect(jsonPath("$.[*].priorityGroup").value(hasItem(DEFAULT_PRIORITY_GROUP.toString())))
            .andExpect(jsonPath("$.[*].schedule").value(hasItem(sameInstant(DEFAULT_SCHEDULE))));
    }

    @Test
    @Transactional
    public void getEvent() throws Exception {
        // Initialize the database
        eventRepository.saveAndFlush(event);

        // Get the event
        restEventMockMvc.perform(get("/api/events/{id}", event.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(event.getId().intValue()))
            .andExpect(jsonPath("$.eventStatus").value(DEFAULT_EVENT_STATUS.toString()))
            .andExpect(jsonPath("$.eventLevel").value(DEFAULT_EVENT_LEVEL.toString()))
            .andExpect(jsonPath("$.amplifyType").value(DEFAULT_AMPLIFY_TYPE.toString()))
            .andExpect(jsonPath("$.priorityGroup").value(DEFAULT_PRIORITY_GROUP.toString()))
            .andExpect(jsonPath("$.schedule").value(sameInstant(DEFAULT_SCHEDULE)));
    }

    @Test
    @Transactional
    public void getNonExistingEvent() throws Exception {
        // Get the event
        restEventMockMvc.perform(get("/api/events/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEvent() throws Exception {
        // Initialize the database
        eventRepository.saveAndFlush(event);
        int databaseSizeBeforeUpdate = eventRepository.findAll().size();

        // Update the event
        Event updatedEvent = eventRepository.findOne(event.getId());
        updatedEvent
            .eventStatus(UPDATED_EVENT_STATUS)
            .eventLevel(UPDATED_EVENT_LEVEL)
            .amplifyType(UPDATED_AMPLIFY_TYPE)
            .priorityGroup(UPDATED_PRIORITY_GROUP)
            .schedule(UPDATED_SCHEDULE);
        EventDTO eventDTO = eventMapper.toDto(updatedEvent);

        restEventMockMvc.perform(put("/api/events")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(eventDTO)))
            .andExpect(status().isOk());

        // Validate the Event in the database
        List<Event> eventList = eventRepository.findAll();
        assertThat(eventList).hasSize(databaseSizeBeforeUpdate);
        Event testEvent = eventList.get(eventList.size() - 1);
        assertThat(testEvent.getEventStatus()).isEqualTo(UPDATED_EVENT_STATUS);
        assertThat(testEvent.getEventLevel()).isEqualTo(UPDATED_EVENT_LEVEL);
        assertThat(testEvent.getAmplifyType()).isEqualTo(UPDATED_AMPLIFY_TYPE);
        assertThat(testEvent.getPriorityGroup()).isEqualTo(UPDATED_PRIORITY_GROUP);
        assertThat(testEvent.getSchedule()).isEqualTo(UPDATED_SCHEDULE);
    }

    @Test
    @Transactional
    public void updateNonExistingEvent() throws Exception {
        int databaseSizeBeforeUpdate = eventRepository.findAll().size();

        // Create the Event
        EventDTO eventDTO = eventMapper.toDto(event);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restEventMockMvc.perform(put("/api/events")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(eventDTO)))
            .andExpect(status().isCreated());

        // Validate the Event in the database
        List<Event> eventList = eventRepository.findAll();
        assertThat(eventList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteEvent() throws Exception {
        // Initialize the database
        eventRepository.saveAndFlush(event);
        int databaseSizeBeforeDelete = eventRepository.findAll().size();

        // Get the event
        restEventMockMvc.perform(delete("/api/events/{id}", event.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Event> eventList = eventRepository.findAll();
        assertThat(eventList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Event.class);
        Event event1 = new Event();
        event1.setId(1L);
        Event event2 = new Event();
        event2.setId(event1.getId());
        assertThat(event1).isEqualTo(event2);
        event2.setId(2L);
        assertThat(event1).isNotEqualTo(event2);
        event1.setId(null);
        assertThat(event1).isNotEqualTo(event2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EventDTO.class);
        EventDTO eventDTO1 = new EventDTO();
        eventDTO1.setId(1L);
        EventDTO eventDTO2 = new EventDTO();
        assertThat(eventDTO1).isNotEqualTo(eventDTO2);
        eventDTO2.setId(eventDTO1.getId());
        assertThat(eventDTO1).isEqualTo(eventDTO2);
        eventDTO2.setId(2L);
        assertThat(eventDTO1).isNotEqualTo(eventDTO2);
        eventDTO1.setId(null);
        assertThat(eventDTO1).isNotEqualTo(eventDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(eventMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(eventMapper.fromId(null)).isNull();
    }
}
