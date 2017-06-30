package com.topica.tea.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.topica.tea.service.BrandkeyProductService;
import com.topica.tea.web.rest.util.HeaderUtil;
import com.topica.tea.web.rest.util.PaginationUtil;
import com.topica.tea.service.dto.BrandkeyProductDTO;
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

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing BrandkeyProduct.
 */
@RestController
@RequestMapping("/api")
public class BrandkeyProductResource {

    private final Logger log = LoggerFactory.getLogger(BrandkeyProductResource.class);

    private static final String ENTITY_NAME = "brandkeyProduct";

    private final BrandkeyProductService brandkeyProductService;

    public BrandkeyProductResource(BrandkeyProductService brandkeyProductService) {
        this.brandkeyProductService = brandkeyProductService;
    }

    /**
     * POST  /brandkey-products/save-all : Create all brandkeyProduct.
     *
     * @param List<brandkeyProductDTO> the list brandkeyProductDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new brandkeyProductDTO, or with status 400 (Bad Request) if the brandkeyProduct has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/brandkey-products/save-all")
    @Timed
    public ResponseEntity<List<BrandkeyProductDTO>> createAllBrandkeyProduct(@Valid @RequestBody List<BrandkeyProductDTO> brandkeyProductDTOs) throws URISyntaxException {
        log.debug("REST request to save BrandkeyProduct : {}", brandkeyProductDTOs);
        if (brandkeyProductDTOs == null || brandkeyProductDTOs.size() == 0) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "List brandkeyProduct cannot empty")).body(null);
        }
        // Remove all
        brandkeyProductService.deleteAll();
        
        // Add all new
        List<BrandkeyProductDTO> result = brandkeyProductService.saveAll(brandkeyProductDTOs);
        return ResponseEntity.created(new URI("/api/brandkey-products/save-all"))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, ""))
            .body(result);
    }
    
    /**
     * POST  /brandkey-products : Create a new brandkeyProduct.
     *
     * @param brandkeyProductDTO the brandkeyProductDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new brandkeyProductDTO, or with status 400 (Bad Request) if the brandkeyProduct has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/brandkey-products")
    @Timed
    public ResponseEntity<BrandkeyProductDTO> createBrandkeyProduct(@Valid @RequestBody BrandkeyProductDTO brandkeyProductDTO) throws URISyntaxException {
        log.debug("REST request to save BrandkeyProduct : {}", brandkeyProductDTO);
        if (brandkeyProductDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new brandkeyProduct cannot already have an ID")).body(null);
        }
        BrandkeyProductDTO result = brandkeyProductService.save(brandkeyProductDTO);
        return ResponseEntity.created(new URI("/api/brandkey-products/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /brandkey-products : Updates an existing brandkeyProduct.
     *
     * @param brandkeyProductDTO the brandkeyProductDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated brandkeyProductDTO,
     * or with status 400 (Bad Request) if the brandkeyProductDTO is not valid,
     * or with status 500 (Internal Server Error) if the brandkeyProductDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/brandkey-products")
    @Timed
    public ResponseEntity<BrandkeyProductDTO> updateBrandkeyProduct(@Valid @RequestBody BrandkeyProductDTO brandkeyProductDTO) throws URISyntaxException {
        log.debug("REST request to update BrandkeyProduct : {}", brandkeyProductDTO);
        if (brandkeyProductDTO.getId() == null) {
            return createBrandkeyProduct(brandkeyProductDTO);
        }
        BrandkeyProductDTO result = brandkeyProductService.save(brandkeyProductDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, brandkeyProductDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /brandkey-products : get all the brandkeyProducts.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of brandkeyProducts in body
     */
    @GetMapping("/brandkey-products")
    @Timed
    public ResponseEntity<List<BrandkeyProductDTO>> getAllBrandkeyProducts(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of BrandkeyProducts");
        Page<BrandkeyProductDTO> page = brandkeyProductService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/brandkey-products");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /brandkey-products/:id : get the "id" brandkeyProduct.
     *
     * @param id the id of the brandkeyProductDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the brandkeyProductDTO, or with status 404 (Not Found)
     */
    @GetMapping("/brandkey-products/{id}")
    @Timed
    public ResponseEntity<BrandkeyProductDTO> getBrandkeyProduct(@PathVariable Long id) {
        log.debug("REST request to get BrandkeyProduct : {}", id);
        BrandkeyProductDTO brandkeyProductDTO = brandkeyProductService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(brandkeyProductDTO));
    }

    /**
     * DELETE  /brandkey-products/:id : delete the "id" brandkeyProduct.
     *
     * @param id the id of the brandkeyProductDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/brandkey-products/{id}")
    @Timed
    public ResponseEntity<Void> deleteBrandkeyProduct(@PathVariable Long id) {
        log.debug("REST request to delete BrandkeyProduct : {}", id);
        brandkeyProductService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
