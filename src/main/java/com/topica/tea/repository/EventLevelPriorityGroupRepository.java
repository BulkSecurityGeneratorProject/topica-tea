package com.topica.tea.repository;

import com.topica.tea.domain.EventLevelPriorityGroup;
import com.topica.tea.domain.enumeration.EventLevel;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the EventLevelPriorityGroup entity.
 */
@Repository
public interface EventLevelPriorityGroupRepository extends JpaRepository<EventLevelPriorityGroup,Long> {
	List<EventLevelPriorityGroup> findAllByIsMeatContent(boolean isMeatContent);
	
	List<EventLevelPriorityGroup> findAllByIsMeatContentAndEventLevel(boolean isMeatContent, EventLevel event);
}
