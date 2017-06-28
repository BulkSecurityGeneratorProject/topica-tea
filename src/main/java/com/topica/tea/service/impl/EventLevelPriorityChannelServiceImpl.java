package com.topica.tea.service.impl;

import com.topica.tea.service.EventLevelPriorityChannelService;
import com.topica.tea.domain.EventLevelPriorityChannel;
import com.topica.tea.repository.EventLevelPriorityChannelRepository;
import com.topica.tea.service.dto.EventLevelPriorityChannelDTO;
import com.topica.tea.service.mapper.EventLevelPriorityChannelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing EventLevelPriorityChannel.
 */
@Service
@Transactional
public class EventLevelPriorityChannelServiceImpl implements EventLevelPriorityChannelService{

    private final Logger log = LoggerFactory.getLogger(EventLevelPriorityChannelServiceImpl.class);

    private final EventLevelPriorityChannelRepository eventLevelPriorityChannelRepository;

    private final EventLevelPriorityChannelMapper eventLevelPriorityChannelMapper;

    public EventLevelPriorityChannelServiceImpl(EventLevelPriorityChannelRepository eventLevelPriorityChannelRepository, EventLevelPriorityChannelMapper eventLevelPriorityChannelMapper) {
        this.eventLevelPriorityChannelRepository = eventLevelPriorityChannelRepository;
        this.eventLevelPriorityChannelMapper = eventLevelPriorityChannelMapper;
    }

    /**
     * Save a eventLevelPriorityChannel.
     *
     * @param eventLevelPriorityChannelDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public EventLevelPriorityChannelDTO save(EventLevelPriorityChannelDTO eventLevelPriorityChannelDTO) {
        log.debug("Request to save EventLevelPriorityChannel : {}", eventLevelPriorityChannelDTO);
        EventLevelPriorityChannel eventLevelPriorityChannel = eventLevelPriorityChannelMapper.toEntity(eventLevelPriorityChannelDTO);
        eventLevelPriorityChannel = eventLevelPriorityChannelRepository.save(eventLevelPriorityChannel);
        return eventLevelPriorityChannelMapper.toDto(eventLevelPriorityChannel);
    }

    /**
     *  Get all the eventLevelPriorityChannels.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EventLevelPriorityChannelDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EventLevelPriorityChannels");
        return eventLevelPriorityChannelRepository.findAll(pageable)
            .map(eventLevelPriorityChannelMapper::toDto);
    }

    /**
     *  Get one eventLevelPriorityChannel by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public EventLevelPriorityChannelDTO findOne(Long id) {
        log.debug("Request to get EventLevelPriorityChannel : {}", id);
        EventLevelPriorityChannel eventLevelPriorityChannel = eventLevelPriorityChannelRepository.findOne(id);
        return eventLevelPriorityChannelMapper.toDto(eventLevelPriorityChannel);
    }

    /**
     *  Delete the  eventLevelPriorityChannel by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EventLevelPriorityChannel : {}", id);
        eventLevelPriorityChannelRepository.delete(id);
    }
}
