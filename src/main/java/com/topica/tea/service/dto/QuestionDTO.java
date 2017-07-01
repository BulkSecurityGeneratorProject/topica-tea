package com.topica.tea.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import com.topica.tea.domain.enumeration.EventType;
import com.topica.tea.domain.enumeration.AmplifyType;

/**
 * A DTO for the Question entity.
 */
public class QuestionDTO implements Serializable {

    private Long id;

    private Boolean isMeatContent;

    private EventType eventType;

    private AmplifyType amplifyType;

    private String extraContent;

    private Long roleId;

    private Long inviteeId;

    private Long scaleId;

    private Set<BrandkeyDTO> brandkeys = new HashSet<>();

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

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public AmplifyType getAmplifyType() {
        return amplifyType;
    }

    public void setAmplifyType(AmplifyType amplifyType) {
        this.amplifyType = amplifyType;
    }

    public String getExtraContent() {
        return extraContent;
    }

    public void setExtraContent(String extraContent) {
        this.extraContent = extraContent;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long channelGroupId) {
        this.roleId = channelGroupId;
    }

    public Long getInviteeId() {
        return inviteeId;
    }

    public void setInviteeId(Long channelGroupId) {
        this.inviteeId = channelGroupId;
    }

    public Long getScaleId() {
        return scaleId;
    }

    public void setScaleId(Long channelGroupId) {
        this.scaleId = channelGroupId;
    }

    public Set<BrandkeyDTO> getBrandkeys() {
        return brandkeys;
    }

    public void setBrandkeys(Set<BrandkeyDTO> brandkeys) {
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

        QuestionDTO questionDTO = (QuestionDTO) o;
        if(questionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), questionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "QuestionDTO{" +
            "id=" + getId() +
            ", isMeatContent='" + isIsMeatContent() + "'" +
            ", eventType='" + getEventType() + "'" +
            ", amplifyType='" + getAmplifyType() + "'" +
            ", extraContent='" + getExtraContent() + "'" +
            "}";
    }
}
