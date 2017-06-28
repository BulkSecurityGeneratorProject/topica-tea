package com.topica.tea.service.mapper;

import com.topica.tea.domain.Brandkey;
import com.topica.tea.domain.BrandkeyProduct;
import com.topica.tea.domain.Product;
import com.topica.tea.service.dto.BrandkeyProductDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2017-06-28T17:16:57+0700",
    comments = "version: 1.1.0.Final, compiler: Eclipse JDT (IDE) 3.12.3.v20170228-1205, environment: Java 1.8.0_101 (Oracle Corporation)"
)
@Component
public class BrandkeyProductMapperImpl implements BrandkeyProductMapper {

    @Autowired
    private BrandkeyMapper brandkeyMapper;
    @Autowired
    private ProductMapper productMapper;

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

    @Override
    public BrandkeyProductDTO toDto(BrandkeyProduct brandkeyProduct) {
        if ( brandkeyProduct == null ) {
            return null;
        }

        BrandkeyProductDTO brandkeyProductDTO_ = new BrandkeyProductDTO();

        brandkeyProductDTO_.setProductId( brandkeyProductProductId( brandkeyProduct ) );
        brandkeyProductDTO_.setBrandkeyId( brandkeyProductBrandkeyId( brandkeyProduct ) );
        brandkeyProductDTO_.setId( brandkeyProduct.getId() );

        return brandkeyProductDTO_;
    }

    @Override
    public BrandkeyProduct toEntity(BrandkeyProductDTO brandkeyProductDTO) {
        if ( brandkeyProductDTO == null ) {
            return null;
        }

        BrandkeyProduct brandkeyProduct_ = new BrandkeyProduct();

        brandkeyProduct_.setBrandkey( brandkeyMapper.fromId( brandkeyProductDTO.getBrandkeyId() ) );
        brandkeyProduct_.setProduct( productMapper.fromId( brandkeyProductDTO.getProductId() ) );
        brandkeyProduct_.setId( brandkeyProductDTO.getId() );

        return brandkeyProduct_;
    }

    private Long brandkeyProductProductId(BrandkeyProduct brandkeyProduct) {

        if ( brandkeyProduct == null ) {
            return null;
        }
        Product product = brandkeyProduct.getProduct();
        if ( product == null ) {
            return null;
        }
        Long id = product.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long brandkeyProductBrandkeyId(BrandkeyProduct brandkeyProduct) {

        if ( brandkeyProduct == null ) {
            return null;
        }
        Brandkey brandkey = brandkeyProduct.getBrandkey();
        if ( brandkey == null ) {
            return null;
        }
        Long id = brandkey.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
