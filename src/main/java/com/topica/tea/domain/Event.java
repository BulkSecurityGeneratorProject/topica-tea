package com.topica.tea.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

import com.topica.tea.domain.enumeration.EventStatus;

import com.topica.tea.domain.enumeration.EventLevel;

import com.topica.tea.domain.enumeration.AmplifyType;

import com.topica.tea.domain.enumeration.PriorityGroup;

/**
 * A Event.
 */
@Entity
@Table(name = "event")
public class Event implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "event_status")
    private EventStatus eventStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "event_level")
    private EventLevel eventLevel;

    @Enumerated(EnumType.STRING)
    @Column(name = "amplify_type")
    private AmplifyType amplifyType;

    @Enumerated(EnumType.STRING)
    @Column(name = "priority_group")
    private PriorityGroup priorityGroup;

    @Column(name = "schedule")
    private ZonedDateTime schedule;

    @OneToOne
    @JoinColumn(unique = true)
    private Question question;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EventStatus getEventStatus() {
        return eventStatus;
    }

    public Event eventStatus(EventStatus eventStatus) {
        this.eventStatus = eventStatus;
        return this;
    }

    public void setEventStatus(EventStatus eventStatus) {
        this.eventStatus = eventStatus;
    }

    public EventLevel getEventLevel() {
        return eventLevel;
    }

    public Event eventLevel(EventLevel eventLevel) {
        this.eventLevel = eventLevel;
        return this;
    }

    public void setEventLevel(EventLevel eventLevel) {
        this.eventLevel = eventLevel;
    }

    public AmplifyType getAmplifyType() {
        return amplifyType;
    }

    public Event amplifyType(AmplifyType amplifyType) {
        this.amplifyType = amplifyType;
        return this;
    }

    public void setAmplifyType(AmplifyType amplifyType) {
        this.amplifyType = amplifyType;
    }

    public PriorityGroup getPriorityGroup() {
        return priorityGroup;
    }

    public Event priorityGroup(PriorityGroup priorityGroup) {
        this.priorityGroup = priorityGroup;
        return this;
    }

    public void setPriorityGroup(PriorityGroup priorityGroup) {
        this.priorityGroup = priorityGroup;
    }

    public ZonedDateTime getSchedule() {
        return schedule;
    }

    public Event schedule(ZonedDateTime schedule) {
        this.schedule = schedule;
        return this;
    }

    public void setSchedule(ZonedDateTime schedule) {
        this.schedule = schedule;
    }

    public Question getQuestion() {
        return question;
    }

    public Event question(Question question) {
        this.question = question;
        return this;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Event event = (Event) o;
        if (event.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), event.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Event{" +
            "id=" + getId() +
            ", eventStatus='" + getEventStatus() + "'" +
            ", eventLevel='" + getEventLevel() + "'" +
            ", amplifyType='" + getAmplifyType() + "'" +
            ", priorityGroup='" + getPriorityGroup() + "'" +
            ", schedule='" + getSchedule() + "'" +
            "}";
    }
}
