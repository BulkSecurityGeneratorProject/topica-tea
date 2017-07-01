package com.topica.tea.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.topica.tea.domain.Authority;

/**
 * Spring Data JPA repository for the Authority entity.
 */
public interface AuthorityRepository extends JpaRepository<Authority, String> {
	Authority findOneByName(String name);
}
