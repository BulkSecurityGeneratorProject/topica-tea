package com.topica.tea.service;

import com.topica.tea.service.dto.ChannelProductDTO;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing ChannelProduct.
 */
public interface ChannelProductService {

    /**
     * Save a channelProduct.
     *
     * @param channelProductDTO the entity to save
     * @return the persisted entity
     */
    ChannelProductDTO save(ChannelProductDTO channelProductDTO);

    List<ChannelProductDTO> findAll();
    
    /**
     *  Get all the channelProducts.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<ChannelProductDTO> findAll(Pageable pageable);

    /**
     *  Get the "id" channelProduct.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    ChannelProductDTO findOne(Long id);

    /**
     *  Delete the "id" channelProduct.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
