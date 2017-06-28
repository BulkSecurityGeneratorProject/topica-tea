package com.topica.tea.service;

import com.topica.tea.service.dto.ChannelGroupDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing ChannelGroup.
 */
public interface ChannelGroupService {

    /**
     * Save a channelGroup.
     *
     * @param channelGroupDTO the entity to save
     * @return the persisted entity
     */
    ChannelGroupDTO save(ChannelGroupDTO channelGroupDTO);

    /**
     *  Get all the channelGroups.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<ChannelGroupDTO> findAll(Pageable pageable);

    /**
     *  Get the "id" channelGroup.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    ChannelGroupDTO findOne(Long id);

    /**
     *  Delete the "id" channelGroup.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
