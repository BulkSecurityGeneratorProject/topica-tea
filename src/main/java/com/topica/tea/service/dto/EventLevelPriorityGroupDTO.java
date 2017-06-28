package com.topica.tea.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import com.topica.tea.domain.enumeration.EventLevel;
import com.topica.tea.domain.enumeration.PriorityGroup;

/**
 * A DTO for the EventLevelPriorityGroup entity.
 */
public class EventLevelPriorityGroupDTO implements Serializable {

    private Long id;

    private Boolean isMeatContent;

    @NotNull
    private EventLevel eventLevel;

    @NotNull
    private PriorityGroup priorityGroup;

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

    public PriorityGroup getPriorityGroup() {
        return priorityGroup;
    }

    public void setPriorityGroup(PriorityGroup priorityGroup) {
        this.priorityGroup = priorityGroup;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EventLevelPriorityGroupDTO eventLevelPriorityGroupDTO = (EventLevelPriorityGroupDTO) o;
        if(eventLevelPriorityGroupDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), eventLevelPriorityGroupDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EventLevelPriorityGroupDTO{" +
            "id=" + getId() +
            ", isMeatContent='" + isIsMeatContent() + "'" +
            ", eventLevel='" + getEventLevel() + "'" +
            ", priorityGroup='" + getPriorityGroup() + "'" +
            "}";
    }
}
