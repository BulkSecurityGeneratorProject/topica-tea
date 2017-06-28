package com.topica.tea.web.rest;

import com.topica.tea.TopicaEventAmplifyApp;

import com.topica.tea.domain.Brandkey;
import com.topica.tea.repository.BrandkeyRepository;
import com.topica.tea.service.BrandkeyService;
import com.topica.tea.service.dto.BrandkeyDTO;
import com.topica.tea.service.mapper.BrandkeyMapper;
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

/**
 * Test class for the BrandkeyResource REST controller.
 *
 * @see BrandkeyResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TopicaEventAmplifyApp.class)
public class BrandkeyResourceIntTest {

    private static final String DEFAULT_BRANDKEY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_BRANDKEY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private BrandkeyRepository brandkeyRepository;

    @Autowired
    private BrandkeyMapper brandkeyMapper;

    @Autowired
    private BrandkeyService brandkeyService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restBrandkeyMockMvc;

    private Brandkey brandkey;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        BrandkeyResource brandkeyResource = new BrandkeyResource(brandkeyService);
        this.restBrandkeyMockMvc = MockMvcBuilders.standaloneSetup(brandkeyResource)
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
    public static Brandkey createEntity(EntityManager em) {
        Brandkey brandkey = new Brandkey()
            .brandkeyName(DEFAULT_BRANDKEY_NAME)
            .description(DEFAULT_DESCRIPTION);
        return brandkey;
    }

    @Before
    public void initTest() {
        brandkey = createEntity(em);
    }

    @Test
    @Transactional
    public void createBrandkey() throws Exception {
        int databaseSizeBeforeCreate = brandkeyRepository.findAll().size();

        // Create the Brandkey
        BrandkeyDTO brandkeyDTO = brandkeyMapper.toDto(brandkey);
        restBrandkeyMockMvc.perform(post("/api/brandkeys")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(brandkeyDTO)))
            .andExpect(status().isCreated());

        // Validate the Brandkey in the database
        List<Brandkey> brandkeyList = brandkeyRepository.findAll();
        assertThat(brandkeyList).hasSize(databaseSizeBeforeCreate + 1);
        Brandkey testBrandkey = brandkeyList.get(brandkeyList.size() - 1);
        assertThat(testBrandkey.getBrandkeyName()).isEqualTo(DEFAULT_BRANDKEY_NAME);
        assertThat(testBrandkey.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createBrandkeyWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = brandkeyRepository.findAll().size();

        // Create the Brandkey with an existing ID
        brandkey.setId(1L);
        BrandkeyDTO brandkeyDTO = brandkeyMapper.toDto(brandkey);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBrandkeyMockMvc.perform(post("/api/brandkeys")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(brandkeyDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Brandkey> brandkeyList = brandkeyRepository.findAll();
        assertThat(brandkeyList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkBrandkeyNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = brandkeyRepository.findAll().size();
        // set the field null
        brandkey.setBrandkeyName(null);

        // Create the Brandkey, which fails.
        BrandkeyDTO brandkeyDTO = brandkeyMapper.toDto(brandkey);

        restBrandkeyMockMvc.perform(post("/api/brandkeys")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(brandkeyDTO)))
            .andExpect(status().isBadRequest());

        List<Brandkey> brandkeyList = brandkeyRepository.findAll();
        assertThat(brandkeyList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllBrandkeys() throws Exception {
        // Initialize the database
        brandkeyRepository.saveAndFlush(brandkey);

        // Get all the brandkeyList
        restBrandkeyMockMvc.perform(get("/api/brandkeys?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(brandkey.getId().intValue())))
            .andExpect(jsonPath("$.[*].brandkeyName").value(hasItem(DEFAULT_BRANDKEY_NAME.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));
    }

    @Test
    @Transactional
    public void getBrandkey() throws Exception {
        // Initialize the database
        brandkeyRepository.saveAndFlush(brandkey);

        // Get the brandkey
        restBrandkeyMockMvc.perform(get("/api/brandkeys/{id}", brandkey.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(brandkey.getId().intValue()))
            .andExpect(jsonPath("$.brandkeyName").value(DEFAULT_BRANDKEY_NAME.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingBrandkey() throws Exception {
        // Get the brandkey
        restBrandkeyMockMvc.perform(get("/api/brandkeys/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBrandkey() throws Exception {
        // Initialize the database
        brandkeyRepository.saveAndFlush(brandkey);
        int databaseSizeBeforeUpdate = brandkeyRepository.findAll().size();

        // Update the brandkey
        Brandkey updatedBrandkey = brandkeyRepository.findOne(brandkey.getId());
        updatedBrandkey
            .brandkeyName(UPDATED_BRANDKEY_NAME)
            .description(UPDATED_DESCRIPTION);
        BrandkeyDTO brandkeyDTO = brandkeyMapper.toDto(updatedBrandkey);

        restBrandkeyMockMvc.perform(put("/api/brandkeys")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(brandkeyDTO)))
            .andExpect(status().isOk());

        // Validate the Brandkey in the database
        List<Brandkey> brandkeyList = brandkeyRepository.findAll();
        assertThat(brandkeyList).hasSize(databaseSizeBeforeUpdate);
        Brandkey testBrandkey = brandkeyList.get(brandkeyList.size() - 1);
        assertThat(testBrandkey.getBrandkeyName()).isEqualTo(UPDATED_BRANDKEY_NAME);
        assertThat(testBrandkey.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingBrandkey() throws Exception {
        int databaseSizeBeforeUpdate = brandkeyRepository.findAll().size();

        // Create the Brandkey
        BrandkeyDTO brandkeyDTO = brandkeyMapper.toDto(brandkey);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restBrandkeyMockMvc.perform(put("/api/brandkeys")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(brandkeyDTO)))
            .andExpect(status().isCreated());

        // Validate the Brandkey in the database
        List<Brandkey> brandkeyList = brandkeyRepository.findAll();
        assertThat(brandkeyList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteBrandkey() throws Exception {
        // Initialize the database
        brandkeyRepository.saveAndFlush(brandkey);
        int databaseSizeBeforeDelete = brandkeyRepository.findAll().size();

        // Get the brandkey
        restBrandkeyMockMvc.perform(delete("/api/brandkeys/{id}", brandkey.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Brandkey> brandkeyList = brandkeyRepository.findAll();
        assertThat(brandkeyList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Brandkey.class);
        Brandkey brandkey1 = new Brandkey();
        brandkey1.setId(1L);
        Brandkey brandkey2 = new Brandkey();
        brandkey2.setId(brandkey1.getId());
        assertThat(brandkey1).isEqualTo(brandkey2);
        brandkey2.setId(2L);
        assertThat(brandkey1).isNotEqualTo(brandkey2);
        brandkey1.setId(null);
        assertThat(brandkey1).isNotEqualTo(brandkey2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BrandkeyDTO.class);
        BrandkeyDTO brandkeyDTO1 = new BrandkeyDTO();
        brandkeyDTO1.setId(1L);
        BrandkeyDTO brandkeyDTO2 = new BrandkeyDTO();
        assertThat(brandkeyDTO1).isNotEqualTo(brandkeyDTO2);
        brandkeyDTO2.setId(brandkeyDTO1.getId());
        assertThat(brandkeyDTO1).isEqualTo(brandkeyDTO2);
        brandkeyDTO2.setId(2L);
        assertThat(brandkeyDTO1).isNotEqualTo(brandkeyDTO2);
        brandkeyDTO1.setId(null);
        assertThat(brandkeyDTO1).isNotEqualTo(brandkeyDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(brandkeyMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(brandkeyMapper.fromId(null)).isNull();
    }
}
