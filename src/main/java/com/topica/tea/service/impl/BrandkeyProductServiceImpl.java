package com.topica.tea.service.impl;

import com.topica.tea.service.BrandkeyProductService;
import com.topica.tea.domain.BrandkeyProduct;
import com.topica.tea.repository.BrandkeyProductRepository;
import com.topica.tea.service.dto.BrandkeyProductDTO;
import com.topica.tea.service.mapper.BrandkeyProductMapper;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing BrandkeyProduct.
 */
@Service
@Transactional
public class BrandkeyProductServiceImpl implements BrandkeyProductService{

    private final Logger log = LoggerFactory.getLogger(BrandkeyProductServiceImpl.class);

    private final BrandkeyProductRepository brandkeyProductRepository;

    private final BrandkeyProductMapper brandkeyProductMapper;

    public BrandkeyProductServiceImpl(BrandkeyProductRepository brandkeyProductRepository, BrandkeyProductMapper brandkeyProductMapper) {
        this.brandkeyProductRepository = brandkeyProductRepository;
        this.brandkeyProductMapper = brandkeyProductMapper;
    }

    /**
     * Save a brandkeyProduct.
     *
     * @param brandkeyProductDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public BrandkeyProductDTO save(BrandkeyProductDTO brandkeyProductDTO) {
        log.debug("Request to save BrandkeyProduct : {}", brandkeyProductDTO);
        BrandkeyProduct brandkeyProduct = brandkeyProductMapper.toEntity(brandkeyProductDTO);
        brandkeyProduct = brandkeyProductRepository.save(brandkeyProduct);
        return brandkeyProductMapper.toDto(brandkeyProduct);
    }

    /**
     *  Get all the brandkeyProducts.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<BrandkeyProductDTO> findAll(Pageable pageable) {
        log.debug("Request to get all BrandkeyProducts");
        return brandkeyProductRepository.findAll(pageable)
            .map(brandkeyProductMapper::toDto);
    }

    /**
     *  Get one brandkeyProduct by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public BrandkeyProductDTO findOne(Long id) {
        log.debug("Request to get BrandkeyProduct : {}", id);
        BrandkeyProduct brandkeyProduct = brandkeyProductRepository.findOne(id);
        return brandkeyProductMapper.toDto(brandkeyProduct);
    }

    /**
     *  Delete the  brandkeyProduct by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete BrandkeyProduct : {}", id);
        brandkeyProductRepository.delete(id);
    }

	@Override
	public List<BrandkeyProductDTO> saveAll(List<BrandkeyProductDTO> brandkeyProductDTOs) {
		log.debug("Request to saveAll list BrandkeyProduct : {}", brandkeyProductDTOs);
		
        List<BrandkeyProduct> brandkeyProducts = brandkeyProductMapper.toEntity(brandkeyProductDTOs);
        brandkeyProducts = brandkeyProductRepository.save(brandkeyProducts);
        return brandkeyProductMapper.toDto(brandkeyProducts);
	}

	@Override
	public void deleteAll() {
		log.debug("Request to deleteAll BrandkeyProducts");
		brandkeyProductRepository.deleteAll();
	}
}
