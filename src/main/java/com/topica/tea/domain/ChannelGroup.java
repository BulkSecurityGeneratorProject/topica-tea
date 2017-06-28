package com.topica.tea.domain;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

import com.topica.tea.domain.enumeration.ChannelGroupType;

/**
 * A ChannelGroup.
 */
@Entity
@Table(name = "channel_group")
public class ChannelGroup implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "channel_group_type", nullable = false)
    private ChannelGroupType channelGroupType;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "point", nullable = false)
    private Long point;

    @Column(name = "description")
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ChannelGroupType getChannelGroupType() {
        return channelGroupType;
    }

    public ChannelGroup channelGroupType(ChannelGroupType channelGroupType) {
        this.channelGroupType = channelGroupType;
        return this;
    }

    public void setChannelGroupType(ChannelGroupType channelGroupType) {
        this.channelGroupType = channelGroupType;
    }

    public String getName() {
        return name;
    }

    public ChannelGroup name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPoint() {
        return point;
    }

    public ChannelGroup point(Long point) {
        this.point = point;
        return this;
    }

    public void setPoint(Long point) {
        this.point = point;
    }

    public String getDescription() {
        return description;
    }

    public ChannelGroup description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ChannelGroup channelGroup = (ChannelGroup) o;
        if (channelGroup.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), channelGroup.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ChannelGroup{" +
            "id=" + getId() +
            ", channelGroupType='" + getChannelGroupType() + "'" +
            ", name='" + getName() + "'" +
            ", point='" + getPoint() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
