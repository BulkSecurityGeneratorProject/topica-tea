package com.topica.tea.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A ProductHtmlTemplate.
 */
@Entity
@Table(name = "product_html_template")
public class ProductHtmlTemplate implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "html_template_id")
    private Long htmlTemplateId;

    @Column(name = "description")
    private String description;

    @Column(name = "channel_product_id")
    private Long channelProductId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public ProductHtmlTemplate name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getProductId() {
        return productId;
    }

    public ProductHtmlTemplate productId(Long productId) {
        this.productId = productId;
        return this;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getHtmlTemplateId() {
        return htmlTemplateId;
    }

    public ProductHtmlTemplate htmlTemplateId(Long htmlTemplateId) {
        this.htmlTemplateId = htmlTemplateId;
        return this;
    }

    public void setHtmlTemplateId(Long htmlTemplateId) {
        this.htmlTemplateId = htmlTemplateId;
    }

    public String getDescription() {
        return description;
    }

    public ProductHtmlTemplate description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getChannelProductId() {
        return channelProductId;
    }

    public ProductHtmlTemplate channelProductId(Long channelProductId) {
        this.channelProductId = channelProductId;
        return this;
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
        ProductHtmlTemplate productHtmlTemplate = (ProductHtmlTemplate) o;
        if (productHtmlTemplate.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), productHtmlTemplate.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProductHtmlTemplate{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", productId='" + getProductId() + "'" +
            ", htmlTemplateId='" + getHtmlTemplateId() + "'" +
            ", description='" + getDescription() + "'" +
            ", channelProductId='" + getChannelProductId() + "'" +
            "}";
    }
}
