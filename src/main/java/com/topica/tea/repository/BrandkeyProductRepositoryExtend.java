package com.topica.tea.repository;

import com.topica.tea.domain.BrandkeyProduct;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the BrandkeyProduct entity.
 */
@SuppressWarnings("unused")
public interface BrandkeyProductRepositoryExtend {
	public List<BrandkeyProduct> saveAll(List<BrandkeyProduct> brandkeyProducts);
}
