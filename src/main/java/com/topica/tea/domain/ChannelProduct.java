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

    @NotNull
    @Column(name = "jhi_link", nullable = false)
    private String link;

    @Column(name = "traffic")
    private Long traffic;

    @Enumerated(EnumType.STRING)
    @Column(name = "traffic_type")
    private TrafficType trafficType;

    @OneToOne
    @JoinColumn
    private Product product;

    @OneToOne
    @JoinColumn
    private AdsType adsType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
