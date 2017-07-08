package com.topica.tea.service.dto;


import java.io.Serializable;
import java.util.Objects;
import com.topica.tea.domain.enumeration.SocialNetworkType;

/**
 * A DTO for the Fanpage entity.
 */
public class FanpageDTO implements Serializable {

    private Long id;

    private SocialNetworkType socialNetworkType;

    private String apiKey;

    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SocialNetworkType getSocialNetworkType() {
        return socialNetworkType;
    }

    public void setSocialNetworkType(SocialNetworkType socialNetworkType) {
        this.socialNetworkType = socialNetworkType;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
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

        FanpageDTO fanpageDTO = (FanpageDTO) o;
        if(fanpageDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), fanpageDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FanpageDTO{" +
            "id=" + getId() +
            ", socialNetworkType='" + getSocialNetworkType() + "'" +
            ", apiKey='" + getApiKey() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
