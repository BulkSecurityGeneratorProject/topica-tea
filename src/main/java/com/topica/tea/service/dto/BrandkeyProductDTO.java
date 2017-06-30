package com.topica.tea.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the BrandkeyProduct entity.
 */
public class BrandkeyProductDTO implements Serializable {

    private Long id;

    @NotNull
    private Long brandkey_id;

    @NotNull
    private Long product_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBrandkey_id() {
        return brandkey_id;
    }

    public void setBrandkey_id(Long brandkey_id) {
        this.brandkey_id = brandkey_id;
    }

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
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
            ", brandkey_id='" + getBrandkey_id() + "'" +
            ", product_id='" + getProduct_id() + "'" +
            "}";
    }
}
