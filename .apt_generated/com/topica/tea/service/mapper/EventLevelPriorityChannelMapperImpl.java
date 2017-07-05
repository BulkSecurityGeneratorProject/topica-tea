package com.topica.tea.service.mapper;

import com.topica.tea.domain.EventLevelPriorityChannel;
import com.topica.tea.service.dto.EventLevelPriorityChannelDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2017-07-04T09:16:33+0700",
    comments = "version: 1.1.0.Final, compiler: Eclipse JDT (IDE) 3.12.3.v20170228-1205, environment: Java 1.8.0_101 (Oracle Corporation)"
)
@Component
public class EventLevelPriorityChannelMapperImpl implements EventLevelPriorityChannelMapper {

    @Override
    public EventLevelPriorityChannel toEntity(EventLevelPriorityChannelDTO dto) {
        if ( dto == null ) {
            return null;
        }

        EventLevelPriorityChannel eventLevelPriorityChannel = new EventLevelPriorityChannel();

        eventLevelPriorityChannel.setId( dto.getId() );
        eventLevelPriorityChannel.setIsMeatContent( dto.isIsMeatContent() );
        eventLevelPriorityChannel.setEventLevel( dto.getEventLevel() );

        return eventLevelPriorityChannel;
    }

    @Override
    public EventLevelPriorityChannelDTO toDto(EventLevelPriorityChannel entity) {
        if ( entity == null ) {
            return null;
        }

        EventLevelPriorityChannelDTO eventLevelPriorityChannelDTO = new EventLevelPriorityChannelDTO();

        eventLevelPriorityChannelDTO.setId( entity.getId() );
        eventLevelPriorityChannelDTO.setIsMeatContent( entity.isIsMeatContent() );
        eventLevelPriorityChannelDTO.setEventLevel( entity.getEventLevel() );

        return eventLevelPriorityChannelDTO;
    }

    @Override
    public List<EventLevelPriorityChannel> toEntity(List<EventLevelPriorityChannelDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<EventLevelPriorityChannel> list = new ArrayList<EventLevelPriorityChannel>();
        for ( EventLevelPriorityChannelDTO eventLevelPriorityChannelDTO : dtoList ) {
            list.add( toEntity( eventLevelPriorityChannelDTO ) );
        }

        return list;
    }

    @Override
    public List<EventLevelPriorityChannelDTO> toDto(List<EventLevelPriorityChannel> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<EventLevelPriorityChannelDTO> list = new ArrayList<EventLevelPriorityChannelDTO>();
        for ( EventLevelPriorityChannel eventLevelPriorityChannel : entityList ) {
            list.add( toDto( eventLevelPriorityChannel ) );
        }

        return list;
    }
}
