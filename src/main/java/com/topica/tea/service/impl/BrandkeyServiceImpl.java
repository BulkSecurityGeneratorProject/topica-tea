package com.topica.tea.service.impl;

import com.topica.tea.service.BrandkeyService;
import com.topica.tea.domain.Brandkey;
import com.topica.tea.repository.BrandkeyRepository;
import com.topica.tea.service.dto.BrandkeyDTO;
import com.topica.tea.service.mapper.BrandkeyMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Brandkey.
 */
@Service
@Transactional
public class BrandkeyServiceImpl implements BrandkeyService{

    private final Logger log = LoggerFactory.getLogger(BrandkeyServiceImpl.class);

    private final BrandkeyRepository brandkeyRepository;

    private final BrandkeyMapper brandkeyMapper;

    public BrandkeyServiceImpl(BrandkeyRepository brandkeyRepository, BrandkeyMapper brandkeyMapper) {
        this.brandkeyRepository = brandkeyRepository;
        this.brandkeyMapper = brandkeyMapper;
    }

    /**
     * Save a brandkey.
     *
     * @param brandkeyDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public BrandkeyDTO save(BrandkeyDTO brandkeyDTO) {
        log.debug("Request to save Brandkey : {}", brandkeyDTO);
        Brandkey brandkey = brandkeyMapper.toEntity(brandkeyDTO);
        brandkey = brandkeyRepository.save(brandkey);
        return brandkeyMapper.toDto(brandkey);
    }

    /**
     *  Get all the brandkeys.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<BrandkeyDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Brandkeys");
        return brandkeyRepository.findAll(pageable)
            .map(brandkeyMapper::toDto);
    }

    /**
     *  Get one brandkey by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public BrandkeyDTO findOne(Long id) {
        log.debug("Request to get Brandkey : {}", id);
        Brandkey brandkey = brandkeyRepository.findOne(id);
        return brandkeyMapper.toDto(brandkey);
    }

    /**
     *  Delete the  brandkey by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Brandkey : {}", id);
        brandkeyRepository.delete(id);
    }
}
