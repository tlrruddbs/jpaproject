package jpabook.jpashop.repository.order.query;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class OrderQueryRepository {
	
	private final EntityManager em;
	
//	public List<OrderQueryDto> findOrderQueryDtos(){
//		em.createQuery(""
//				+ "select from Order o"
//				+ " join o.member m"
//				+ " join o.delivery d", OrderQueryDto.class)
//		.getResultList();
//	}
}
