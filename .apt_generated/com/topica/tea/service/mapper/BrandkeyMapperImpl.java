package com.topica.tea.service.mapper;

import com.topica.tea.domain.Brandkey;
import com.topica.tea.service.dto.BrandkeyDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2017-07-02T11:05:05+0700",
    comments = "version: 1.1.0.Final, compiler: Eclipse JDT (IDE) 3.12.3.v20170228-1205, environment: Java 1.8.0_101 (Oracle Corporation)"
)
@Component
public class BrandkeyMapperImpl implements BrandkeyMapper {

    @Override
    public Brandkey toEntity(BrandkeyDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Brandkey brandkey = new Brandkey();

        brandkey.setId( dto.getId() );
        brandkey.setBrandkeyName( dto.getBrandkeyName() );
        brandkey.setDescription( dto.getDescription() );

        return brandkey;
    }

    @Override
    public BrandkeyDTO toDto(Brandkey entity) {
        if ( entity == null ) {
            return null;
        }

        BrandkeyDTO brandkeyDTO = new BrandkeyDTO();

        brandkeyDTO.setId( entity.getId() );
        brandkeyDTO.setBrandkeyName( entity.getBrandkeyName() );
        brandkeyDTO.setDescription( entity.getDescription() );

        return brandkeyDTO;
    }

    @Override
    public List<Brandkey> toEntity(List<BrandkeyDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Brandkey> list = new ArrayList<Brandkey>();
        for ( BrandkeyDTO brandkeyDTO : dtoList ) {
            list.add( toEntity( brandkeyDTO ) );
        }

        return list;
    }

    @Override
    public List<BrandkeyDTO> toDto(List<Brandkey> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<BrandkeyDTO> list = new ArrayList<BrandkeyDTO>();
        for ( Brandkey brandkey : entityList ) {
            list.add( toDto( brandkey ) );
        }

        return list;
    }
}
