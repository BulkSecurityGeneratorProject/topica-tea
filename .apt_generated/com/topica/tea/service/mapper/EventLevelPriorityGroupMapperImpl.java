package com.topica.tea.service.mapper;

import com.topica.tea.domain.EventLevelPriorityGroup;
import com.topica.tea.service.dto.EventLevelPriorityGroupDTO;
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
public class EventLevelPriorityGroupMapperImpl implements EventLevelPriorityGroupMapper {

    @Override
    public EventLevelPriorityGroup toEntity(EventLevelPriorityGroupDTO dto) {
        if ( dto == null ) {
            return null;
        }

        EventLevelPriorityGroup eventLevelPriorityGroup = new EventLevelPriorityGroup();

        eventLevelPriorityGroup.setId( dto.getId() );
        eventLevelPriorityGroup.setIsMeatContent( dto.isIsMeatContent() );
        eventLevelPriorityGroup.setEventLevel( dto.getEventLevel() );
        eventLevelPriorityGroup.setPriorityGroup( dto.getPriorityGroup() );

        return eventLevelPriorityGroup;
    }

    @Override
    public EventLevelPriorityGroupDTO toDto(EventLevelPriorityGroup entity) {
        if ( entity == null ) {
            return null;
        }

        EventLevelPriorityGroupDTO eventLevelPriorityGroupDTO = new EventLevelPriorityGroupDTO();

        eventLevelPriorityGroupDTO.setId( entity.getId() );
        eventLevelPriorityGroupDTO.setIsMeatContent( entity.isIsMeatContent() );
        eventLevelPriorityGroupDTO.setEventLevel( entity.getEventLevel() );
        eventLevelPriorityGroupDTO.setPriorityGroup( entity.getPriorityGroup() );

        return eventLevelPriorityGroupDTO;
    }

    @Override
    public List<EventLevelPriorityGroup> toEntity(List<EventLevelPriorityGroupDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<EventLevelPriorityGroup> list = new ArrayList<EventLevelPriorityGroup>();
        for ( EventLevelPriorityGroupDTO eventLevelPriorityGroupDTO : dtoList ) {
            list.add( toEntity( eventLevelPriorityGroupDTO ) );
        }

        return list;
    }

    @Override
    public List<EventLevelPriorityGroupDTO> toDto(List<EventLevelPriorityGroup> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<EventLevelPriorityGroupDTO> list = new ArrayList<EventLevelPriorityGroupDTO>();
        for ( EventLevelPriorityGroup eventLevelPriorityGroup : entityList ) {
            list.add( toDto( eventLevelPriorityGroup ) );
        }

        return list;
    }
}
