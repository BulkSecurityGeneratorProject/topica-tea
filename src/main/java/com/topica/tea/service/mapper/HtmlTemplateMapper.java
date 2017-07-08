package com.topica.tea.service.mapper;

import com.topica.tea.domain.*;
import com.topica.tea.service.dto.HtmlTemplateDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity HtmlTemplate and its DTO HtmlTemplateDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface HtmlTemplateMapper extends EntityMapper <HtmlTemplateDTO, HtmlTemplate> {
    
    
    default HtmlTemplate fromId(Long id) {
        if (id == null) {
            return null;
        }
        HtmlTemplate htmlTemplate = new HtmlTemplate();
        htmlTemplate.setId(id);
        return htmlTemplate;
    }
}
