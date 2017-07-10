package com.topica.tea.service.impl;

import com.topica.tea.service.ChannelProductService;
import com.topica.tea.domain.ChannelProduct;
import com.topica.tea.repository.ChannelProductRepository;
import com.topica.tea.service.dto.ChannelProductDTO;
import com.topica.tea.service.mapper.ChannelProductMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing ChannelProduct.
 */
@Service
@Transactional
public class ChannelProductServiceImpl implements ChannelProductService{

    private final Logger log = LoggerFactory.getLogger(ChannelProductServiceImpl.class);

    private final ChannelProductRepository channelProductRepository;

    private final ChannelProductMapper channelProductMapper;

    public ChannelProductServiceImpl(ChannelProductRepository channelProductRepository, ChannelProductMapper channelProductMapper) {
        this.channelProductRepository = channelProductRepository;
        this.channelProductMapper = channelProductMapper;
    }

    /**
     * Save a channelProduct.
     *
     * @param channelProductDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ChannelProductDTO save(ChannelProductDTO channelProductDTO) {
        log.debug("Request to save ChannelProduct : {}", channelProductDTO);
        ChannelProduct channelProduct = channelProductMapper.toEntity(channelProductDTO);
        channelProduct = channelProductRepository.save(channelProduct);
        return channelProductMapper.toDto(channelProduct);
    }

    /**
     *  Get all the channelProducts.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ChannelProductDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ChannelProducts");
        return channelProductRepository.findAll(pageable)
            .map(channelProductMapper::toDto);
    }

    /**
     *  Get one channelProduct by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public ChannelProductDTO findOne(Long id) {
        log.debug("Request to get ChannelProduct : {}", id);
        ChannelProduct channelProduct = channelProductRepository.findOne(id);
        return channelProductMapper.toDto(channelProduct);
    }

    /**
     *  Delete the  channelProduct by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ChannelProduct : {}", id);
        channelProductRepository.delete(id);
    }
}
