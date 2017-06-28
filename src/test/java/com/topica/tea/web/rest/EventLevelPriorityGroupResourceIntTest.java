package com.topica.tea.web.rest;

import com.topica.tea.TopicaEventAmplifyApp;

import com.topica.tea.domain.EventLevelPriorityGroup;
import com.topica.tea.repository.EventLevelPriorityGroupRepository;
import com.topica.tea.service.EventLevelPriorityGroupService;
import com.topica.tea.service.dto.EventLevelPriorityGroupDTO;
import com.topica.tea.service.mapper.EventLevelPriorityGroupMapper;
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
import com.topica.tea.domain.enumeration.PriorityGroup;
/**
 * Test class for the EventLevelPriorityGroupResource REST controller.
 *
 * @see EventLevelPriorityGroupResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TopicaEventAmplifyApp.class)
public class EventLevelPriorityGroupResourceIntTest {

    private static final Boolean DEFAULT_IS_MEAT_CONTENT = false;
    private static final Boolean UPDATED_IS_MEAT_CONTENT = true;

    private static final EventLevel DEFAULT_EVENT_LEVEL = EventLevel.E1;
    private static final EventLevel UPDATED_EVENT_LEVEL = EventLevel.E2;

    private static final PriorityGroup DEFAULT_PRIORITY_GROUP = PriorityGroup.K0A;
    private static final PriorityGroup UPDATED_PRIORITY_GROUP = PriorityGroup.K0B;

    @Autowired
    private EventLevelPriorityGroupRepository eventLevelPriorityGroupRepository;

    @Autowired
    private EventLevelPriorityGroupMapper eventLevelPriorityGroupMapper;

    @Autowired
    private EventLevelPriorityGroupService eventLevelPriorityGroupService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restEventLevelPriorityGroupMockMvc;

    private EventLevelPriorityGroup eventLevelPriorityGroup;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        EventLevelPriorityGroupResource eventLevelPriorityGroupResource = new EventLevelPriorityGroupResource(eventLevelPriorityGroupService);
        this.restEventLevelPriorityGroupMockMvc = MockMvcBuilders.standaloneSetup(eventLevelPriorityGroupResource)
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
    public static EventLevelPriorityGroup createEntity(EntityManager em) {
        EventLevelPriorityGroup eventLevelPriorityGroup = new EventLevelPriorityGroup()
            .isMeatContent(DEFAULT_IS_MEAT_CONTENT)
            .eventLevel(DEFAULT_EVENT_LEVEL)
            .priorityGroup(DEFAULT_PRIORITY_GROUP);
        return eventLevelPriorityGroup;
    }

    @Before
    public void initTest() {
        eventLevelPriorityGroup = createEntity(em);
    }

    @Test
    @Transactional
    public void createEventLevelPriorityGroup() throws Exception {
        int databaseSizeBeforeCreate = eventLevelPriorityGroupRepository.findAll().size();

        // Create the EventLevelPriorityGroup
        EventLevelPriorityGroupDTO eventLevelPriorityGroupDTO = eventLevelPriorityGroupMapper.toDto(eventLevelPriorityGroup);
        restEventLevelPriorityGroupMockMvc.perform(post("/api/event-level-priority-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(eventLevelPriorityGroupDTO)))
            .andExpect(status().isCreated());

        // Validate the EventLevelPriorityGroup in the database
        List<EventLevelPriorityGroup> eventLevelPriorityGroupList = eventLevelPriorityGroupRepository.findAll();
        assertThat(eventLevelPriorityGroupList).hasSize(databaseSizeBeforeCreate + 1);
        EventLevelPriorityGroup testEventLevelPriorityGroup = eventLevelPriorityGroupList.get(eventLevelPriorityGroupList.size() - 1);
        assertThat(testEventLevelPriorityGroup.isIsMeatContent()).isEqualTo(DEFAULT_IS_MEAT_CONTENT);
        assertThat(testEventLevelPriorityGroup.getEventLevel()).isEqualTo(DEFAULT_EVENT_LEVEL);
        assertThat(testEventLevelPriorityGroup.getPriorityGroup()).isEqualTo(DEFAULT_PRIORITY_GROUP);
    }

    @Test
    @Transactional
    public void createEventLevelPriorityGroupWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = eventLevelPriorityGroupRepository.findAll().size();

        // Create the EventLevelPriorityGroup with an existing ID
        eventLevelPriorityGroup.setId(1L);
        EventLevelPriorityGroupDTO eventLevelPriorityGroupDTO = eventLevelPriorityGroupMapper.toDto(eventLevelPriorityGroup);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEventLevelPriorityGroupMockMvc.perform(post("/api/event-level-priority-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(eventLevelPriorityGroupDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<EventLevelPriorityGroup> eventLevelPriorityGroupList = eventLevelPriorityGroupRepository.findAll();
        assertThat(eventLevelPriorityGroupList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkEventLevelIsRequired() throws Exception {
        int databaseSizeBeforeTest = eventLevelPriorityGroupRepository.findAll().size();
        // set the field null
        eventLevelPriorityGroup.setEventLevel(null);

        // Create the EventLevelPriorityGroup, which fails.
        EventLevelPriorityGroupDTO eventLevelPriorityGroupDTO = eventLevelPriorityGroupMapper.toDto(eventLevelPriorityGroup);

        restEventLevelPriorityGroupMockMvc.perform(post("/api/event-level-priority-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(eventLevelPriorityGroupDTO)))
            .andExpect(status().isBadRequest());

        List<EventLevelPriorityGroup> eventLevelPriorityGroupList = eventLevelPriorityGroupRepository.findAll();
        assertThat(eventLevelPriorityGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPriorityGroupIsRequired() throws Exception {
        int databaseSizeBeforeTest = eventLevelPriorityGroupRepository.findAll().size();
        // set the field null
        eventLevelPriorityGroup.setPriorityGroup(null);

        // Create the EventLevelPriorityGroup, which fails.
        EventLevelPriorityGroupDTO eventLevelPriorityGroupDTO = eventLevelPriorityGroupMapper.toDto(eventLevelPriorityGroup);

        restEventLevelPriorityGroupMockMvc.perform(post("/api/event-level-priority-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(eventLevelPriorityGroupDTO)))
            .andExpect(status().isBadRequest());

        List<EventLevelPriorityGroup> eventLevelPriorityGroupList = eventLevelPriorityGroupRepository.findAll();
        assertThat(eventLevelPriorityGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllEventLevelPriorityGroups() throws Exception {
        // Initialize the database
        eventLevelPriorityGroupRepository.saveAndFlush(eventLevelPriorityGroup);

        // Get all the eventLevelPriorityGroupList
        restEventLevelPriorityGroupMockMvc.perform(get("/api/event-level-priority-groups?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(eventLevelPriorityGroup.getId().intValue())))
            .andExpect(jsonPath("$.[*].isMeatContent").value(hasItem(DEFAULT_IS_MEAT_CONTENT.booleanValue())))
            .andExpect(jsonPath("$.[*].eventLevel").value(hasItem(DEFAULT_EVENT_LEVEL.toString())))
            .andExpect(jsonPath("$.[*].priorityGroup").value(hasItem(DEFAULT_PRIORITY_GROUP.toString())));
    }

    @Test
    @Transactional
    public void getEventLevelPriorityGroup() throws Exception {
        // Initialize the database
        eventLevelPriorityGroupRepository.saveAndFlush(eventLevelPriorityGroup);

        // Get the eventLevelPriorityGroup
        restEventLevelPriorityGroupMockMvc.perform(get("/api/event-level-priority-groups/{id}", eventLevelPriorityGroup.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(eventLevelPriorityGroup.getId().intValue()))
            .andExpect(jsonPath("$.isMeatContent").value(DEFAULT_IS_MEAT_CONTENT.booleanValue()))
            .andExpect(jsonPath("$.eventLevel").value(DEFAULT_EVENT_LEVEL.toString()))
            .andExpect(jsonPath("$.priorityGroup").value(DEFAULT_PRIORITY_GROUP.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingEventLevelPriorityGroup() throws Exception {
        // Get the eventLevelPriorityGroup
        restEventLevelPriorityGroupMockMvc.perform(get("/api/event-level-priority-groups/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEventLevelPriorityGroup() throws Exception {
        // Initialize the database
        eventLevelPriorityGroupRepository.saveAndFlush(eventLevelPriorityGroup);
        int databaseSizeBeforeUpdate = eventLevelPriorityGroupRepository.findAll().size();

        // Update the eventLevelPriorityGroup
        EventLevelPriorityGroup updatedEventLevelPriorityGroup = eventLevelPriorityGroupRepository.findOne(eventLevelPriorityGroup.getId());
        updatedEventLevelPriorityGroup
            .isMeatContent(UPDATED_IS_MEAT_CONTENT)
            .eventLevel(UPDATED_EVENT_LEVEL)
            .priorityGroup(UPDATED_PRIORITY_GROUP);
        EventLevelPriorityGroupDTO eventLevelPriorityGroupDTO = eventLevelPriorityGroupMapper.toDto(updatedEventLevelPriorityGroup);

        restEventLevelPriorityGroupMockMvc.perform(put("/api/event-level-priority-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(eventLevelPriorityGroupDTO)))
            .andExpect(status().isOk());

        // Validate the EventLevelPriorityGroup in the database
        List<EventLevelPriorityGroup> eventLevelPriorityGroupList = eventLevelPriorityGroupRepository.findAll();
        assertThat(eventLevelPriorityGroupList).hasSize(databaseSizeBeforeUpdate);
        EventLevelPriorityGroup testEventLevelPriorityGroup = eventLevelPriorityGroupList.get(eventLevelPriorityGroupList.size() - 1);
        assertThat(testEventLevelPriorityGroup.isIsMeatContent()).isEqualTo(UPDATED_IS_MEAT_CONTENT);
        assertThat(testEventLevelPriorityGroup.getEventLevel()).isEqualTo(UPDATED_EVENT_LEVEL);
        assertThat(testEventLevelPriorityGroup.getPriorityGroup()).isEqualTo(UPDATED_PRIORITY_GROUP);
    }

    @Test
    @Transactional
    public void updateNonExistingEventLevelPriorityGroup() throws Exception {
        int databaseSizeBeforeUpdate = eventLevelPriorityGroupRepository.findAll().size();

        // Create the EventLevelPriorityGroup
        EventLevelPriorityGroupDTO eventLevelPriorityGroupDTO = eventLevelPriorityGroupMapper.toDto(eventLevelPriorityGroup);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restEventLevelPriorityGroupMockMvc.perform(put("/api/event-level-priority-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(eventLevelPriorityGroupDTO)))
            .andExpect(status().isCreated());

        // Validate the EventLevelPriorityGroup in the database
        List<EventLevelPriorityGroup> eventLevelPriorityGroupList = eventLevelPriorityGroupRepository.findAll();
        assertThat(eventLevelPriorityGroupList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteEventLevelPriorityGroup() throws Exception {
        // Initialize the database
        eventLevelPriorityGroupRepository.saveAndFlush(eventLevelPriorityGroup);
        int databaseSizeBeforeDelete = eventLevelPriorityGroupRepository.findAll().size();

        // Get the eventLevelPriorityGroup
        restEventLevelPriorityGroupMockMvc.perform(delete("/api/event-level-priority-groups/{id}", eventLevelPriorityGroup.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<EventLevelPriorityGroup> eventLevelPriorityGroupList = eventLevelPriorityGroupRepository.findAll();
        assertThat(eventLevelPriorityGroupList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EventLevelPriorityGroup.class);
        EventLevelPriorityGroup eventLevelPriorityGroup1 = new EventLevelPriorityGroup();
        eventLevelPriorityGroup1.setId(1L);
        EventLevelPriorityGroup eventLevelPriorityGroup2 = new EventLevelPriorityGroup();
        eventLevelPriorityGroup2.setId(eventLevelPriorityGroup1.getId());
        assertThat(eventLevelPriorityGroup1).isEqualTo(eventLevelPriorityGroup2);
        eventLevelPriorityGroup2.setId(2L);
        assertThat(eventLevelPriorityGroup1).isNotEqualTo(eventLevelPriorityGroup2);
        eventLevelPriorityGroup1.setId(null);
        assertThat(eventLevelPriorityGroup1).isNotEqualTo(eventLevelPriorityGroup2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EventLevelPriorityGroupDTO.class);
        EventLevelPriorityGroupDTO eventLevelPriorityGroupDTO1 = new EventLevelPriorityGroupDTO();
        eventLevelPriorityGroupDTO1.setId(1L);
        EventLevelPriorityGroupDTO eventLevelPriorityGroupDTO2 = new EventLevelPriorityGroupDTO();
        assertThat(eventLevelPriorityGroupDTO1).isNotEqualTo(eventLevelPriorityGroupDTO2);
        eventLevelPriorityGroupDTO2.setId(eventLevelPriorityGroupDTO1.getId());
        assertThat(eventLevelPriorityGroupDTO1).isEqualTo(eventLevelPriorityGroupDTO2);
        eventLevelPriorityGroupDTO2.setId(2L);
        assertThat(eventLevelPriorityGroupDTO1).isNotEqualTo(eventLevelPriorityGroupDTO2);
        eventLevelPriorityGroupDTO1.setId(null);
        assertThat(eventLevelPriorityGroupDTO1).isNotEqualTo(eventLevelPriorityGroupDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(eventLevelPriorityGroupMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(eventLevelPriorityGroupMapper.fromId(null)).isNull();
    }
}
