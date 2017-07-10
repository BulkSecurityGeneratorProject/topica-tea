package com.topica.tea.service.mapper;

import com.topica.tea.domain.HtmlTemplate;
import com.topica.tea.service.dto.HtmlTemplateDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2017-07-09T08:16:06+0700",
    comments = "version: 1.1.0.Final, compiler: Eclipse JDT (IDE) 3.12.3.v20170228-1205, environment: Java 1.8.0_101 (Oracle Corporation)"
)
@Component
public class HtmlTemplateMapperImpl implements HtmlTemplateMapper {

    @Override
    public HtmlTemplate toEntity(HtmlTemplateDTO dto) {
        if ( dto == null ) {
            return null;
        }

        HtmlTemplate htmlTemplate = new HtmlTemplate();

        htmlTemplate.setUploadedFilename( dto.getUploadedFilename() );
        htmlTemplate.setId( dto.getId() );
        htmlTemplate.setName( dto.getName() );
        htmlTemplate.setLayoutType( dto.getLayoutType() );
        htmlTemplate.setCssContent( dto.getCssContent() );
        htmlTemplate.setTemplateContent( dto.getTemplateContent() );
        htmlTemplate.setDescription( dto.getDescription() );

        return htmlTemplate;
    }

    @Override
    public HtmlTemplateDTO toDto(HtmlTemplate entity) {
        if ( entity == null ) {
            return null;
        }

        HtmlTemplateDTO htmlTemplateDTO = new HtmlTemplateDTO();

        htmlTemplateDTO.setId( entity.getId() );
        htmlTemplateDTO.setName( entity.getName() );
        htmlTemplateDTO.setLayoutType( entity.getLayoutType() );
        htmlTemplateDTO.setCssContent( entity.getCssContent() );
        htmlTemplateDTO.setTemplateContent( entity.getTemplateContent() );
        htmlTemplateDTO.setDescription( entity.getDescription() );
        htmlTemplateDTO.setUploadedFilename( entity.getUploadedFilename() );

        return htmlTemplateDTO;
    }

    @Override
    public List<HtmlTemplate> toEntity(List<HtmlTemplateDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<HtmlTemplate> list = new ArrayList<HtmlTemplate>();
        for ( HtmlTemplateDTO htmlTemplateDTO : dtoList ) {
            list.add( toEntity( htmlTemplateDTO ) );
        }

        return list;
    }

    @Override
    public List<HtmlTemplateDTO> toDto(List<HtmlTemplate> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<HtmlTemplateDTO> list = new ArrayList<HtmlTemplateDTO>();
        for ( HtmlTemplate htmlTemplate : entityList ) {
            list.add( toDto( htmlTemplate ) );
        }

        return list;
    }
}
