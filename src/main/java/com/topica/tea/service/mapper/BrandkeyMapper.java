package com.topica.tea.service.mapper;

import com.topica.tea.domain.*;
import com.topica.tea.service.dto.BrandkeyDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Brandkey and its DTO BrandkeyDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BrandkeyMapper extends EntityMapper <BrandkeyDTO, Brandkey> {
    
    
    default Brandkey fromId(Long id) {
        if (id == null) {
            return null;
        }
        Brandkey brandkey = new Brandkey();
        brandkey.setId(id);
        return brandkey;
    }
}
