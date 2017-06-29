package com.topica.tea.service.dto;


import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import com.topica.tea.domain.enumeration.EventStatus;
import com.topica.tea.domain.enumeration.EventLevel;
import com.topica.tea.domain.enumeration.AmplifyType;
import com.topica.tea.domain.enumeration.PriorityGroup;

/**
 * A DTO for the Event entity.
 */
public class EventDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    private String description;

    private String content;

    private EventStatus eventStatus;

    private EventLevel eventLevel;

    private AmplifyType amplifyType;

    private PriorityGroup priorityGroup;

    private ZonedDateTime schedule;

    private Long questionId;

    private Long articleId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public EventStatus getEventStatus() {
        return eventStatus;
    }

    public void setEventStatus(EventStatus eventStatus) {
        this.eventStatus = eventStatus;
    }

    public EventLevel getEventLevel() {
        return eventLevel;
    }

    public void setEventLevel(EventLevel eventLevel) {
        this.eventLevel = eventLevel;
    }

    public AmplifyType getAmplifyType() {
        return amplifyType;
    }

    public void setAmplifyType(AmplifyType amplifyType) {
        this.amplifyType = amplifyType;
    }

    public PriorityGroup getPriorityGroup() {
        return priorityGroup;
    }

    public void setPriorityGroup(PriorityGroup priorityGroup) {
        this.priorityGroup = priorityGroup;
    }

    public ZonedDateTime getSchedule() {
        return schedule;
    }

    public void setSchedule(ZonedDateTime schedule) {
        this.schedule = schedule;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EventDTO eventDTO = (EventDTO) o;
        if(eventDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), eventDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EventDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", content='" + getContent() + "'" +
            ", eventStatus='" + getEventStatus() + "'" +
            ", eventLevel='" + getEventLevel() + "'" +
            ", amplifyType='" + getAmplifyType() + "'" +
            ", priorityGroup='" + getPriorityGroup() + "'" +
            ", schedule='" + getSchedule() + "'" +
            "}";
    }
}
