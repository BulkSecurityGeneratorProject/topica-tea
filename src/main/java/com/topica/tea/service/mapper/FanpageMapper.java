package com.topica.tea.service.mapper;

import com.topica.tea.domain.*;
import com.topica.tea.service.dto.FanpageDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Fanpage and its DTO FanpageDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface FanpageMapper extends EntityMapper <FanpageDTO, Fanpage> {
    
    
    default Fanpage fromId(Long id) {
        if (id == null) {
            return null;
        }
        Fanpage fanpage = new Fanpage();
        fanpage.setId(id);
        return fanpage;
    }
}
