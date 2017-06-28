package com.topica.tea.service.mapper;

import com.topica.tea.domain.*;
import com.topica.tea.service.dto.EventDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Event and its DTO EventDTO.
 */
@Mapper(componentModel = "spring", uses = {QuestionMapper.class, })
public interface EventMapper extends EntityMapper <EventDTO, Event> {

    @Mapping(source = "question.id", target = "questionId")
    EventDTO toDto(Event event); 

    @Mapping(source = "questionId", target = "question")
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
