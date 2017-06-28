package com.topica.tea.domain;


import javax.persistence.*;
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

    @OneToOne
    @JoinColumn(unique = true)
    private Brandkey brandkey;

    @OneToOne
    @JoinColumn(unique = true)
    private Product product;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Brandkey getBrandkey() {
        return brandkey;
    }

    public BrandkeyProduct brandkey(Brandkey brandkey) {
        this.brandkey = brandkey;
        return this;
    }

    public void setBrandkey(Brandkey brandkey) {
        this.brandkey = brandkey;
    }

    public Product getProduct() {
        return product;
    }

    public BrandkeyProduct product(Product product) {
        this.product = product;
        return this;
    }

    public void setProduct(Product product) {
        this.product = product;
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
            "}";
    }
}
