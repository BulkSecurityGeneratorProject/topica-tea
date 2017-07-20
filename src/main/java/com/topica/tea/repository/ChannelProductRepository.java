package com.topica.tea.repository;

import com.topica.tea.domain.ChannelProduct;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the ChannelProduct entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ChannelProductRepository extends JpaRepository<ChannelProduct,Long> {
	List<ChannelProduct> findAllByProductId(Long productId);
}
