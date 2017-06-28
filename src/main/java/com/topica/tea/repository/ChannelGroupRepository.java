package com.topica.tea.repository;

import com.topica.tea.domain.ChannelGroup;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the ChannelGroup entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ChannelGroupRepository extends JpaRepository<ChannelGroup,Long> {

}
