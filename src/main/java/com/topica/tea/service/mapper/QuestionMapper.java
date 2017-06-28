package com.topica.tea.service.mapper;

import com.topica.tea.domain.*;
import com.topica.tea.service.dto.QuestionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Question and its DTO QuestionDTO.
 */
@Mapper(componentModel = "spring", uses = {ChannelGroupMapper.class, BrandkeyMapper.class, })
public interface QuestionMapper extends EntityMapper <QuestionDTO, Question> {

    @Mapping(source = "role.id", target = "roleId")

    @Mapping(source = "invitee.id", target = "inviteeId")

    @Mapping(source = "scale.id", target = "scaleId")
    QuestionDTO toDto(Question question); 

    @Mapping(source = "roleId", target = "role")

    @Mapping(source = "inviteeId", target = "invitee")

    @Mapping(source = "scaleId", target = "scale")
    Question toEntity(QuestionDTO questionDTO); 
    default Question fromId(Long id) {
        if (id == null) {
            return null;
        }
        Question question = new Question();
        question.setId(id);
        return question;
    }
}
