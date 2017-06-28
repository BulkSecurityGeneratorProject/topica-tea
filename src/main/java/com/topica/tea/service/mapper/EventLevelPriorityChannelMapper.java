package com.topica.tea.service.mapper;

import com.topica.tea.domain.*;
import com.topica.tea.service.dto.EventLevelPriorityChannelDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity EventLevelPriorityChannel and its DTO EventLevelPriorityChannelDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EventLevelPriorityChannelMapper extends EntityMapper <EventLevelPriorityChannelDTO, EventLevelPriorityChannel> {
    
    
    default EventLevelPriorityChannel fromId(Long id) {
        if (id == null) {
            return null;
        }
        EventLevelPriorityChannel eventLevelPriorityChannel = new EventLevelPriorityChannel();
        eventLevelPriorityChannel.setId(id);
        return eventLevelPriorityChannel;
    }
}
