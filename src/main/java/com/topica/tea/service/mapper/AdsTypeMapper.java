package com.topica.tea.service.mapper;

import com.topica.tea.domain.*;
import com.topica.tea.service.dto.AdsTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AdsType and its DTO AdsTypeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AdsTypeMapper extends EntityMapper <AdsTypeDTO, AdsType> {
    
    
    default AdsType fromId(Long id) {
        if (id == null) {
            return null;
        }
        AdsType adsType = new AdsType();
        adsType.setId(id);
        return adsType;
    }
}
