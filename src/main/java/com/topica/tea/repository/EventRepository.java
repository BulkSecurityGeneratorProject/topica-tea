package com.topica.tea.repository;

import com.topica.tea.domain.Event;
import com.topica.tea.domain.Question;
import com.topica.tea.domain.enumeration.EventStatus;

import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;


/**
 * Spring Data JPA repository for the Event entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EventRepository extends JpaRepository<Event,Long>, EventRepositoryExtend {

}
