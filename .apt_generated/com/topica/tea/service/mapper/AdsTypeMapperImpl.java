package com.topica.tea.service.mapper;

import com.topica.tea.domain.AdsType;
import com.topica.tea.service.dto.AdsTypeDTO;
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
public class AdsTypeMapperImpl implements AdsTypeMapper {

    @Override
    public AdsType toEntity(AdsTypeDTO dto) {
        if ( dto == null ) {
            return null;
        }

        AdsType adsType = new AdsType();

        adsType.setId( dto.getId() );
        adsType.setName( dto.getName() );
        adsType.setDescription( dto.getDescription() );

        return adsType;
    }

    @Override
    public AdsTypeDTO toDto(AdsType entity) {
        if ( entity == null ) {
            return null;
        }

        AdsTypeDTO adsTypeDTO = new AdsTypeDTO();

        adsTypeDTO.setId( entity.getId() );
        adsTypeDTO.setName( entity.getName() );
        adsTypeDTO.setDescription( entity.getDescription() );

        return adsTypeDTO;
    }

    @Override
    public List<AdsType> toEntity(List<AdsTypeDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<AdsType> list = new ArrayList<AdsType>();
        for ( AdsTypeDTO adsTypeDTO : dtoList ) {
            list.add( toEntity( adsTypeDTO ) );
        }

        return list;
    }

    @Override
    public List<AdsTypeDTO> toDto(List<AdsType> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<AdsTypeDTO> list = new ArrayList<AdsTypeDTO>();
        for ( AdsType adsType : entityList ) {
            list.add( toDto( adsType ) );
        }

        return list;
    }
}
