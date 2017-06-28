package com.topica.tea.repository;

import com.topica.tea.domain.EventLevelPriorityGroup;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the EventLevelPriorityGroup entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EventLevelPriorityGroupRepository extends JpaRepository<EventLevelPriorityGroup,Long> {

}
