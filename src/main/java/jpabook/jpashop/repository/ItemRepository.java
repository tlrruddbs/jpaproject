package jpabook.jpashop.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import jpabook.jpashop.entity.item.Items;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ItemRepository {
	private final EntityManager em;
	
	public void save(Items item) {
		if(item.getId() == null) {
			em.persist(item);
		} else {
			em.merge(item);
		}
	}
	
	public Items findOne(Long id) {
		return em.find(Items.class, id);
	}
	
	public List<Items> findAll(){
		return em.createQuery("select i from Items i", Items.class)
				.getResultList();
	}
}

