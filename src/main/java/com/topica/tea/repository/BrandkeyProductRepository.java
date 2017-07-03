package com.topica.tea.repository;

import com.topica.tea.domain.BrandkeyProduct;
import com.topica.tea.domain.Question;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;


/**
 * Spring Data JPA repository for the BrandkeyProduct entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BrandkeyProductRepository extends JpaRepository<BrandkeyProduct,Long>, BrandkeyProductRepositoryExtend {
	
	@Query("select bp from BrandkeyProduct bp where bp.brandkey_id =:id")
	List<BrandkeyProduct> findAllByBrandkeyId(@Param("id") Long id);
}
