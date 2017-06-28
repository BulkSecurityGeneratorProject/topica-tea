package com.topica.tea.service.mapper;

import com.topica.tea.domain.*;
import com.topica.tea.service.dto.EventLevelPriorityGroupDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity EventLevelPriorityGroup and its DTO EventLevelPriorityGroupDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EventLevelPriorityGroupMapper extends EntityMapper <EventLevelPriorityGroupDTO, EventLevelPriorityGroup> {
    
    
    default EventLevelPriorityGroup fromId(Long id) {
        if (id == null) {
            return null;
        }
        EventLevelPriorityGroup eventLevelPriorityGroup = new EventLevelPriorityGroup();
        eventLevelPriorityGroup.setId(id);
        return eventLevelPriorityGroup;
    }
}
