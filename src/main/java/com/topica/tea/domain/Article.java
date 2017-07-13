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
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "title", nullable = false)
    private String title;

    // Fanpage
    @Column(name = "fanpage_content")
    private String fanpageContent;
    
    @Column(name = "fanpage_image")
    private String fanpageImage;
    
    @Column(name = "fanpage_link")
    private String fanpageLink;
    
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubject1() {
		return subject1;
	}

	public void setSubject1(String subject1) {
		this.subject1 = subject1;
	}

	public String getContent1() {
		return content1;
	}

	public void setContent1(String content1) {
		this.content1 = content1;
	}

	public String getUrlLink1() {
		return urlLink1;
	}

	public void setUrlLink1(String urlLink1) {
		this.urlLink1 = urlLink1;
	}

	public String getImageLink1() {
		return imageLink1;
	}

	public void setImageLink1(String imageLink1) {
		this.imageLink1 = imageLink1;
	}

	public String getSubject2() {
		return subject2;
	}

	public void setSubject2(String subject2) {
		this.subject2 = subject2;
	}

	public String getContent2() {
		return content2;
	}

	public void setContent2(String content2) {
		this.content2 = content2;
	}

	public String getUrlLink2() {
		return urlLink2;
	}

	public void setUrlLink2(String urlLink2) {
		this.urlLink2 = urlLink2;
	}

	public String getImageLink2() {
		return imageLink2;
	}

	public void setImageLink2(String imageLink2) {
		this.imageLink2 = imageLink2;
	}

	public String getSubject3() {
		return subject3;
	}

	public void setSubject3(String subject3) {
		this.subject3 = subject3;
	}

	public String getContent3() {
		return content3;
	}

	public void setContent3(String content3) {
		this.content3 = content3;
	}

	public String getUrlLink3() {
		return urlLink3;
	}

	public void setUrlLink3(String urlLink3) {
		this.urlLink3 = urlLink3;
	}

	public String getImageLink3() {
		return imageLink3;
	}

	public void setImageLink3(String imageLink3) {
		this.imageLink3 = imageLink3;
	}

	public String getImageFilename() {
		return imageFilename;
	}

	public void setImageFilename(String imageFilename) {
		this.imageFilename = imageFilename;
	}

	public String getVideoLink() {
		return videoLink;
	}

	public void setVideoLink(String videoLink) {
		this.videoLink = videoLink;
	}

	public String getVideoCaption() {
		return videoCaption;
	}

	public void setVideoCaption(String videoCaption) {
		this.videoCaption = videoCaption;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getFanpageContent() {
		return fanpageContent;
	}

	public void setFanpageContent(String fanpageContent) {
		this.fanpageContent = fanpageContent;
	}
	
	public String getFanpageImage() {
		return fanpageImage;
	}

	public void setFanpageImage(String fanpageImage) {
		this.fanpageImage = fanpageImage;
	}

	public String getFanpageLink() {
		return fanpageLink;
	}

	public void setFanpageLink(String fanpageLink) {
		this.fanpageLink = fanpageLink;
	}
}
