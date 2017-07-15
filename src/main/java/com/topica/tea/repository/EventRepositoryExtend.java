package com.topica.tea.repository;

import com.topica.tea.domain.BrandkeyProduct;
import com.topica.tea.domain.Event;
import com.topica.tea.domain.enumeration.EventStatus;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;


/**
 * Spring Data JPA repository for the BrandkeyProduct entity.
 */
@SuppressWarnings("unused")
public interface EventRepositoryExtend {
	
	Event findPublishInjectOneByProductCode(String productCode);
	
	Event findPublishInjectOneByProductId(Long productId);
	
	Event findPublishInjectOneByChannelProductId(Long productId);
	
	Event findOneByEventStatus(EventStatus status);
}
