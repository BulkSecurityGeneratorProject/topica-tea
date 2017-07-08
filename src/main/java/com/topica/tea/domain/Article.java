package com.topica.tea.domain;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

/**
 * A Article.
 */
@Entity
@Table(name = "article")
@Setter
@Getter
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "title", nullable = false)
    private String title;

    // 1
    @Column(name = "subject1")
    private String subject1;
    
    @Column(name = "content1")
    private String content1;
    
    @Column(name = "url_link1")
    private String urlLink1;
    
    @Column(name = "image_link1")
    private String imageLink1;
    
    // 2
    @Column(name = "subject2")
    private String subject2;
    
    @Column(name = "content2")
    private String content2;
    
    @Column(name = "url_link2")
    private String urlLink2;
    
    @Column(name = "image_link2")
    private String imageLink2;
    
    // 3
    @Column(name = "subject3")
    private String subject3;
    
    @Column(name = "content3")
    private String content3;
    
    @Column(name = "url_link3")
    private String urlLink3;
    
    @Column(name = "image_link3")
    private String imageLink3;

    @Column(name = "image_filename")
    private String imageFilename;
    
    @Column(name = "video_link")
    private String videoLink;
    
    @Column(name = "video_caption")
    private String videoCaption;
    
    @Column(name = "jhi_type")
    private Integer type;

    @Column(name = "status")
    private Integer status;

    public Article title(String title) {
        this.title = title;
        return this;
    }

    public Article type(Integer type) {
        this.type = type;
        return this;
    }

    public Article status(Integer status) {
        this.status = status;
        return this;
    }

    @Override
    public String toString() {
        return "Article{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", content1='" + getContent1() + "'" +
            ", type='" + getType() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
