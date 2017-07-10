package com.topica.tea.repository;

import com.topica.tea.domain.AdsType;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the AdsType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AdsTypeRepository extends JpaRepository<AdsType,Long> {

}
