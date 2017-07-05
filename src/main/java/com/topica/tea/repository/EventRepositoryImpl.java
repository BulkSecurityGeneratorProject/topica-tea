package com.topica.tea.repository;

import com.topica.tea.config.audit.AuditEventConverter;
import com.topica.tea.domain.BrandkeyProduct;
import com.topica.tea.domain.Event;
import com.topica.tea.domain.Product;
import com.topica.tea.domain.enumeration.AmplifyType;
import com.topica.tea.domain.enumeration.EventStatus;

import groovy.util.logging.Slf4j;

import org.springframework.stereotype.Repository;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.social.connect.ConnectionFactoryLocator;


/**
 * Spring Data JPA repository for the BrandkeyProduct entity.
 */
@SuppressWarnings("unused")
public class EventRepositoryImpl implements EventRepositoryExtend {

	@PersistenceContext
    private EntityManager entityManager;
	
	private JpaEntityInformation<Event, ?> entityInformation;
	
    private ProductRepository productRepository;
    
    public EventRepositoryImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
	
	@PostConstruct
    public void postConstruct() {
        this.entityInformation = JpaEntityInformationSupport.getEntityInformation(Event.class, entityManager);
    }

	@SuppressWarnings("unchecked")
	@Override
	public Event findPublishInjectOneByProductCode(String productCode) {
		try {
			String sql = "select * from event e"
					+ " inner join event_products ep on e.id = ep.event_id"
					+ " inner join product p on ep.products_id = p.id"
					+ " inner join event_amplify_type eat on eat.event_id = e.id"
					+ " where p.product_code = :productCode"
					+ " and eat.amplify_type = :amplifyType"
					+ " and e.event_status = :eventStatus"
					+ " order by e.id desc";
			javax.persistence.Query query = entityManager.createNativeQuery(sql, Event.class);
			query.setParameter("productCode", productCode);
			query.setParameter("amplifyType", AmplifyType.INJECT.toString());
			query.setParameter("eventStatus", EventStatus.MANAGER_APPROVE.toString());
			
			List<Event> events = query.getResultList();
			if (events == null || events.size() == 0) {
				return null;
			}
			
			return events.get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
