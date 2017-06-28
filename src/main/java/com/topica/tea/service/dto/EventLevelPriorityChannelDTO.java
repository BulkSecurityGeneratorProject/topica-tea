package com.topica.tea.service.dto;


import java.io.Serializable;
import java.util.Objects;
import com.topica.tea.domain.enumeration.EventLevel;

/**
 * A DTO for the EventLevelPriorityChannel entity.
 */
public class EventLevelPriorityChannelDTO implements Serializable {

    private Long id;

    private Boolean isMeatContent;

    private EventLevel eventLevel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isIsMeatContent() {
        return isMeatContent;
    }

    public void setIsMeatContent(Boolean isMeatContent) {
        this.isMeatContent = isMeatContent;
    }

    public EventLevel getEventLevel() {
        return eventLevel;
    }

    public void setEventLevel(EventLevel eventLevel) {
        this.eventLevel = eventLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EventLevelPriorityChannelDTO eventLevelPriorityChannelDTO = (EventLevelPriorityChannelDTO) o;
        if(eventLevelPriorityChannelDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), eventLevelPriorityChannelDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EventLevelPriorityChannelDTO{" +
            "id=" + getId() +
            ", isMeatContent='" + isIsMeatContent() + "'" +
            ", eventLevel='" + getEventLevel() + "'" +
            "}";
    }
}
