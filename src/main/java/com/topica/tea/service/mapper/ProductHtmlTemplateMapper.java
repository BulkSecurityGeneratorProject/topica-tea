package com.topica.tea.service.mapper;

import com.topica.tea.domain.*;
import com.topica.tea.service.dto.ProductHtmlTemplateDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ProductHtmlTemplate and its DTO ProductHtmlTemplateDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProductHtmlTemplateMapper extends EntityMapper <ProductHtmlTemplateDTO, ProductHtmlTemplate> {
    
    
    default ProductHtmlTemplate fromId(Long id) {
        if (id == null) {
            return null;
        }
        ProductHtmlTemplate productHtmlTemplate = new ProductHtmlTemplate();
        productHtmlTemplate.setId(id);
        return productHtmlTemplate;
    }
}
