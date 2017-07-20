package com.topica.tea.service.dto;


import java.io.Serializable;
import java.util.Objects;

import javax.validation.constraints.NotNull;

import com.topica.tea.domain.enumeration.TrafficType;

/**
 * A DTO for the ChannelProduct entity.
 */
public class ChannelProductDTO implements Serializable {

    private Long id;

    private String name;
    
    private String link;

    private Long htmlTemplateId;
    
    private Long traffic;

    private TrafficType trafficType;

    private Long productId;

    private Long adsTypeId;
    
    private String appId;
    
    private String appSecret;
    
    private String appAccessToken;
    
    private String pageId;
    
    private ProductDTO product;
    
    private AdsTypeDTO adsType;

    private HtmlTemplateDTO htmlTemplate;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Long getTraffic() {
        return traffic;
    }

    public void setTraffic(Long traffic) {
        this.traffic = traffic;
    }

    public TrafficType getTrafficType() {
        return trafficType;
    }

    public void setTrafficType(TrafficType trafficType) {
        this.trafficType = trafficType;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getAdsTypeId() {
        return adsTypeId;
    }

    public void setAdsTypeId(Long adsTypeId) {
        this.adsTypeId = adsTypeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ChannelProductDTO channelProductDTO = (ChannelProductDTO) o;
        if(channelProductDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), channelProductDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ChannelProductDTO{" +
            "id=" + getId() +
            ", link='" + getLink() + "'" +
            ", traffic='" + getTraffic() + "'" +
            ", trafficType='" + getTrafficType() + "'" +
            ", productId='" + getProductId() + "'" +
            "}";
    }

	public ProductDTO getProduct() {
		return product;
	}

	public void setProduct(ProductDTO product) {
		this.product = product;
	}

	public AdsTypeDTO getAdsType() {
		return adsType;
	}

	public void setAdsType(AdsTypeDTO adsType) {
		this.adsType = adsType;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public String getAppAccessToken() {
		return appAccessToken;
	}

	public void setAppAccessToken(String appAccessToken) {
		this.appAccessToken = appAccessToken;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getHtmlTemplateId() {
		return htmlTemplateId;
	}

	public void setHtmlTemplateId(Long htmlTemplateId) {
		this.htmlTemplateId = htmlTemplateId;
	}

	public HtmlTemplateDTO getHtmlTemplate() {
		return htmlTemplate;
	}

	public void setHtmlTemplate(HtmlTemplateDTO htmlTemplate) {
		this.htmlTemplate = htmlTemplate;
	}

	public String getPageId() {
		return pageId;
	}

	public void setPageId(String pageId) {
		this.pageId = pageId;
	}
}
