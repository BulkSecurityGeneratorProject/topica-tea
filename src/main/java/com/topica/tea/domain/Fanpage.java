package com.topica.tea.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

import com.topica.tea.domain.enumeration.SocialNetworkType;

/**
 * A Fanpage.
 */
@Entity
@Table(name = "fanpage")
public class Fanpage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "social_network_type")
    private SocialNetworkType socialNetworkType;

    @Column(name = "api_key")
    private String apiKey;

    @Column(name = "description")
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

    public Fanpage socialNetworkType(SocialNetworkType socialNetworkType) {
        this.socialNetworkType = socialNetworkType;
        return this;
    }

    public void setSocialNetworkType(SocialNetworkType socialNetworkType) {
        this.socialNetworkType = socialNetworkType;
    }

    public String getApiKey() {
        return apiKey;
    }

    public Fanpage apiKey(String apiKey) {
        this.apiKey = apiKey;
        return this;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getDescription() {
        return description;
    }

    public Fanpage description(String description) {
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
        Fanpage fanpage = (Fanpage) o;
        if (fanpage.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), fanpage.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Fanpage{" +
            "id=" + getId() +
            ", socialNetworkType='" + getSocialNetworkType() + "'" +
            ", apiKey='" + getApiKey() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
