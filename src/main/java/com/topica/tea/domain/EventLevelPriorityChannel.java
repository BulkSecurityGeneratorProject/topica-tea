package com.topica.tea.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

import com.topica.tea.domain.enumeration.EventLevel;

/**
 * A EventLevelPriorityChannel.
 */
@Entity
@Table(name = "event_level_priority_channel")
public class EventLevelPriorityChannel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "is_meat_content")
    private Boolean isMeatContent;

    @Enumerated(EnumType.STRING)
    @Column(name = "event_level")
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

    public EventLevelPriorityChannel isMeatContent(Boolean isMeatContent) {
        this.isMeatContent = isMeatContent;
        return this;
    }

    public void setIsMeatContent(Boolean isMeatContent) {
        this.isMeatContent = isMeatContent;
    }

    public EventLevel getEventLevel() {
        return eventLevel;
    }

    public EventLevelPriorityChannel eventLevel(EventLevel eventLevel) {
        this.eventLevel = eventLevel;
        return this;
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
        EventLevelPriorityChannel eventLevelPriorityChannel = (EventLevelPriorityChannel) o;
        if (eventLevelPriorityChannel.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), eventLevelPriorityChannel.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EventLevelPriorityChannel{" +
            "id=" + getId() +
            ", isMeatContent='" + isIsMeatContent() + "'" +
            ", eventLevel='" + getEventLevel() + "'" +
            "}";
    }
}
