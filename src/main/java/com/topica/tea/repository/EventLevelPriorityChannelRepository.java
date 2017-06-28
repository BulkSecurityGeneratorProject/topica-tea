package com.topica.tea.repository;

import com.topica.tea.domain.EventLevelPriorityChannel;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the EventLevelPriorityChannel entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EventLevelPriorityChannelRepository extends JpaRepository<EventLevelPriorityChannel,Long> {

}
