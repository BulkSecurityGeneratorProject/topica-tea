package com.topica.tea.service.mapper;

import com.topica.tea.domain.BrandkeyProduct;
import com.topica.tea.service.dto.BrandkeyProductDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2017-07-01T10:03:55+0700",
    comments = "version: 1.1.0.Final, compiler: Eclipse JDT (IDE) 3.12.3.v20170228-1205, environment: Java 1.8.0_101 (Oracle Corporation)"
)
@Component
public class BrandkeyProductMapperImpl implements BrandkeyProductMapper {

    @Override
    public BrandkeyProduct toEntity(BrandkeyProductDTO dto) {
        if ( dto == null ) {
            return null;
        }

        BrandkeyProduct brandkeyProduct = new BrandkeyProduct();

        brandkeyProduct.setId( dto.getId() );
        brandkeyProduct.setBrandkey_id( dto.getBrandkey_id() );
        brandkeyProduct.setProduct_id( dto.getProduct_id() );

        return brandkeyProduct;
    }

    @Override
    public BrandkeyProductDTO toDto(BrandkeyProduct entity) {
        if ( entity == null ) {
            return null;
        }

        BrandkeyProductDTO brandkeyProductDTO = new BrandkeyProductDTO();

        brandkeyProductDTO.setId( entity.getId() );
        brandkeyProductDTO.setBrandkey_id( entity.getBrandkey_id() );
        brandkeyProductDTO.setProduct_id( entity.getProduct_id() );

        return brandkeyProductDTO;
    }

    @Override
    public List<BrandkeyProduct> toEntity(List<BrandkeyProductDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<BrandkeyProduct> list = new ArrayList<BrandkeyProduct>();
        for ( BrandkeyProductDTO brandkeyProductDTO : dtoList ) {
            list.add( toEntity( brandkeyProductDTO ) );
        }

        return list;
    }

    @Override
    public List<BrandkeyProductDTO> toDto(List<BrandkeyProduct> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<BrandkeyProductDTO> list = new ArrayList<BrandkeyProductDTO>();
        for ( BrandkeyProduct brandkeyProduct : entityList ) {
            list.add( toDto( brandkeyProduct ) );
        }

        return list;
    }
}
