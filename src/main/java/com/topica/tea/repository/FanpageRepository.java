package com.topica.tea.repository;

import com.topica.tea.domain.Fanpage;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Fanpage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FanpageRepository extends JpaRepository<Fanpage,Long> {

}
