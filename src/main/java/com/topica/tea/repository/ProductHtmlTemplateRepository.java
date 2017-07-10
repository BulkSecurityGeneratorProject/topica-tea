package com.topica.tea.repository;

import com.topica.tea.domain.ProductHtmlTemplate;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the ProductHtmlTemplate entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProductHtmlTemplateRepository extends JpaRepository<ProductHtmlTemplate,Long> {

	ProductHtmlTemplate findOneByProductIdAndHtmlTemplateId(Long productId, Long htmlTemplateId);
	
	ProductHtmlTemplate findOneByChannelProductIdAndHtmlTemplateId(Long channelProductId, Long htmlTemplateId);
}
