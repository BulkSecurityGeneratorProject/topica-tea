package com.topica.tea.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Brandkey entity.
 */
public class BrandkeyDTO implements Serializable {

    private Long id;

    @NotNull
    private String brandkeyName;

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

    public void setBrandkeyName(String brandkeyName) {
        this.brandkeyName = brandkeyName;
    }

    public String getDescription() {
        return description;
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

        BrandkeyDTO brandkeyDTO = (BrandkeyDTO) o;
        if(brandkeyDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), brandkeyDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BrandkeyDTO{" +
            "id=" + getId() +
            ", brandkeyName='" + getBrandkeyName() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
