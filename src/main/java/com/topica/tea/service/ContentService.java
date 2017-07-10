package com.topica.tea.service;

import com.topica.tea.domain.HtmlTemplate;
import com.topica.tea.domain.User;
import com.topica.tea.domain.enumeration.EventLevel;
import com.topica.tea.repository.HtmlTemplateRepository;
import com.topica.tea.repository.ProductHtmlTemplateRepository;
import com.topica.tea.service.dto.ArticleDTO;
import com.topica.tea.service.dto.EventDTO;

import io.github.jhipster.config.JHipsterProperties;

import org.apache.commons.lang3.CharEncoding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.apache.commons.lang3.StringUtils;

import javax.mail.internet.MimeMessage;
import java.util.Locale;

/**
 * Service for sending emails.
 * <p>
 * We use the @Async annotation to send emails asynchronously.
 * </p>
 */
@Service
public class ContentService {

    private final Logger log = LoggerFactory.getLogger(ContentService.class);

    private final JHipsterProperties jHipsterProperties;

    private final MessageSource messageSource;

    private final SpringTemplateEngine templateEngine;
    
    private final HtmlTemplateRepository htmlTemplateRepository;
    

    public ContentService(JHipsterProperties jHipsterProperties,
            MessageSource messageSource, SpringTemplateEngine templateEngine, HtmlTemplateRepository htmlTemplateRepository) {

        this.jHipsterProperties = jHipsterProperties;
        this.messageSource = messageSource;
        this.templateEngine = templateEngine;
        this.htmlTemplateRepository = htmlTemplateRepository;
    }

    public String process(EventDTO eventDTO, Long templateId) {
    	ArticleDTO article = eventDTO.getArticle();
    	if (null == article) {
    		return null;
    	}
    	HtmlTemplate templateEntity = htmlTemplateRepository.findOne(templateId);
    	if (null == templateEntity) {
    		return null;
    	}
    	
    	
    	String cssInline = templateEntity.getCssContent();
    	String template = templateEntity.getTemplateContent();
    	
    	// Repalce @@xx@@
    	template = template.replaceAll("@@urlLink1@@", article.getUrlLink1());
    	template = template.replaceAll("@@urlLink2@@", article.getUrlLink2());
    	template = template.replaceAll("@@urlLink3@@", article.getUrlLink3());
    	
    	template = template.replaceAll("@@subject1@@", article.getSubject1());
    	template = template.replaceAll("@@subject2@@", article.getSubject2());
    	template = template.replaceAll("@@subject3@@", article.getSubject3());
    	
    	template = template.replaceAll("@@imageLink1@@", article.getImageLink1());
    	template = template.replaceAll("@@imageLink2@@", article.getImageLink2());
    	template = template.replaceAll("@@imageLink3@@", article.getImageLink3());
    	
    	template = template.replaceAll("@@content1@@", article.getContent1());
    	template = template.replaceAll("@@content2@@", article.getContent2());
    	template = template.replaceAll("@@content3@@", article.getContent3());
    	
    	template = template.replaceAll("@@videoLink@@", article.getVideoLink());
    	template = template.replaceAll("@@videoCaption@@", article.getVideoCaption());
    	// Build content
    	StringBuffer sb = new StringBuffer();
    	// Append CSS Inline
    	sb.append("<style>").append(cssInline).append("</style>");

    	// AppendContent
    	sb.append(template);
    	
    	return sb.toString();
    }
}
