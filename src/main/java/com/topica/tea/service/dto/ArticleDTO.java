package com.topica.tea.service.dto;


import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

/**
 * A DTO for the Article entity.
 */
@Getter
@Setter
public class ArticleDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
    private Long id;

    @NotNull
    private String title;

    private Integer type;

    private Integer status;

    private String imageFilename;

    private String videoLink;
    
    private String videoCaption;
    
    // 1
    private String content1;
    private String subject1;
    private String urlLink1;
    private String imageLink1;

    // 2
    private String content2;
    private String subject2;
    private String urlLink2;
    private String imageLink2;
    
    // 3
    private String content3;
    private String subject3;
    private String urlLink3;
    private String imageLink3;
}
