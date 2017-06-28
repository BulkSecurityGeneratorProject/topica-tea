package com.topica.tea.web.rest;

import com.topica.tea.TopicaEventAmplifyApp;

import com.topica.tea.domain.BrandkeyProduct;
import com.topica.tea.repository.BrandkeyProductRepository;
import com.topica.tea.service.BrandkeyProductService;
import com.topica.tea.service.dto.BrandkeyProductDTO;
import com.topica.tea.service.mapper.BrandkeyProductMapper;
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
 * Test class for the BrandkeyProductResource REST controller.
 *
 * @see BrandkeyProductResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TopicaEventAmplifyApp.class)
public class BrandkeyProductResourceIntTest {

    @Autowired
    private BrandkeyProductRepository brandkeyProductRepository;

    @Autowired
    private BrandkeyProductMapper brandkeyProductMapper;

    @Autowired
    private BrandkeyProductService brandkeyProductService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restBrandkeyProductMockMvc;

    private BrandkeyProduct brandkeyProduct;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        BrandkeyProductResource brandkeyProductResource = new BrandkeyProductResource(brandkeyProductService);
        this.restBrandkeyProductMockMvc = MockMvcBuilders.standaloneSetup(brandkeyProductResource)
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
    public static BrandkeyProduct createEntity(EntityManager em) {
        BrandkeyProduct brandkeyProduct = new BrandkeyProduct();
        return brandkeyProduct;
    }

    @Before
    public void initTest() {
        brandkeyProduct = createEntity(em);
    }

    @Test
    @Transactional
    public void createBrandkeyProduct() throws Exception {
        int databaseSizeBeforeCreate = brandkeyProductRepository.findAll().size();

        // Create the BrandkeyProduct
        BrandkeyProductDTO brandkeyProductDTO = brandkeyProductMapper.toDto(brandkeyProduct);
        restBrandkeyProductMockMvc.perform(post("/api/brandkey-products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(brandkeyProductDTO)))
            .andExpect(status().isCreated());

        // Validate the BrandkeyProduct in the database
        List<BrandkeyProduct> brandkeyProductList = brandkeyProductRepository.findAll();
        assertThat(brandkeyProductList).hasSize(databaseSizeBeforeCreate + 1);
        BrandkeyProduct testBrandkeyProduct = brandkeyProductList.get(brandkeyProductList.size() - 1);
    }

    @Test
    @Transactional
    public void createBrandkeyProductWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = brandkeyProductRepository.findAll().size();

        // Create the BrandkeyProduct with an existing ID
        brandkeyProduct.setId(1L);
        BrandkeyProductDTO brandkeyProductDTO = brandkeyProductMapper.toDto(brandkeyProduct);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBrandkeyProductMockMvc.perform(post("/api/brandkey-products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(brandkeyProductDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<BrandkeyProduct> brandkeyProductList = brandkeyProductRepository.findAll();
        assertThat(brandkeyProductList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllBrandkeyProducts() throws Exception {
        // Initialize the database
        brandkeyProductRepository.saveAndFlush(brandkeyProduct);

        // Get all the brandkeyProductList
        restBrandkeyProductMockMvc.perform(get("/api/brandkey-products?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(brandkeyProduct.getId().intValue())));
    }

    @Test
    @Transactional
    public void getBrandkeyProduct() throws Exception {
        // Initialize the database
        brandkeyProductRepository.saveAndFlush(brandkeyProduct);

        // Get the brandkeyProduct
        restBrandkeyProductMockMvc.perform(get("/api/brandkey-products/{id}", brandkeyProduct.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(brandkeyProduct.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingBrandkeyProduct() throws Exception {
        // Get the brandkeyProduct
        restBrandkeyProductMockMvc.perform(get("/api/brandkey-products/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBrandkeyProduct() throws Exception {
        // Initialize the database
        brandkeyProductRepository.saveAndFlush(brandkeyProduct);
        int databaseSizeBeforeUpdate = brandkeyProductRepository.findAll().size();

        // Update the brandkeyProduct
        BrandkeyProduct updatedBrandkeyProduct = brandkeyProductRepository.findOne(brandkeyProduct.getId());
        BrandkeyProductDTO brandkeyProductDTO = brandkeyProductMapper.toDto(updatedBrandkeyProduct);

        restBrandkeyProductMockMvc.perform(put("/api/brandkey-products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(brandkeyProductDTO)))
            .andExpect(status().isOk());

        // Validate the BrandkeyProduct in the database
        List<BrandkeyProduct> brandkeyProductList = brandkeyProductRepository.findAll();
        assertThat(brandkeyProductList).hasSize(databaseSizeBeforeUpdate);
        BrandkeyProduct testBrandkeyProduct = brandkeyProductList.get(brandkeyProductList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingBrandkeyProduct() throws Exception {
        int databaseSizeBeforeUpdate = brandkeyProductRepository.findAll().size();

        // Create the BrandkeyProduct
        BrandkeyProductDTO brandkeyProductDTO = brandkeyProductMapper.toDto(brandkeyProduct);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restBrandkeyProductMockMvc.perform(put("/api/brandkey-products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(brandkeyProductDTO)))
            .andExpect(status().isCreated());

        // Validate the BrandkeyProduct in the database
        List<BrandkeyProduct> brandkeyProductList = brandkeyProductRepository.findAll();
        assertThat(brandkeyProductList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteBrandkeyProduct() throws Exception {
        // Initialize the database
        brandkeyProductRepository.saveAndFlush(brandkeyProduct);
        int databaseSizeBeforeDelete = brandkeyProductRepository.findAll().size();

        // Get the brandkeyProduct
        restBrandkeyProductMockMvc.perform(delete("/api/brandkey-products/{id}", brandkeyProduct.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<BrandkeyProduct> brandkeyProductList = brandkeyProductRepository.findAll();
        assertThat(brandkeyProductList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BrandkeyProduct.class);
        BrandkeyProduct brandkeyProduct1 = new BrandkeyProduct();
        brandkeyProduct1.setId(1L);
        BrandkeyProduct brandkeyProduct2 = new BrandkeyProduct();
        brandkeyProduct2.setId(brandkeyProduct1.getId());
        assertThat(brandkeyProduct1).isEqualTo(brandkeyProduct2);
        brandkeyProduct2.setId(2L);
        assertThat(brandkeyProduct1).isNotEqualTo(brandkeyProduct2);
        brandkeyProduct1.setId(null);
        assertThat(brandkeyProduct1).isNotEqualTo(brandkeyProduct2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BrandkeyProductDTO.class);
        BrandkeyProductDTO brandkeyProductDTO1 = new BrandkeyProductDTO();
        brandkeyProductDTO1.setId(1L);
        BrandkeyProductDTO brandkeyProductDTO2 = new BrandkeyProductDTO();
        assertThat(brandkeyProductDTO1).isNotEqualTo(brandkeyProductDTO2);
        brandkeyProductDTO2.setId(brandkeyProductDTO1.getId());
        assertThat(brandkeyProductDTO1).isEqualTo(brandkeyProductDTO2);
        brandkeyProductDTO2.setId(2L);
        assertThat(brandkeyProductDTO1).isNotEqualTo(brandkeyProductDTO2);
        brandkeyProductDTO1.setId(null);
        assertThat(brandkeyProductDTO1).isNotEqualTo(brandkeyProductDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(brandkeyProductMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(brandkeyProductMapper.fromId(null)).isNull();
    }
}
