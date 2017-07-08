package com.topica.tea.service.dto;


import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * A DTO for the ProductHtmlTemplate entity.
 */
@Setter
@Getter
public class ProductHtmlTemplateDTO implements Serializable {

    private Long id;

    private String name;

    private Long productId;

    private Long htmlTemplateId;

    private String description;
    
    private ProductDTO productDTO;
    
    private HtmlTemplateDTO htmlTemplateDTO;
}
