package com.topica.tea.service.impl;

import com.topica.tea.service.ProductHtmlTemplateService;
import com.topica.tea.domain.ProductHtmlTemplate;
import com.topica.tea.repository.ProductHtmlTemplateRepository;
import com.topica.tea.service.dto.ProductHtmlTemplateDTO;
import com.topica.tea.service.mapper.ProductHtmlTemplateMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing ProductHtmlTemplate.
 */
@Service
@Transactional
public class ProductHtmlTemplateServiceImpl implements ProductHtmlTemplateService{

    private final Logger log = LoggerFactory.getLogger(ProductHtmlTemplateServiceImpl.class);

    private final ProductHtmlTemplateRepository productHtmlTemplateRepository;

    private final ProductHtmlTemplateMapper productHtmlTemplateMapper;

    public ProductHtmlTemplateServiceImpl(ProductHtmlTemplateRepository productHtmlTemplateRepository, ProductHtmlTemplateMapper productHtmlTemplateMapper) {
        this.productHtmlTemplateRepository = productHtmlTemplateRepository;
        this.productHtmlTemplateMapper = productHtmlTemplateMapper;
    }

    /**
     * Save a productHtmlTemplate.
     *
     * @param productHtmlTemplateDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ProductHtmlTemplateDTO save(ProductHtmlTemplateDTO productHtmlTemplateDTO) {
        log.debug("Request to save ProductHtmlTemplate : {}", productHtmlTemplateDTO);
        ProductHtmlTemplate productHtmlTemplate = productHtmlTemplateMapper.toEntity(productHtmlTemplateDTO);
        productHtmlTemplate = productHtmlTemplateRepository.save(productHtmlTemplate);
        return productHtmlTemplateMapper.toDto(productHtmlTemplate);
    }

    /**
     *  Get all the productHtmlTemplates.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ProductHtmlTemplateDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ProductHtmlTemplates");
        return productHtmlTemplateRepository.findAll(pageable)
            .map(productHtmlTemplateMapper::toDto);
    }

    /**
     *  Get one productHtmlTemplate by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public ProductHtmlTemplateDTO findOne(Long id) {
        log.debug("Request to get ProductHtmlTemplate : {}", id);
        ProductHtmlTemplate productHtmlTemplate = productHtmlTemplateRepository.findOne(id);
        return productHtmlTemplateMapper.toDto(productHtmlTemplate);
    }

    /**
     *  Delete the  productHtmlTemplate by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ProductHtmlTemplate : {}", id);
        productHtmlTemplateRepository.delete(id);
    }

	@Override
	public ProductHtmlTemplateDTO findOneByProductIdAndHtmlTemplateId(Long productId, Long htmlTemplateId) {
		log.debug("Request to findOneByProductIdAndHtmlTemplateId : productId {}, templateId {}", productId, htmlTemplateId);
		ProductHtmlTemplate productHtmlTemplate = productHtmlTemplateRepository.findOneByProductIdAndHtmlTemplateId(productId, htmlTemplateId);
		return productHtmlTemplateMapper.toDto(productHtmlTemplate);
	}

	@Override
	public ProductHtmlTemplateDTO findOneByChannelProductIdAndHtmlTemplateId(Long channelProductId,
			Long htmlTemplateId) {
		log.debug("Request to findOneByChannelProductIdAndHtmlTemplateId : channelProductId {}, templateId {}", channelProductId, htmlTemplateId);
		ProductHtmlTemplate productHtmlTemplate = productHtmlTemplateRepository.findOneByChannelProductIdAndHtmlTemplateId(channelProductId, htmlTemplateId);
		return productHtmlTemplateMapper.toDto(productHtmlTemplate);
	}
}
