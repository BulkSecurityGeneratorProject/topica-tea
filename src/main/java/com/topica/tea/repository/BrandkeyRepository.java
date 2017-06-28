package com.topica.tea.repository;

import com.topica.tea.domain.Brandkey;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Brandkey entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BrandkeyRepository extends JpaRepository<Brandkey,Long> {

}
