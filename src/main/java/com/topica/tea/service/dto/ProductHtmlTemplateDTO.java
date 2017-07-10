package com.topica.tea.service.dto;


import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the ProductHtmlTemplate entity.
 */
public class ProductHtmlTemplateDTO implements Serializable {

    private Long id;

    private String name;

    private Long productId;

    private Long htmlTemplateId;

    private String description;

	private Long channelProductId;
    
    private ProductDTO productDTO;
    
    private HtmlTemplateDTO htmlTemplateDTO;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getHtmlTemplateId() {
        return htmlTemplateId;
    }

    public void setHtmlTemplateId(Long htmlTemplateId) {
        this.htmlTemplateId = htmlTemplateId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getChannelProductId() {
        return channelProductId;
    }

    public void setChannelProductId(Long channelProductId) {
        this.channelProductId = channelProductId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProductHtmlTemplateDTO productHtmlTemplateDTO = (ProductHtmlTemplateDTO) o;
        if(productHtmlTemplateDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), productHtmlTemplateDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProductHtmlTemplateDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", productId='" + getProductId() + "'" +
            ", htmlTemplateId='" + getHtmlTemplateId() + "'" +
            ", description='" + getDescription() + "'" +
            ", channelProductId='" + getChannelProductId() + "'" +
            "}";
    }

	public ProductDTO getProductDTO() {
		return productDTO;
	}

	public void setProductDTO(ProductDTO productDTO) {
		this.productDTO = productDTO;
	}
	

    public HtmlTemplateDTO getHtmlTemplateDTO() {
		return htmlTemplateDTO;
	}

	public void setHtmlTemplateDTO(HtmlTemplateDTO htmlTemplateDTO) {
		this.htmlTemplateDTO = htmlTemplateDTO;
	}
}
