package com.topica.tea.service.mapper;

import com.topica.tea.domain.Product;
import com.topica.tea.service.dto.ProductDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2017-07-05T10:09:51+0700",
    comments = "version: 1.1.0.Final, compiler: Eclipse JDT (IDE) 3.12.3.v20170228-1205, environment: Java 1.8.0_101 (Oracle Corporation)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductDTO toDto(Product arg0) {
        if ( arg0 == null ) {
            return null;
        }

        ProductDTO productDTO = new ProductDTO();

        productDTO.setDescription( arg0.getDescription() );
        productDTO.setId( arg0.getId() );
        productDTO.setProductCode( arg0.getProductCode() );
        productDTO.setProductName( arg0.getProductName() );

        return productDTO;
    }

    @Override
    public List<ProductDTO> toDto(List<Product> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<ProductDTO> list = new ArrayList<ProductDTO>();
        for ( Product product : arg0 ) {
            list.add( toDto( product ) );
        }

        return list;
    }

    @Override
    public Product toEntity(ProductDTO arg0) {
        if ( arg0 == null ) {
            return null;
        }

        Product product = new Product();

        product.setDescription( arg0.getDescription() );
        product.setId( arg0.getId() );
        product.setProductCode( arg0.getProductCode() );
        product.setProductName( arg0.getProductName() );

        return product;
    }

    @Override
    public List<Product> toEntity(List<ProductDTO> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<Product> list = new ArrayList<Product>();
        for ( ProductDTO productDTO : arg0 ) {
            list.add( toEntity( productDTO ) );
        }

        return list;
    }
}
