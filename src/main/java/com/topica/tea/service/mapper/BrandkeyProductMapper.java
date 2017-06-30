package com.topica.tea.service.mapper;

import com.topica.tea.domain.*;
import com.topica.tea.service.dto.BrandkeyProductDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity BrandkeyProduct and its DTO BrandkeyProductDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BrandkeyProductMapper extends EntityMapper <BrandkeyProductDTO, BrandkeyProduct> {
    
    
    default BrandkeyProduct fromId(Long id) {
        if (id == null) {
            return null;
        }
        BrandkeyProduct brandkeyProduct = new BrandkeyProduct();
        brandkeyProduct.setId(id);
        return brandkeyProduct;
    }
}
