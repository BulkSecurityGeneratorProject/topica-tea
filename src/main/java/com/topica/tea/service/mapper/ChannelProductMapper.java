package com.topica.tea.service.mapper;

import com.topica.tea.domain.*;
import com.topica.tea.service.dto.ChannelProductDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ChannelProduct and its DTO ChannelProductDTO.
 */
@Mapper(componentModel = "spring", uses = {ProductMapper.class, AdsTypeMapper.class, })
public interface ChannelProductMapper extends EntityMapper <ChannelProductDTO, ChannelProduct> {

    @Mapping(source = "product.id", target = "productId")

    @Mapping(source = "adsType.id", target = "adsTypeId")
    ChannelProductDTO toDto(ChannelProduct channelProduct); 

    @Mapping(source = "productId", target = "product")

    @Mapping(source = "adsTypeId", target = "adsType")
    ChannelProduct toEntity(ChannelProductDTO channelProductDTO); 
    default ChannelProduct fromId(Long id) {
        if (id == null) {
            return null;
        }
        ChannelProduct channelProduct = new ChannelProduct();
        channelProduct.setId(id);
        return channelProduct;
    }
}
