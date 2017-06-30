package com.topica.tea.repository;

import com.topica.tea.domain.BrandkeyProduct;
import org.springframework.stereotype.Repository;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;


/**
 * Spring Data JPA repository for the BrandkeyProduct entity.
 */
@SuppressWarnings("unused")
public class BrandkeyProductRepositoryImpl implements BrandkeyProductRepositoryExtend {

	@PersistenceContext
    private EntityManager entityManager;
	
	private JpaEntityInformation<BrandkeyProduct, ?> entityInformation;
	
	@PostConstruct
    public void postConstruct() {
        this.entityInformation = JpaEntityInformationSupport.getEntityInformation(BrandkeyProduct.class, entityManager);
    }
	
	@Override
	public List<BrandkeyProduct> saveAll(List<BrandkeyProduct> brandkeyProducts) {
		entityManager.getTransaction().begin();
		
		for (BrandkeyProduct brandkeyProduct : brandkeyProducts) {
			entityManager.persist(brandkeyProduct);
		}
		
		entityManager.getTransaction().commit();
		return brandkeyProducts;
	}
}
