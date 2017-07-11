package com.topica.tea.service.mapper;

import com.topica.tea.domain.ProductHtmlTemplate;
import com.topica.tea.service.dto.ProductHtmlTemplateDTO;
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
public class ProductHtmlTemplateMapperImpl implements ProductHtmlTemplateMapper {

    @Override
    public ProductHtmlTemplate toEntity(ProductHtmlTemplateDTO dto) {
        if ( dto == null ) {
            return null;
        }

        ProductHtmlTemplate productHtmlTemplate = new ProductHtmlTemplate();

        productHtmlTemplate.setId( dto.getId() );
        productHtmlTemplate.setName( dto.getName() );
        productHtmlTemplate.setProductId( dto.getProductId() );
        productHtmlTemplate.setHtmlTemplateId( dto.getHtmlTemplateId() );
        productHtmlTemplate.setDescription( dto.getDescription() );
        productHtmlTemplate.setChannelProductId( dto.getChannelProductId() );

        return productHtmlTemplate;
    }

    @Override
    public ProductHtmlTemplateDTO toDto(ProductHtmlTemplate entity) {
        if ( entity == null ) {
            return null;
        }

        ProductHtmlTemplateDTO productHtmlTemplateDTO = new ProductHtmlTemplateDTO();

        productHtmlTemplateDTO.setId( entity.getId() );
        productHtmlTemplateDTO.setName( entity.getName() );
        productHtmlTemplateDTO.setProductId( entity.getProductId() );
        productHtmlTemplateDTO.setHtmlTemplateId( entity.getHtmlTemplateId() );
        productHtmlTemplateDTO.setDescription( entity.getDescription() );
        productHtmlTemplateDTO.setChannelProductId( entity.getChannelProductId() );

        return productHtmlTemplateDTO;
    }

    @Override
    public List<ProductHtmlTemplate> toEntity(List<ProductHtmlTemplateDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<ProductHtmlTemplate> list = new ArrayList<ProductHtmlTemplate>();
        for ( ProductHtmlTemplateDTO productHtmlTemplateDTO : dtoList ) {
            list.add( toEntity( productHtmlTemplateDTO ) );
        }

        return list;
    }

    @Override
    public List<ProductHtmlTemplateDTO> toDto(List<ProductHtmlTemplate> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<ProductHtmlTemplateDTO> list = new ArrayList<ProductHtmlTemplateDTO>();
        for ( ProductHtmlTemplate productHtmlTemplate : entityList ) {
            list.add( toDto( productHtmlTemplate ) );
        }

        return list;
    }
}
