package com.topica.tea.service.mapper;

import com.topica.tea.domain.Fanpage;
import com.topica.tea.service.dto.FanpageDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2017-07-10T22:02:26+0700",
    comments = "version: 1.1.0.Final, compiler: Eclipse JDT (IDE) 3.12.3.v20170228-1205, environment: Java 1.8.0_101 (Oracle Corporation)"
)
@Component
public class FanpageMapperImpl implements FanpageMapper {

    @Override
    public Fanpage toEntity(FanpageDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Fanpage fanpage = new Fanpage();

        fanpage.setId( dto.getId() );
        fanpage.setSocialNetworkType( dto.getSocialNetworkType() );
        fanpage.setApiKey( dto.getApiKey() );
        fanpage.setDescription( dto.getDescription() );

        return fanpage;
    }

    @Override
    public FanpageDTO toDto(Fanpage entity) {
        if ( entity == null ) {
            return null;
        }

        FanpageDTO fanpageDTO = new FanpageDTO();

        fanpageDTO.setId( entity.getId() );
        fanpageDTO.setSocialNetworkType( entity.getSocialNetworkType() );
        fanpageDTO.setApiKey( entity.getApiKey() );
        fanpageDTO.setDescription( entity.getDescription() );

        return fanpageDTO;
    }

    @Override
    public List<Fanpage> toEntity(List<FanpageDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Fanpage> list = new ArrayList<Fanpage>();
        for ( FanpageDTO fanpageDTO : dtoList ) {
            list.add( toEntity( fanpageDTO ) );
        }

        return list;
    }

    @Override
    public List<FanpageDTO> toDto(List<Fanpage> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<FanpageDTO> list = new ArrayList<FanpageDTO>();
        for ( Fanpage fanpage : entityList ) {
            list.add( toDto( fanpage ) );
        }

        return list;
    }
}
