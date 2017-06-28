package com.topica.tea.repository;

import com.topica.tea.domain.BrandkeyProduct;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the BrandkeyProduct entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BrandkeyProductRepository extends JpaRepository<BrandkeyProduct,Long> {

}
