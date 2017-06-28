package com.topica.tea.domain;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

import com.topica.tea.domain.enumeration.EventLevel;

import com.topica.tea.domain.enumeration.PriorityGroup;

/**
 * A EventLevelPriorityGroup.
 */
@Entity
@Table(name = "event_level_priority_group")
public class EventLevelPriorityGroup implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "is_meat_content")
    private Boolean isMeatContent;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "event_level", nullable = false)
    private EventLevel eventLevel;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "priority_group", nullable = false)
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

    public EventLevelPriorityGroup isMeatContent(Boolean isMeatContent) {
        this.isMeatContent = isMeatContent;
        return this;
    }

    public void setIsMeatContent(Boolean isMeatContent) {
        this.isMeatContent = isMeatContent;
    }

    public EventLevel getEventLevel() {
        return eventLevel;
    }

    public EventLevelPriorityGroup eventLevel(EventLevel eventLevel) {
        this.eventLevel = eventLevel;
        return this;
    }

    public void setEventLevel(EventLevel eventLevel) {
        this.eventLevel = eventLevel;
    }

    public PriorityGroup getPriorityGroup() {
        return priorityGroup;
    }

    public EventLevelPriorityGroup priorityGroup(PriorityGroup priorityGroup) {
        this.priorityGroup = priorityGroup;
        return this;
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
        EventLevelPriorityGroup eventLevelPriorityGroup = (EventLevelPriorityGroup) o;
        if (eventLevelPriorityGroup.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), eventLevelPriorityGroup.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EventLevelPriorityGroup{" +
            "id=" + getId() +
            ", isMeatContent='" + isIsMeatContent() + "'" +
            ", eventLevel='" + getEventLevel() + "'" +
            ", priorityGroup='" + getPriorityGroup() + "'" +
            "}";
    }
}
