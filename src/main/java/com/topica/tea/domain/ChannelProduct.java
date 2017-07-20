package com.topica.tea.domain;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

import com.topica.tea.domain.enumeration.TrafficType;

/**
 * A ChannelProduct.
 */
@Entity
@Table(name = "channel_product")
public class ChannelProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
    
    @Column(name = "jhi_link", nullable = false)
    private String link;

    @Column(name = "traffic")
    private Long traffic;

    @Enumerated(EnumType.STRING)
    @Column(name = "traffic_type")
    private TrafficType trafficType;

    @Column(name = "app_id", nullable = true)
    private String appId;
    
    @Column(name = "app_secret", nullable = true)
    private String appSecret;
    
    @Column(name = "app_access_token", nullable = true)
    private String appAccessToken;
    
    @Column(name = "page_id", nullable = true)
    private String pageId;
    
    @OneToOne
    @JoinColumn
    private Product product;

    @OneToOne
    @JoinColumn
    private AdsType adsType;

    @OneToOne
    @JoinColumn
    private HtmlTemplate htmlTemplate;
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getPageId() {
        return pageId;
    }

    public ChannelProduct pageId(String pageId) {
        this.pageId = pageId;
        return this;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public HtmlTemplate getHtmlTemplate() {
        return htmlTemplate;
    }

    public ChannelProduct htmlTemplate(HtmlTemplate htmlTemplate) {
        this.htmlTemplate = htmlTemplate;
        return this;
    }

    public void setHtmlTemplate(HtmlTemplate htmlTemplate) {
        this.htmlTemplate = htmlTemplate;
    }

    public String getName() {
        return name;
    }

    public ChannelProduct name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getLink() {
        return link;
    }

    public ChannelProduct link(String link) {
        this.link = link;
        return this;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Long getTraffic() {
        return traffic;
    }

    public ChannelProduct traffic(Long traffic) {
        this.traffic = traffic;
        return this;
    }

    public void setTraffic(Long traffic) {
        this.traffic = traffic;
    }

    public TrafficType getTrafficType() {
        return trafficType;
    }

    public ChannelProduct trafficType(TrafficType trafficType) {
        this.trafficType = trafficType;
        return this;
    }

    public void setTrafficType(TrafficType trafficType) {
        this.trafficType = trafficType;
    }

    public Product getProduct() {
        return product;
    }

    public ChannelProduct product(Product product) {
        this.product = product;
        return this;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public AdsType getAdsType() {
        return adsType;
    }

    public ChannelProduct adsType(AdsType adsType) {
        this.adsType = adsType;
        return this;
    }

    public void setAdsType(AdsType adsType) {
        this.adsType = adsType;
    }
    
    public String getAppId() {
        return appId;
    }

    public ChannelProduct appId(String appId) {
        this.appId = appId;
        return this;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }
    
    public String getAppSecret() {
        return appSecret;
    }

    public ChannelProduct appSecret(String appSecret) {
        this.appSecret = appSecret;
        return this;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }
    
    public String getAppAccessToken() {
        return appAccessToken;
    }

    public ChannelProduct appAccessToken(String appAccessToken) {
        this.appAccessToken = appAccessToken;
        return this;
    }

    public void setAppAccessToken(String appAccessToken) {
        this.appAccessToken = appAccessToken;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ChannelProduct channelProduct = (ChannelProduct) o;
        if (channelProduct.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), channelProduct.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ChannelProduct{" +
            "id=" + getId() +
            ", link='" + getLink() + "'" +
            ", traffic='" + getTraffic() + "'" +
            ", trafficType='" + getTrafficType() + "'" +
            ", product='" + getProduct() + "'" +
            "}";
    }
}
