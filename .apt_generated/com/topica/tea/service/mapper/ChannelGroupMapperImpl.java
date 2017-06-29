package com.topica.tea.service.mapper;

import com.topica.tea.domain.ChannelGroup;
import com.topica.tea.service.dto.ChannelGroupDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2017-06-29T10:34:48+0700",
    comments = "version: 1.1.0.Final, compiler: Eclipse JDT (IDE) 3.12.3.v20170228-1205, environment: Java 1.8.0_101 (Oracle Corporation)"
)
@Component
public class ChannelGroupMapperImpl implements ChannelGroupMapper {

    @Override
    public ChannelGroup toEntity(ChannelGroupDTO dto) {
        if ( dto == null ) {
            return null;
        }

        ChannelGroup channelGroup = new ChannelGroup();

        channelGroup.setId( dto.getId() );
        channelGroup.setChannelGroupType( dto.getChannelGroupType() );
        channelGroup.setName( dto.getName() );
        channelGroup.setPoint( dto.getPoint() );
        channelGroup.setDescription( dto.getDescription() );

        return channelGroup;
    }

    @Override
    public ChannelGroupDTO toDto(ChannelGroup entity) {
        if ( entity == null ) {
            return null;
        }

        ChannelGroupDTO channelGroupDTO = new ChannelGroupDTO();

        channelGroupDTO.setId( entity.getId() );
        channelGroupDTO.setChannelGroupType( entity.getChannelGroupType() );
        channelGroupDTO.setName( entity.getName() );
        channelGroupDTO.setPoint( entity.getPoint() );
        channelGroupDTO.setDescription( entity.getDescription() );

        return channelGroupDTO;
    }

    @Override
    public List<ChannelGroup> toEntity(List<ChannelGroupDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<ChannelGroup> list = new ArrayList<ChannelGroup>();
        for ( ChannelGroupDTO channelGroupDTO : dtoList ) {
            list.add( toEntity( channelGroupDTO ) );
        }

        return list;
    }

    @Override
    public List<ChannelGroupDTO> toDto(List<ChannelGroup> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<ChannelGroupDTO> list = new ArrayList<ChannelGroupDTO>();
        for ( ChannelGroup channelGroup : entityList ) {
            list.add( toDto( channelGroup ) );
        }

        return list;
    }
}
