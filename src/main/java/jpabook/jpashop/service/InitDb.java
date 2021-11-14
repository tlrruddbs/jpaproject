package jpabook.jpashop.service;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import jpabook.jpashop.entity.Address;
import jpabook.jpashop.entity.Delivery;
import jpabook.jpashop.entity.Member;
import jpabook.jpashop.entity.Order;
import jpabook.jpashop.entity.OrderItem;
import jpabook.jpashop.entity.item.Book;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class InitDb {
	
	private final InitService initService;
	
	@PostConstruct
	public void init() {
		initService.dbInit();
		initService.dbInit2();
	}
	
	@Transactional
	@Component
	@RequiredArgsConstructor
	static class InitService{
		private final EntityManager em;
		public void dbInit() {
			Member member = new Member();
			member.setName("userA");
			member.setAddress(new Address("서울", "1", "1111"));
			em.persist(member);
			
			Book book1 = new Book();
			book1.setName("jpa1 book");
			book1.setPrice(10000);
			book1.setStockQuantity(100);
			em.persist(book1);
			
			Book book2 = new Book();
			book2.setName("jpa2 book");
			book2.setPrice(20000);
			book2.setStockQuantity(100);
			em.persist(book2);
			
			OrderItem orderItem1 = OrderItem.createOrderItem(book1, 10000, 1);
			OrderItem orderItem2 = OrderItem.createOrderItem(book2, 20000, 2);
			
			Delivery delivery = new Delivery();
			delivery.setAddress(member.getAddress());
			Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
			em.persist(order);
		}
		public void dbInit2() {
			Member member = new Member();
			member.setName("userB");
			member.setAddress(new Address("서울", "1", "1111"));
			em.persist(member);
			
			Book book1 = new Book();
			book1.setName("SPRING1 book");
			book1.setPrice(20000);
			book1.setStockQuantity(200);
			em.persist(book1);
			
			Book book2 = new Book();
			book2.setName("SPRING2 book");
			book2.setPrice(40000);
			book2.setStockQuantity(300);
			em.persist(book2);
			
			OrderItem orderItem1 = OrderItem.createOrderItem(book1, 20000, 3);
			OrderItem orderItem2 = OrderItem.createOrderItem(book2, 40000, 4);
			
			Delivery delivery = new Delivery();
			delivery.setAddress(member.getAddress());
			Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
			em.persist(order);
		}
	}
}
