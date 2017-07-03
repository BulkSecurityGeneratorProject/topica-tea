package com.topica.tea.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Objects;

import com.topica.tea.domain.enumeration.EventType;
import com.topica.tea.domain.enumeration.PriorityGroup;
import com.topica.tea.domain.enumeration.AmplifyType;

/**
 * A Question.
 */
@Entity
@Table(name = "question")
public class Question implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "is_meat_content")
    private Boolean isMeatContent;

    @Enumerated(EnumType.STRING)
    @Column(name = "event_type")
    private EventType eventType;

//    @Enumerated(EnumType.STRING)
//    @Column(name = "amplify_type")
//    private AmplifyType amplifyType;

    @Column(name = "extra_content")
    private String extraContent;

    @OneToOne
    @JoinColumn
    private ChannelGroup role;

    @OneToOne
    @JoinColumn
    private ChannelGroup invitee;

    @OneToOne
    @JoinColumn
    private ChannelGroup scale;

    @ManyToMany
    @JoinTable(name = "question_brandkeys",
               joinColumns = @JoinColumn(name="questions_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="brandkeys_id", referencedColumnName="id"))
    private Set<Brandkey> brandkeys = new HashSet<>();
    
    @ElementCollection
    @Enumerated(EnumType.STRING)
    @Column(name = "amplify_type")
    private List<AmplifyType> amplifyType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isIsMeatContent() {
        return isMeatContent;
    }

    public Question isMeatContent(Boolean isMeatContent) {
        this.isMeatContent = isMeatContent;
        return this;
    }

    public void setIsMeatContent(Boolean isMeatContent) {
        this.isMeatContent = isMeatContent;
    }

    public EventType getEventType() {
        return eventType;
    }

    public Question eventType(EventType eventType) {
        this.eventType = eventType;
        return this;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

//    public AmplifyType getAmplifyType() {
//        return amplifyType;
//    }
//
//    public Question amplifyType(AmplifyType amplifyType) {
//        this.amplifyType = amplifyType;
//        return this;
//    }
//
//    public void setAmplifyType(AmplifyType amplifyType) {
//        this.amplifyType = amplifyType;
//    }
    
    public List<AmplifyType> getAmplifyType() {
        return amplifyType;
    }

    public Question amplifyType(List<AmplifyType> amplifyType) {
        this.amplifyType = amplifyType;
        return this;
    }

    public void setAmplifyType(List<AmplifyType> amplifyType) {
        this.amplifyType = amplifyType;
    }

    public String getExtraContent() {
        return extraContent;
    }

    public Question extraContent(String extraContent) {
        this.extraContent = extraContent;
        return this;
    }

    public void setExtraContent(String extraContent) {
        this.extraContent = extraContent;
    }

    public ChannelGroup getRole() {
        return role;
    }

    public Question role(ChannelGroup channelGroup) {
        this.role = channelGroup;
        return this;
    }

    public void setRole(ChannelGroup channelGroup) {
        this.role = channelGroup;
    }

    public ChannelGroup getInvitee() {
        return invitee;
    }

    public Question invitee(ChannelGroup channelGroup) {
        this.invitee = channelGroup;
        return this;
    }

    public void setInvitee(ChannelGroup channelGroup) {
        this.invitee = channelGroup;
    }

    public ChannelGroup getScale() {
        return scale;
    }

    public Question scale(ChannelGroup channelGroup) {
        this.scale = channelGroup;
        return this;
    }

    public void setScale(ChannelGroup channelGroup) {
        this.scale = channelGroup;
    }

    public Set<Brandkey> getBrandkeys() {
        return brandkeys;
    }

    public Question brandkeys(Set<Brandkey> brandkeys) {
        this.brandkeys = brandkeys;
        return this;
    }

    public Question addBrandkeys(Brandkey brandkey) {
        this.brandkeys.add(brandkey);
        return this;
    }

    public Question removeBrandkeys(Brandkey brandkey) {
        this.brandkeys.remove(brandkey);
        return this;
    }

    public void setBrandkeys(Set<Brandkey> brandkeys) {
        this.brandkeys = brandkeys;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Question question = (Question) o;
        if (question.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), question.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Question{" +
            "id=" + getId() +
            ", isMeatContent='" + isIsMeatContent() + "'" +
            ", eventType='" + getEventType() + "'" +
            ", amplifyType='" + getAmplifyType() + "'" +
            ", extraContent='" + getExtraContent() + "'" +
            "}";
    }
}
