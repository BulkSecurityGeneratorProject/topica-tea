package com.topica.tea.service.mapper;

import com.topica.tea.domain.ProductHtmlTemplate;
import com.topica.tea.service.dto.ProductHtmlTemplateDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2017-07-10T00:51:32+0700",
    comments = "version: 1.1.0.Final, compiler: Eclipse JDT (IDE) 3.12.3.v20170228-1205, environment: Java 1.8.0_101 (Oracle Corporation)"
)
@Component
public class ProductHtmlTemplateMapperImpl implements ProductHtmlTemplateMapper {

    @Override
    public ProductHtmlTemplateDTO toDto(ProductHtmlTemplate arg0) {
        if ( arg0 == null ) {
            return null;
        }

        ProductHtmlTemplateDTO productHtmlTemplateDTO = new ProductHtmlTemplateDTO();

        productHtmlTemplateDTO.setChannelProductId( arg0.getChannelProductId() );
        productHtmlTemplateDTO.setDescription( arg0.getDescription() );
        productHtmlTemplateDTO.setHtmlTemplateId( arg0.getHtmlTemplateId() );
        productHtmlTemplateDTO.setId( arg0.getId() );
        productHtmlTemplateDTO.setName( arg0.getName() );
        productHtmlTemplateDTO.setProductId( arg0.getProductId() );

        return productHtmlTemplateDTO;
    }

    @Override
    public List<ProductHtmlTemplateDTO> toDto(List<ProductHtmlTemplate> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<ProductHtmlTemplateDTO> list = new ArrayList<ProductHtmlTemplateDTO>();
        for ( ProductHtmlTemplate productHtmlTemplate : arg0 ) {
            list.add( toDto( productHtmlTemplate ) );
        }

        return list;
    }

    @Override
    public ProductHtmlTemplate toEntity(ProductHtmlTemplateDTO arg0) {
        if ( arg0 == null ) {
            return null;
        }

        ProductHtmlTemplate productHtmlTemplate = new ProductHtmlTemplate();

        productHtmlTemplate.setChannelProductId( arg0.getChannelProductId() );
        productHtmlTemplate.setDescription( arg0.getDescription() );
        productHtmlTemplate.setHtmlTemplateId( arg0.getHtmlTemplateId() );
        productHtmlTemplate.setId( arg0.getId() );
        productHtmlTemplate.setName( arg0.getName() );
        productHtmlTemplate.setProductId( arg0.getProductId() );

        return productHtmlTemplate;
    }

    @Override
    public List<ProductHtmlTemplate> toEntity(List<ProductHtmlTemplateDTO> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<ProductHtmlTemplate> list = new ArrayList<ProductHtmlTemplate>();
        for ( ProductHtmlTemplateDTO productHtmlTemplateDTO : arg0 ) {
            list.add( toEntity( productHtmlTemplateDTO ) );
        }

        return list;
    }
}
