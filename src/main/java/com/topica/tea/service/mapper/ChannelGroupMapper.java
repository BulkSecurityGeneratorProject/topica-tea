package com.topica.tea.service.mapper;

import com.topica.tea.domain.*;
import com.topica.tea.service.dto.ChannelGroupDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ChannelGroup and its DTO ChannelGroupDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ChannelGroupMapper extends EntityMapper <ChannelGroupDTO, ChannelGroup> {
    
    
    default ChannelGroup fromId(Long id) {
        if (id == null) {
            return null;
        }
        ChannelGroup channelGroup = new ChannelGroup();
        channelGroup.setId(id);
        return channelGroup;
    }
}
