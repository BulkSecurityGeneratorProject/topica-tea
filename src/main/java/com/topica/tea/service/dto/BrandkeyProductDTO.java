package com.topica.tea.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the BrandkeyProduct entity.
 */
public class BrandkeyProductDTO implements Serializable {

    private Long id;

    private Long brandkeyId;

    private Long productId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBrandkeyId() {
        return brandkeyId;
    }

    public void setBrandkeyId(Long brandkeyId) {
        this.brandkeyId = brandkeyId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BrandkeyProductDTO brandkeyProductDTO = (BrandkeyProductDTO) o;
        if(brandkeyProductDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), brandkeyProductDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BrandkeyProductDTO{" +
            "id=" + getId() +
            "}";
    }
}
