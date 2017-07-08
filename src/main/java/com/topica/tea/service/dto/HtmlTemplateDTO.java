package com.topica.tea.service.dto;


import java.io.Serializable;
import java.util.Objects;
import com.topica.tea.domain.enumeration.LayoutType;

/**
 * A DTO for the HtmlTemplate entity.
 */
public class HtmlTemplateDTO implements Serializable {

    private Long id;

    private String name;

    private LayoutType layoutType;

    private String cssContent;

    private String templateContent;

    private String description;
    
    private String uploadedFilename;

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

    public LayoutType getLayoutType() {
        return layoutType;
    }

    public void setLayoutType(LayoutType layoutType) {
        this.layoutType = layoutType;
    }

    public String getCssContent() {
        return cssContent;
    }

    public void setCssContent(String cssContent) {
        this.cssContent = cssContent;
    }

    public String getTemplateContent() {
        return templateContent;
    }

    public void setTemplateContent(String templateContent) {
        this.templateContent = templateContent;
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

        HtmlTemplateDTO htmlTemplateDTO = (HtmlTemplateDTO) o;
        if(htmlTemplateDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), htmlTemplateDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "HtmlTemplateDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", layoutType='" + getLayoutType() + "'" +
            ", cssContent='" + getCssContent() + "'" +
            ", templateContent='" + getTemplateContent() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }

	public String getUploadedFilename() {
		return uploadedFilename;
	}

	public void setUploadedFilename(String uploadedFilename) {
		this.uploadedFilename = uploadedFilename;
	}
}
