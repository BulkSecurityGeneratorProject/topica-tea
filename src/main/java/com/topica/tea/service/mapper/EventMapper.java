package com.topica.tea.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.topica.tea.domain.Event;
import com.topica.tea.service.dto.EventDTO;

/**
 * Mapper for the entity Event and its DTO EventDTO.
 */
@Mapper(componentModel = "spring", uses = {QuestionMapper.class, ArticleMapper.class, ProductMapper.class, })
public interface EventMapper extends EntityMapper <EventDTO, Event> {

    @Mapping(source = "question.id", target = "questionId")

    @Mapping(source = "article.id", target = "articleId")
    
    //@Mapping(target="priorityGroup", ignore=true)
    EventDTO toDto(Event event); 

    @Mapping(source = "questionId", target = "question")

    @Mapping(source = "articleId", target = "article")
    
    Event toEntity(EventDTO eventDTO); 
    default Event fromId(Long id) {
        if (id == null) {
            return null;
        }
        Event event = new Event();
        event.setId(id);
        return event;
    }
}
