package com.topica.tea.domain;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Brandkey.
 */
@Entity
@Table(name = "brandkey")
public class Brandkey implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "brandkey_name", nullable = false)
    private String brandkeyName;

    @Column(name = "description")
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrandkeyName() {
        return brandkeyName;
    }

    public Brandkey brandkeyName(String brandkeyName) {
        this.brandkeyName = brandkeyName;
        return this;
    }

    public void setBrandkeyName(String brandkeyName) {
        this.brandkeyName = brandkeyName;
    }

    public String getDescription() {
        return description;
    }

    public Brandkey description(String description) {
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
        Brandkey brandkey = (Brandkey) o;
        if (brandkey.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), brandkey.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Brandkey{" +
            "id=" + getId() +
            ", brandkeyName='" + getBrandkeyName() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
