package com.topica.tea.web.rest;

import com.topica.tea.TopicaEventAmplifyApp;

import com.topica.tea.domain.ChannelGroup;
import com.topica.tea.repository.ChannelGroupRepository;
import com.topica.tea.service.ChannelGroupService;
import com.topica.tea.service.dto.ChannelGroupDTO;
import com.topica.tea.service.mapper.ChannelGroupMapper;
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

import com.topica.tea.domain.enumeration.ChannelGroupType;
/**
 * Test class for the ChannelGroupResource REST controller.
 *
 * @see ChannelGroupResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TopicaEventAmplifyApp.class)
public class ChannelGroupResourceIntTest {

    private static final ChannelGroupType DEFAULT_CHANNEL_GROUP_TYPE = ChannelGroupType.ROLE;
    private static final ChannelGroupType UPDATED_CHANNEL_GROUP_TYPE = ChannelGroupType.INVITEE;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_POINT = 1L;
    private static final Long UPDATED_POINT = 2L;

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private ChannelGroupRepository channelGroupRepository;

    @Autowired
    private ChannelGroupMapper channelGroupMapper;

    @Autowired
    private ChannelGroupService channelGroupService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restChannelGroupMockMvc;

    private ChannelGroup channelGroup;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ChannelGroupResource channelGroupResource = new ChannelGroupResource(channelGroupService);
        this.restChannelGroupMockMvc = MockMvcBuilders.standaloneSetup(channelGroupResource)
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
    public static ChannelGroup createEntity(EntityManager em) {
        ChannelGroup channelGroup = new ChannelGroup()
            .channelGroupType(DEFAULT_CHANNEL_GROUP_TYPE)
            .name(DEFAULT_NAME)
            .point(DEFAULT_POINT)
            .description(DEFAULT_DESCRIPTION);
        return channelGroup;
    }

    @Before
    public void initTest() {
        channelGroup = createEntity(em);
    }

    @Test
    @Transactional
    public void createChannelGroup() throws Exception {
        int databaseSizeBeforeCreate = channelGroupRepository.findAll().size();

        // Create the ChannelGroup
        ChannelGroupDTO channelGroupDTO = channelGroupMapper.toDto(channelGroup);
        restChannelGroupMockMvc.perform(post("/api/channel-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(channelGroupDTO)))
            .andExpect(status().isCreated());

        // Validate the ChannelGroup in the database
        List<ChannelGroup> channelGroupList = channelGroupRepository.findAll();
        assertThat(channelGroupList).hasSize(databaseSizeBeforeCreate + 1);
        ChannelGroup testChannelGroup = channelGroupList.get(channelGroupList.size() - 1);
        assertThat(testChannelGroup.getChannelGroupType()).isEqualTo(DEFAULT_CHANNEL_GROUP_TYPE);
        assertThat(testChannelGroup.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testChannelGroup.getPoint()).isEqualTo(DEFAULT_POINT);
        assertThat(testChannelGroup.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createChannelGroupWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = channelGroupRepository.findAll().size();

        // Create the ChannelGroup with an existing ID
        channelGroup.setId(1L);
        ChannelGroupDTO channelGroupDTO = channelGroupMapper.toDto(channelGroup);

        // An entity with an existing ID cannot be created, so this API call must fail
        restChannelGroupMockMvc.perform(post("/api/channel-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(channelGroupDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<ChannelGroup> channelGroupList = channelGroupRepository.findAll();
        assertThat(channelGroupList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkChannelGroupTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = channelGroupRepository.findAll().size();
        // set the field null
        channelGroup.setChannelGroupType(null);

        // Create the ChannelGroup, which fails.
        ChannelGroupDTO channelGroupDTO = channelGroupMapper.toDto(channelGroup);

        restChannelGroupMockMvc.perform(post("/api/channel-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(channelGroupDTO)))
            .andExpect(status().isBadRequest());

        List<ChannelGroup> channelGroupList = channelGroupRepository.findAll();
        assertThat(channelGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = channelGroupRepository.findAll().size();
        // set the field null
        channelGroup.setName(null);

        // Create the ChannelGroup, which fails.
        ChannelGroupDTO channelGroupDTO = channelGroupMapper.toDto(channelGroup);

        restChannelGroupMockMvc.perform(post("/api/channel-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(channelGroupDTO)))
            .andExpect(status().isBadRequest());

        List<ChannelGroup> channelGroupList = channelGroupRepository.findAll();
        assertThat(channelGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPointIsRequired() throws Exception {
        int databaseSizeBeforeTest = channelGroupRepository.findAll().size();
        // set the field null
        channelGroup.setPoint(null);

        // Create the ChannelGroup, which fails.
        ChannelGroupDTO channelGroupDTO = channelGroupMapper.toDto(channelGroup);

        restChannelGroupMockMvc.perform(post("/api/channel-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(channelGroupDTO)))
            .andExpect(status().isBadRequest());

        List<ChannelGroup> channelGroupList = channelGroupRepository.findAll();
        assertThat(channelGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllChannelGroups() throws Exception {
        // Initialize the database
        channelGroupRepository.saveAndFlush(channelGroup);

        // Get all the channelGroupList
        restChannelGroupMockMvc.perform(get("/api/channel-groups?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(channelGroup.getId().intValue())))
            .andExpect(jsonPath("$.[*].channelGroupType").value(hasItem(DEFAULT_CHANNEL_GROUP_TYPE.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].point").value(hasItem(DEFAULT_POINT.intValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));
    }

    @Test
    @Transactional
    public void getChannelGroup() throws Exception {
        // Initialize the database
        channelGroupRepository.saveAndFlush(channelGroup);

        // Get the channelGroup
        restChannelGroupMockMvc.perform(get("/api/channel-groups/{id}", channelGroup.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(channelGroup.getId().intValue()))
            .andExpect(jsonPath("$.channelGroupType").value(DEFAULT_CHANNEL_GROUP_TYPE.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.point").value(DEFAULT_POINT.intValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingChannelGroup() throws Exception {
        // Get the channelGroup
        restChannelGroupMockMvc.perform(get("/api/channel-groups/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateChannelGroup() throws Exception {
        // Initialize the database
        channelGroupRepository.saveAndFlush(channelGroup);
        int databaseSizeBeforeUpdate = channelGroupRepository.findAll().size();

        // Update the channelGroup
        ChannelGroup updatedChannelGroup = channelGroupRepository.findOne(channelGroup.getId());
        updatedChannelGroup
            .channelGroupType(UPDATED_CHANNEL_GROUP_TYPE)
            .name(UPDATED_NAME)
            .point(UPDATED_POINT)
            .description(UPDATED_DESCRIPTION);
        ChannelGroupDTO channelGroupDTO = channelGroupMapper.toDto(updatedChannelGroup);

        restChannelGroupMockMvc.perform(put("/api/channel-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(channelGroupDTO)))
            .andExpect(status().isOk());

        // Validate the ChannelGroup in the database
        List<ChannelGroup> channelGroupList = channelGroupRepository.findAll();
        assertThat(channelGroupList).hasSize(databaseSizeBeforeUpdate);
        ChannelGroup testChannelGroup = channelGroupList.get(channelGroupList.size() - 1);
        assertThat(testChannelGroup.getChannelGroupType()).isEqualTo(UPDATED_CHANNEL_GROUP_TYPE);
        assertThat(testChannelGroup.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testChannelGroup.getPoint()).isEqualTo(UPDATED_POINT);
        assertThat(testChannelGroup.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingChannelGroup() throws Exception {
        int databaseSizeBeforeUpdate = channelGroupRepository.findAll().size();

        // Create the ChannelGroup
        ChannelGroupDTO channelGroupDTO = channelGroupMapper.toDto(channelGroup);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restChannelGroupMockMvc.perform(put("/api/channel-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(channelGroupDTO)))
            .andExpect(status().isCreated());

        // Validate the ChannelGroup in the database
        List<ChannelGroup> channelGroupList = channelGroupRepository.findAll();
        assertThat(channelGroupList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteChannelGroup() throws Exception {
        // Initialize the database
        channelGroupRepository.saveAndFlush(channelGroup);
        int databaseSizeBeforeDelete = channelGroupRepository.findAll().size();

        // Get the channelGroup
        restChannelGroupMockMvc.perform(delete("/api/channel-groups/{id}", channelGroup.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ChannelGroup> channelGroupList = channelGroupRepository.findAll();
        assertThat(channelGroupList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ChannelGroup.class);
        ChannelGroup channelGroup1 = new ChannelGroup();
        channelGroup1.setId(1L);
        ChannelGroup channelGroup2 = new ChannelGroup();
        channelGroup2.setId(channelGroup1.getId());
        assertThat(channelGroup1).isEqualTo(channelGroup2);
        channelGroup2.setId(2L);
        assertThat(channelGroup1).isNotEqualTo(channelGroup2);
        channelGroup1.setId(null);
        assertThat(channelGroup1).isNotEqualTo(channelGroup2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ChannelGroupDTO.class);
        ChannelGroupDTO channelGroupDTO1 = new ChannelGroupDTO();
        channelGroupDTO1.setId(1L);
        ChannelGroupDTO channelGroupDTO2 = new ChannelGroupDTO();
        assertThat(channelGroupDTO1).isNotEqualTo(channelGroupDTO2);
        channelGroupDTO2.setId(channelGroupDTO1.getId());
        assertThat(channelGroupDTO1).isEqualTo(channelGroupDTO2);
        channelGroupDTO2.setId(2L);
        assertThat(channelGroupDTO1).isNotEqualTo(channelGroupDTO2);
        channelGroupDTO1.setId(null);
        assertThat(channelGroupDTO1).isNotEqualTo(channelGroupDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(channelGroupMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(channelGroupMapper.fromId(null)).isNull();
    }
}
