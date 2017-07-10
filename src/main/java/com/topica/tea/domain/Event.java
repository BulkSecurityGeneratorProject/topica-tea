package com.topica.tea.domain;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

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

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "content")
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(name = "event_status")
    private EventStatus eventStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "event_level")
    private EventLevel eventLevel;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    @Column(name = "amplify_type")
    private List<AmplifyType> amplifyType;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    @Column(name = "priority_group")
    private List<PriorityGroup> priorityGroup;

    @Column(name = "schedule")
    private ZonedDateTime schedule;

    @OneToOne
    @JoinColumn
    private Question question;
    
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn
    private Article article;
    
    @Column(name = "is_hot_event")
    private Boolean isHotEvent;
    
    @ManyToMany
    @JoinTable(name = "event_products",
               joinColumns = @JoinColumn(name="event_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="products_id", referencedColumnName="id"))
    private Set<Product> products = new HashSet<>();
    
    @ManyToMany
    @JoinTable(name = "event_channel_products",
               joinColumns = @JoinColumn(name="event_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="channel_products_id", referencedColumnName="id"))
    private Set<ChannelProduct> channelProducts = new HashSet<>();

	@OneToOne
    @JoinColumn
    private User createdUser;

    @OneToOne
    @JoinColumn
    private User approvalUser;
    
    @OneToOne
    @JoinColumn
    private User managerUser;
    
    @OneToOne
    @JoinColumn
    private User writerUser;

    public Set<ChannelProduct> getChannelProducts() {
		return channelProducts;
	}

	public void setChannelProducts(Set<ChannelProduct> channelProducts) {
		this.channelProducts = channelProducts;
	}
	
    public User getWriterUser() {
		return writerUser;
	}

	public void setWriterUser(User writerUser) {
		this.writerUser = writerUser;
	}

    public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Event name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public Event description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public Event content(String content) {
        this.content = content;
        return this;
    }

    public void setContent(String content) {
        this.content = content;
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

    public List<AmplifyType> getAmplifyType() {
        return amplifyType;
    }

    public Event amplifyType(List<AmplifyType> amplifyType) {
        this.amplifyType = amplifyType;
        return this;
    }

    public void setAmplifyType(List<AmplifyType> amplifyType) {
        this.amplifyType = amplifyType;
    }

    public List<PriorityGroup> getPriorityGroup() {
        return priorityGroup;
    }

    public Event priorityGroup(List<PriorityGroup> priorityGroup) {
        this.priorityGroup = priorityGroup;
        return this;
    }

    public void setPriorityGroup(List<PriorityGroup> priorityGroup) {
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

    public Article getArticle() {
        return article;
    }

    public Event article(Article article) {
        this.article = article;
        return this;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
    
    public Boolean getIsHotEvent() {
        return isHotEvent;
    }

    public Event isHotEvent(Boolean isHotEvent) {
        this.isHotEvent = isHotEvent;
        return this;
    }

    public void setIsHotEvent(Boolean isHotEvent) {
        this.isHotEvent = isHotEvent;
    }
    
    // Custom
    public User getCreatedUser() {
        return createdUser;
    }

    public Event createdUser(User createdUser) {
        this.createdUser = createdUser;
        return this;
    }

    public void setCreatedUser(User createdUser) {
        this.createdUser = createdUser;
    }
    
    public User getApprovalUser() {
        return approvalUser;
    }

    public Event approvalUser(User approvalUser) {
        this.approvalUser = approvalUser;
        return this;
    }

    public void setApprovalUser(User approvalUser) {
        this.approvalUser = approvalUser;
    }
    
    public User getManagerUser() {
        return managerUser;
    }

    public Event managerUser(User managerUser) {
        this.managerUser = managerUser;
        return this;
    }

    public void setManagerUser(User managerUser) {
        this.managerUser = managerUser;
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
