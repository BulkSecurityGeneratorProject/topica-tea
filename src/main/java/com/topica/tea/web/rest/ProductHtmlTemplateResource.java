package com.topica.tea.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.topica.tea.service.HtmlTemplateService;
import com.topica.tea.service.ProductHtmlTemplateService;
import com.topica.tea.service.ProductService;
import com.topica.tea.web.rest.util.HeaderUtil;
import com.topica.tea.web.rest.util.PaginationUtil;
import com.topica.tea.service.dto.HtmlTemplateDTO;
import com.topica.tea.service.dto.ProductDTO;
import com.topica.tea.service.dto.ProductHtmlTemplateDTO;
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
 * REST controller for managing ProductHtmlTemplate.
 */
@RestController
@RequestMapping("/api")
public class ProductHtmlTemplateResource {

    private final Logger log = LoggerFactory.getLogger(ProductHtmlTemplateResource.class);

    private static final String ENTITY_NAME = "productHtmlTemplate";

    private final ProductHtmlTemplateService productHtmlTemplateService;
    
    private final ProductService productService;
    
    private final HtmlTemplateService htmlTemplateService;

    public ProductHtmlTemplateResource(ProductHtmlTemplateService productHtmlTemplateService, ProductService productService
    		, HtmlTemplateService htmlTemplateService) {
        this.productHtmlTemplateService = productHtmlTemplateService;
        this.productService = productService;
        this.htmlTemplateService = htmlTemplateService;
    }

    /**
     * POST  /product-html-templates : Create a new productHtmlTemplate.
     *
     * @param productHtmlTemplateDTO the productHtmlTemplateDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new productHtmlTemplateDTO, or with status 400 (Bad Request) if the productHtmlTemplate has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/product-html-templates")
    @Timed
    public ResponseEntity<ProductHtmlTemplateDTO> createProductHtmlTemplate(@RequestBody ProductHtmlTemplateDTO productHtmlTemplateDTO) throws URISyntaxException {
        log.debug("REST request to save ProductHtmlTemplate : {}", productHtmlTemplateDTO);
        if (productHtmlTemplateDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new productHtmlTemplate cannot already have an ID")).body(null);
        }
        ProductHtmlTemplateDTO result = productHtmlTemplateService.save(productHtmlTemplateDTO);
        return ResponseEntity.created(new URI("/api/product-html-templates/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /product-html-templates : Updates an existing productHtmlTemplate.
     *
     * @param productHtmlTemplateDTO the productHtmlTemplateDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated productHtmlTemplateDTO,
     * or with status 400 (Bad Request) if the productHtmlTemplateDTO is not valid,
     * or with status 500 (Internal Server Error) if the productHtmlTemplateDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/product-html-templates")
    @Timed
    public ResponseEntity<ProductHtmlTemplateDTO> updateProductHtmlTemplate(@RequestBody ProductHtmlTemplateDTO productHtmlTemplateDTO) throws URISyntaxException {
        log.debug("REST request to update ProductHtmlTemplate : {}", productHtmlTemplateDTO);
        if (productHtmlTemplateDTO.getId() == null) {
            return createProductHtmlTemplate(productHtmlTemplateDTO);
        }
        ProductHtmlTemplateDTO result = productHtmlTemplateService.save(productHtmlTemplateDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, productHtmlTemplateDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /product-html-templates : get all the productHtmlTemplates.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of productHtmlTemplates in body
     */
    @GetMapping("/product-html-templates")
    @Timed
    public ResponseEntity<List<ProductHtmlTemplateDTO>> getAllProductHtmlTemplates(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of ProductHtmlTemplates");
        Page<ProductHtmlTemplateDTO> page = productHtmlTemplateService.findAll(pageable);
        
        // Retrive product and html template
        if (null != page.getContent()) {
        	for (ProductHtmlTemplateDTO productHtmlTemplateDTO : page) {
				ProductDTO pDTO = productService.findOne(productHtmlTemplateDTO.getProductId());
				HtmlTemplateDTO hDTO = htmlTemplateService.findOne(productHtmlTemplateDTO.getHtmlTemplateId());
				if (hDTO != null) {
					hDTO.setCssContent(null);
					hDTO.setTemplateContent(null);
				}
				productHtmlTemplateDTO.setProductDTO(pDTO);
				productHtmlTemplateDTO.setHtmlTemplateDTO(hDTO);
			}
        }
        
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/product-html-templates");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /product-html-templates/:id : get the "id" productHtmlTemplate.
     *
     * @param id the id of the productHtmlTemplateDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the productHtmlTemplateDTO, or with status 404 (Not Found)
     */
    @GetMapping("/product-html-templates/{id}")
    @Timed
    public ResponseEntity<ProductHtmlTemplateDTO> getProductHtmlTemplate(@PathVariable Long id) {
        log.debug("REST request to get ProductHtmlTemplate : {}", id);
        ProductHtmlTemplateDTO productHtmlTemplateDTO = productHtmlTemplateService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(productHtmlTemplateDTO));
    }

    /**
     * DELETE  /product-html-templates/:id : delete the "id" productHtmlTemplate.
     *
     * @param id the id of the productHtmlTemplateDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/product-html-templates/{id}")
    @Timed
    public ResponseEntity<Void> deleteProductHtmlTemplate(@PathVariable Long id) {
        log.debug("REST request to delete ProductHtmlTemplate : {}", id);
        productHtmlTemplateService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
