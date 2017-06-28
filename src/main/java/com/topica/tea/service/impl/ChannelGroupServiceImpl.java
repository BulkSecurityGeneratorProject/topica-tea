package com.topica.tea.service.impl;

import com.topica.tea.service.ChannelGroupService;
import com.topica.tea.domain.ChannelGroup;
import com.topica.tea.repository.ChannelGroupRepository;
import com.topica.tea.service.dto.ChannelGroupDTO;
import com.topica.tea.service.mapper.ChannelGroupMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing ChannelGroup.
 */
@Service
@Transactional
public class ChannelGroupServiceImpl implements ChannelGroupService{

    private final Logger log = LoggerFactory.getLogger(ChannelGroupServiceImpl.class);

    private final ChannelGroupRepository channelGroupRepository;

    private final ChannelGroupMapper channelGroupMapper;

    public ChannelGroupServiceImpl(ChannelGroupRepository channelGroupRepository, ChannelGroupMapper channelGroupMapper) {
        this.channelGroupRepository = channelGroupRepository;
        this.channelGroupMapper = channelGroupMapper;
    }

    /**
     * Save a channelGroup.
     *
     * @param channelGroupDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ChannelGroupDTO save(ChannelGroupDTO channelGroupDTO) {
        log.debug("Request to save ChannelGroup : {}", channelGroupDTO);
        ChannelGroup channelGroup = channelGroupMapper.toEntity(channelGroupDTO);
        channelGroup = channelGroupRepository.save(channelGroup);
        return channelGroupMapper.toDto(channelGroup);
    }

    /**
     *  Get all the channelGroups.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ChannelGroupDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ChannelGroups");
        return channelGroupRepository.findAll(pageable)
            .map(channelGroupMapper::toDto);
    }

    /**
     *  Get one channelGroup by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public ChannelGroupDTO findOne(Long id) {
        log.debug("Request to get ChannelGroup : {}", id);
        ChannelGroup channelGroup = channelGroupRepository.findOne(id);
        return channelGroupMapper.toDto(channelGroup);
    }

    /**
     *  Delete the  channelGroup by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ChannelGroup : {}", id);
        channelGroupRepository.delete(id);
    }
}
