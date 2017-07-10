package com.topica.tea.service.impl;

import com.topica.tea.service.AdsTypeService;
import com.topica.tea.domain.AdsType;
import com.topica.tea.repository.AdsTypeRepository;
import com.topica.tea.service.dto.AdsTypeDTO;
import com.topica.tea.service.mapper.AdsTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing AdsType.
 */
@Service
@Transactional
public class AdsTypeServiceImpl implements AdsTypeService{

    private final Logger log = LoggerFactory.getLogger(AdsTypeServiceImpl.class);

    private final AdsTypeRepository adsTypeRepository;

    private final AdsTypeMapper adsTypeMapper;

    public AdsTypeServiceImpl(AdsTypeRepository adsTypeRepository, AdsTypeMapper adsTypeMapper) {
        this.adsTypeRepository = adsTypeRepository;
        this.adsTypeMapper = adsTypeMapper;
    }

    /**
     * Save a adsType.
     *
     * @param adsTypeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public AdsTypeDTO save(AdsTypeDTO adsTypeDTO) {
        log.debug("Request to save AdsType : {}", adsTypeDTO);
        AdsType adsType = adsTypeMapper.toEntity(adsTypeDTO);
        adsType = adsTypeRepository.save(adsType);
        return adsTypeMapper.toDto(adsType);
    }

    /**
     *  Get all the adsTypes.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AdsTypeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AdsTypes");
        return adsTypeRepository.findAll(pageable)
            .map(adsTypeMapper::toDto);
    }

    /**
     *  Get one adsType by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public AdsTypeDTO findOne(Long id) {
        log.debug("Request to get AdsType : {}", id);
        AdsType adsType = adsTypeRepository.findOne(id);
        return adsTypeMapper.toDto(adsType);
    }

    /**
     *  Delete the  adsType by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AdsType : {}", id);
        adsTypeRepository.delete(id);
    }
}
