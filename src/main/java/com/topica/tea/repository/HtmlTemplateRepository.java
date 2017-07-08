package com.topica.tea.repository;

import com.topica.tea.domain.HtmlTemplate;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the HtmlTemplate entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HtmlTemplateRepository extends JpaRepository<HtmlTemplate,Long> {

}
