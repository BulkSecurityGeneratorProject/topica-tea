package com.topica.tea.web.rest;

import com.topica.tea.TopicaEventAmplifyApp;

import com.topica.tea.domain.EventLevelPriorityChannel;
import com.topica.tea.repository.EventLevelPriorityChannelRepository;
import com.topica.tea.service.EventLevelPriorityChannelService;
import com.topica.tea.service.dto.EventLevelPriorityChannelDTO;
import com.topica.tea.service.mapper.EventLevelPriorityChannelMapper;
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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.topica.tea.domain.enumeration.EventLevel;
/**
 * Test class for the EventLevelPriorityChannelResource REST controller.
 *
 * @see EventLevelPriorityChannelResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TopicaEventAmplifyApp.class)
public class EventLevelPriorityChannelResourceIntTest {

    private static final Boolean DEFAULT_IS_MEAT_CONTENT = false;
    private static final Boolean UPDATED_IS_MEAT_CONTENT = true;

    private static final EventLevel DEFAULT_EVENT_LEVEL = EventLevel.E1;
    private static final EventLevel UPDATED_EVENT_LEVEL = EventLevel.E2;

    @Autowired
    private EventLevelPriorityChannelRepository eventLevelPriorityChannelRepository;

    @Autowired
    private EventLevelPriorityChannelMapper eventLevelPriorityChannelMapper;

    @Autowired
    private EventLevelPriorityChannelService eventLevelPriorityChannelService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restEventLevelPriorityChannelMockMvc;

    private EventLevelPriorityChannel eventLevelPriorityChannel;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        EventLevelPriorityChannelResource eventLevelPriorityChannelResource = new EventLevelPriorityChannelResource(eventLevelPriorityChannelService);
        this.restEventLevelPriorityChannelMockMvc = MockMvcBuilders.standaloneSetup(eventLevelPriorityChannelResource)
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
    public static EventLevelPriorityChannel createEntity(EntityManager em) {
        EventLevelPriorityChannel eventLevelPriorityChannel = new EventLevelPriorityChannel()
            .isMeatContent(DEFAULT_IS_MEAT_CONTENT)
            .eventLevel(DEFAULT_EVENT_LEVEL);
        return eventLevelPriorityChannel;
    }

    @Before
    public void initTest() {
        eventLevelPriorityChannel = createEntity(em);
    }

    @Test
    @Transactional
    public void createEventLevelPriorityChannel() throws Exception {
        int databaseSizeBeforeCreate = eventLevelPriorityChannelRepository.findAll().size();

        // Create the EventLevelPriorityChannel
        EventLevelPriorityChannelDTO eventLevelPriorityChannelDTO = eventLevelPriorityChannelMapper.toDto(eventLevelPriorityChannel);
        restEventLevelPriorityChannelMockMvc.perform(post("/api/event-level-priority-channels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(eventLevelPriorityChannelDTO)))
            .andExpect(status().isCreated());

        // Validate the EventLevelPriorityChannel in the database
        List<EventLevelPriorityChannel> eventLevelPriorityChannelList = eventLevelPriorityChannelRepository.findAll();
        assertThat(eventLevelPriorityChannelList).hasSize(databaseSizeBeforeCreate + 1);
        EventLevelPriorityChannel testEventLevelPriorityChannel = eventLevelPriorityChannelList.get(eventLevelPriorityChannelList.size() - 1);
        assertThat(testEventLevelPriorityChannel.isIsMeatContent()).isEqualTo(DEFAULT_IS_MEAT_CONTENT);
        assertThat(testEventLevelPriorityChannel.getEventLevel()).isEqualTo(DEFAULT_EVENT_LEVEL);
    }

    @Test
    @Transactional
    public void createEventLevelPriorityChannelWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = eventLevelPriorityChannelRepository.findAll().size();

        // Create the EventLevelPriorityChannel with an existing ID
        eventLevelPriorityChannel.setId(1L);
        EventLevelPriorityChannelDTO eventLevelPriorityChannelDTO = eventLevelPriorityChannelMapper.toDto(eventLevelPriorityChannel);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEventLevelPriorityChannelMockMvc.perform(post("/api/event-level-priority-channels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(eventLevelPriorityChannelDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<EventLevelPriorityChannel> eventLevelPriorityChannelList = eventLevelPriorityChannelRepository.findAll();
        assertThat(eventLevelPriorityChannelList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllEventLevelPriorityChannels() throws Exception {
        // Initialize the database
        eventLevelPriorityChannelRepository.saveAndFlush(eventLevelPriorityChannel);

        // Get all the eventLevelPriorityChannelList
        restEventLevelPriorityChannelMockMvc.perform(get("/api/event-level-priority-channels?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(eventLevelPriorityChannel.getId().intValue())))
            .andExpect(jsonPath("$.[*].isMeatContent").value(hasItem(DEFAULT_IS_MEAT_CONTENT.booleanValue())))
            .andExpect(jsonPath("$.[*].eventLevel").value(hasItem(DEFAULT_EVENT_LEVEL.toString())));
    }

    @Test
    @Transactional
    public void getEventLevelPriorityChannel() throws Exception {
        // Initialize the database
        eventLevelPriorityChannelRepository.saveAndFlush(eventLevelPriorityChannel);

        // Get the eventLevelPriorityChannel
        restEventLevelPriorityChannelMockMvc.perform(get("/api/event-level-priority-channels/{id}", eventLevelPriorityChannel.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(eventLevelPriorityChannel.getId().intValue()))
            .andExpect(jsonPath("$.isMeatContent").value(DEFAULT_IS_MEAT_CONTENT.booleanValue()))
            .andExpect(jsonPath("$.eventLevel").value(DEFAULT_EVENT_LEVEL.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingEventLevelPriorityChannel() throws Exception {
        // Get the eventLevelPriorityChannel
        restEventLevelPriorityChannelMockMvc.perform(get("/api/event-level-priority-channels/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEventLevelPriorityChannel() throws Exception {
        // Initialize the database
        eventLevelPriorityChannelRepository.saveAndFlush(eventLevelPriorityChannel);
        int databaseSizeBeforeUpdate = eventLevelPriorityChannelRepository.findAll().size();

        // Update the eventLevelPriorityChannel
        EventLevelPriorityChannel updatedEventLevelPriorityChannel = eventLevelPriorityChannelRepository.findOne(eventLevelPriorityChannel.getId());
        updatedEventLevelPriorityChannel
            .isMeatContent(UPDATED_IS_MEAT_CONTENT)
            .eventLevel(UPDATED_EVENT_LEVEL);
        EventLevelPriorityChannelDTO eventLevelPriorityChannelDTO = eventLevelPriorityChannelMapper.toDto(updatedEventLevelPriorityChannel);

        restEventLevelPriorityChannelMockMvc.perform(put("/api/event-level-priority-channels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(eventLevelPriorityChannelDTO)))
            .andExpect(status().isOk());

        // Validate the EventLevelPriorityChannel in the database
        List<EventLevelPriorityChannel> eventLevelPriorityChannelList = eventLevelPriorityChannelRepository.findAll();
        assertThat(eventLevelPriorityChannelList).hasSize(databaseSizeBeforeUpdate);
        EventLevelPriorityChannel testEventLevelPriorityChannel = eventLevelPriorityChannelList.get(eventLevelPriorityChannelList.size() - 1);
        assertThat(testEventLevelPriorityChannel.isIsMeatContent()).isEqualTo(UPDATED_IS_MEAT_CONTENT);
        assertThat(testEventLevelPriorityChannel.getEventLevel()).isEqualTo(UPDATED_EVENT_LEVEL);
    }

    @Test
    @Transactional
    public void updateNonExistingEventLevelPriorityChannel() throws Exception {
        int databaseSizeBeforeUpdate = eventLevelPriorityChannelRepository.findAll().size();

        // Create the EventLevelPriorityChannel
        EventLevelPriorityChannelDTO eventLevelPriorityChannelDTO = eventLevelPriorityChannelMapper.toDto(eventLevelPriorityChannel);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restEventLevelPriorityChannelMockMvc.perform(put("/api/event-level-priority-channels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(eventLevelPriorityChannelDTO)))
            .andExpect(status().isCreated());

        // Validate the EventLevelPriorityChannel in the database
        List<EventLevelPriorityChannel> eventLevelPriorityChannelList = eventLevelPriorityChannelRepository.findAll();
        assertThat(eventLevelPriorityChannelList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteEventLevelPriorityChannel() throws Exception {
        // Initialize the database
        eventLevelPriorityChannelRepository.saveAndFlush(eventLevelPriorityChannel);
        int databaseSizeBeforeDelete = eventLevelPriorityChannelRepository.findAll().size();

        // Get the eventLevelPriorityChannel
        restEventLevelPriorityChannelMockMvc.perform(delete("/api/event-level-priority-channels/{id}", eventLevelPriorityChannel.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<EventLevelPriorityChannel> eventLevelPriorityChannelList = eventLevelPriorityChannelRepository.findAll();
        assertThat(eventLevelPriorityChannelList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EventLevelPriorityChannel.class);
        EventLevelPriorityChannel eventLevelPriorityChannel1 = new EventLevelPriorityChannel();
        eventLevelPriorityChannel1.setId(1L);
        EventLevelPriorityChannel eventLevelPriorityChannel2 = new EventLevelPriorityChannel();
        eventLevelPriorityChannel2.setId(eventLevelPriorityChannel1.getId());
        assertThat(eventLevelPriorityChannel1).isEqualTo(eventLevelPriorityChannel2);
        eventLevelPriorityChannel2.setId(2L);
        assertThat(eventLevelPriorityChannel1).isNotEqualTo(eventLevelPriorityChannel2);
        eventLevelPriorityChannel1.setId(null);
        assertThat(eventLevelPriorityChannel1).isNotEqualTo(eventLevelPriorityChannel2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EventLevelPriorityChannelDTO.class);
        EventLevelPriorityChannelDTO eventLevelPriorityChannelDTO1 = new EventLevelPriorityChannelDTO();
        eventLevelPriorityChannelDTO1.setId(1L);
        EventLevelPriorityChannelDTO eventLevelPriorityChannelDTO2 = new EventLevelPriorityChannelDTO();
        assertThat(eventLevelPriorityChannelDTO1).isNotEqualTo(eventLevelPriorityChannelDTO2);
        eventLevelPriorityChannelDTO2.setId(eventLevelPriorityChannelDTO1.getId());
        assertThat(eventLevelPriorityChannelDTO1).isEqualTo(eventLevelPriorityChannelDTO2);
        eventLevelPriorityChannelDTO2.setId(2L);
        assertThat(eventLevelPriorityChannelDTO1).isNotEqualTo(eventLevelPriorityChannelDTO2);
        eventLevelPriorityChannelDTO1.setId(null);
        assertThat(eventLevelPriorityChannelDTO1).isNotEqualTo(eventLevelPriorityChannelDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(eventLevelPriorityChannelMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(eventLevelPriorityChannelMapper.fromId(null)).isNull();
    }
}
