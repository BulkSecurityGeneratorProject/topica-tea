package com.topica.tea.service.mapper;

import com.topica.tea.domain.*;
import com.topica.tea.service.dto.BrandkeyProductDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity BrandkeyProduct and its DTO BrandkeyProductDTO.
 */
@Mapper(componentModel = "spring", uses = {BrandkeyMapper.class, ProductMapper.class, })
public interface BrandkeyProductMapper extends EntityMapper <BrandkeyProductDTO, BrandkeyProduct> {

    @Mapping(source = "brandkey.id", target = "brandkeyId")

    @Mapping(source = "product.id", target = "productId")
    BrandkeyProductDTO toDto(BrandkeyProduct brandkeyProduct); 

    @Mapping(source = "brandkeyId", target = "brandkey")

    @Mapping(source = "productId", target = "product")
    BrandkeyProduct toEntity(BrandkeyProductDTO brandkeyProductDTO); 
    default BrandkeyProduct fromId(Long id) {
        if (id == null) {
            return null;
        }
        BrandkeyProduct brandkeyProduct = new BrandkeyProduct();
        brandkeyProduct.setId(id);
        return brandkeyProduct;
    }
}
