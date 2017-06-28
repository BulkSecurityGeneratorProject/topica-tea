package com.topica.tea.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import com.topica.tea.domain.enumeration.ChannelGroupType;

/**
 * A DTO for the ChannelGroup entity.
 */
public class ChannelGroupDTO implements Serializable {

    private Long id;

    @NotNull
    private ChannelGroupType channelGroupType;

    @NotNull
    private String name;

    @NotNull
    private Long point;

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

    public void setChannelGroupType(ChannelGroupType channelGroupType) {
        this.channelGroupType = channelGroupType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPoint() {
        return point;
    }

    public void setPoint(Long point) {
        this.point = point;
    }

    public String getDescription() {
        return description;
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

        ChannelGroupDTO channelGroupDTO = (ChannelGroupDTO) o;
        if(channelGroupDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), channelGroupDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ChannelGroupDTO{" +
            "id=" + getId() +
            ", channelGroupType='" + getChannelGroupType() + "'" +
            ", name='" + getName() + "'" +
            ", point='" + getPoint() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
