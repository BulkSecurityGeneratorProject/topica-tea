package com.topica.tea.domain;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A BrandkeyProduct.
 */
@Entity
@Table(name = "brandkey_product")
public class BrandkeyProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "brandkey_id", nullable = false)
    private Long brandkey_id;

    @NotNull
    @Column(name = "product_id", nullable = false)
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

    public BrandkeyProduct brandkey_id(Long brandkey_id) {
        this.brandkey_id = brandkey_id;
        return this;
    }

    public void setBrandkey_id(Long brandkey_id) {
        this.brandkey_id = brandkey_id;
    }

    public Long getProduct_id() {
        return product_id;
    }

    public BrandkeyProduct product_id(Long product_id) {
        this.product_id = product_id;
        return this;
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
        BrandkeyProduct brandkeyProduct = (BrandkeyProduct) o;
        if (brandkeyProduct.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), brandkeyProduct.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BrandkeyProduct{" +
            "id=" + getId() +
            ", brandkey_id='" + getBrandkey_id() + "'" +
            ", product_id='" + getProduct_id() + "'" +
            "}";
    }
}
