package com.topica.tea.service.impl;

import com.topica.tea.service.FanpageService;
import com.topica.tea.domain.Fanpage;
import com.topica.tea.repository.FanpageRepository;
import com.topica.tea.service.dto.FanpageDTO;
import com.topica.tea.service.mapper.FanpageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Fanpage.
 */
@Service
@Transactional
public class FanpageServiceImpl implements FanpageService{

    private final Logger log = LoggerFactory.getLogger(FanpageServiceImpl.class);

    private final FanpageRepository fanpageRepository;

    private final FanpageMapper fanpageMapper;

    public FanpageServiceImpl(FanpageRepository fanpageRepository, FanpageMapper fanpageMapper) {
        this.fanpageRepository = fanpageRepository;
        this.fanpageMapper = fanpageMapper;
    }

    /**
     * Save a fanpage.
     *
     * @param fanpageDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public FanpageDTO save(FanpageDTO fanpageDTO) {
        log.debug("Request to save Fanpage : {}", fanpageDTO);
        Fanpage fanpage = fanpageMapper.toEntity(fanpageDTO);
        fanpage = fanpageRepository.save(fanpage);
        return fanpageMapper.toDto(fanpage);
    }

    /**
     *  Get all the fanpages.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<FanpageDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Fanpages");
        return fanpageRepository.findAll(pageable)
            .map(fanpageMapper::toDto);
    }

    /**
     *  Get one fanpage by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public FanpageDTO findOne(Long id) {
        log.debug("Request to get Fanpage : {}", id);
        Fanpage fanpage = fanpageRepository.findOne(id);
        return fanpageMapper.toDto(fanpage);
    }

    /**
     *  Delete the  fanpage by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Fanpage : {}", id);
        fanpageRepository.delete(id);
    }
}
