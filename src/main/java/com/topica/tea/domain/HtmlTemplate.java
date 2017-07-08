package com.topica.tea.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

import com.topica.tea.domain.enumeration.LayoutType;

/**
 * A HtmlTemplate.
 */
@Entity
@Table(name = "html_template")
public class HtmlTemplate implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "layout_type")
    private LayoutType layoutType;

    @Column(name = "css_content")
    private String cssContent;

    @Column(name = "template_content")
    private String templateContent;

    @Column(name = "description")
    private String description;
    
    @Column(name = "uploaded_filename")
    private String uploadedFilename;

    public String getUploadedFilename() {
		return uploadedFilename;
	}

	public void setUploadedFilename(String uploadedFilename) {
		this.uploadedFilename = uploadedFilename;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public HtmlTemplate name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LayoutType getLayoutType() {
        return layoutType;
    }

    public HtmlTemplate layoutType(LayoutType layoutType) {
        this.layoutType = layoutType;
        return this;
    }

    public void setLayoutType(LayoutType layoutType) {
        this.layoutType = layoutType;
    }

    public String getCssContent() {
        return cssContent;
    }

    public HtmlTemplate cssContent(String cssContent) {
        this.cssContent = cssContent;
        return this;
    }

    public void setCssContent(String cssContent) {
        this.cssContent = cssContent;
    }

    public String getTemplateContent() {
        return templateContent;
    }

    public HtmlTemplate templateContent(String templateContent) {
        this.templateContent = templateContent;
        return this;
    }

    public void setTemplateContent(String templateContent) {
        this.templateContent = templateContent;
    }

    public String getDescription() {
        return description;
    }

    public HtmlTemplate description(String description) {
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
        HtmlTemplate htmlTemplate = (HtmlTemplate) o;
        if (htmlTemplate.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), htmlTemplate.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "HtmlTemplate{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", layoutType='" + getLayoutType() + "'" +
            ", cssContent='" + getCssContent() + "'" +
            ", templateContent='" + getTemplateContent() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
