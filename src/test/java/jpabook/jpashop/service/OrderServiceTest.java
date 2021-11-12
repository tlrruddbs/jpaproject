package jpabook.jpashop.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import jpabook.jpashop.entity.Address;
import jpabook.jpashop.entity.Member;
import jpabook.jpashop.entity.Order;
import jpabook.jpashop.entity.OrderStatus;
import jpabook.jpashop.entity.item.Book;
import jpabook.jpashop.exception.NotEnoughStockException;
import jpabook.jpashop.repository.OrderRepository;
import junit.framework.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {
	
	@Autowired
	EntityManager em;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	OrderRepository orderRepository;
	
	@Test
	public void 상품주문() throws Exception{
		Member member = new Member();
		member.setName("회원1");
		member.setAddress(new Address("서울", "2", "3"));
		em.persist(member);
		
		
		Book book = new Book();
		book.setName("jap");
		book.setPrice(10000);
		book.setStockQuantity(10);
		em.persist(book);
		
		int orderCount = 2;
		
		//when
		Long orderId = orderService.order(member.getId(), book.getId(), orderCount);
		
		//then
		Order getOrder = orderRepository.findOne(orderId);
		
		Assert.assertEquals("상품 주문 시 상태는 ORDER",OrderStatus.ORDER,getOrder.getStatus());
		Assert.assertEquals("주문한 상품 종류 수가 정확해야 한다", 1, getOrder.getOrderItems().size());
		Assert.assertEquals("ㅈ문 가격은 가격 * 수량이다", 10000 * orderCount, getOrder.getTotalPrice());
		Assert.assertEquals("주문 수량만큼 재고가 줄어야 한다", 8, book.getStockQuantity());
	}
	
	@Test
	public void 주문취소() throws Exception{
		Member member = new Member();
		member.setName("회원1");
		member.setAddress(new Address("서울", "2", "3"));
		em.persist(member);
		
		
		Book book = new Book();
		book.setName("jap");
		book.setPrice(10000);
		book.setStockQuantity(10);
		em.persist(book);
		
		int orderCount = 2;
		
		Long orderId = orderService.order(member.getId(), book.getId(), orderCount);
		
		orderService.cancelOrder(orderId);
		
		Order getOrder = orderRepository.findOne(orderId);
		
		Assert.assertEquals("주문 취소시 상태는 cancel이다.", OrderStatus.CANCEL, getOrder.getStatus());
		Assert.assertEquals("주문 취소시 재고가 증가되어야 한다.", 10, book.getStockQuantity());
	}
	
	@Test(expected = NotEnoughStockException.class)
	public void 상품주문_재고수량초과() throws Exception{
		Member member = new Member();
		member.setName("회원1");
		member.setAddress(new Address("서울", "2", "3"));
		em.persist(member);
		
		
		Book book = new Book();
		book.setName("jap");
		book.setPrice(10000);
		book.setStockQuantity(10);
		em.persist(book);
		
		int orderCount = 11;
		
		//when
		Long orderId = orderService.order(member.getId(), book.getId(), orderCount);
		
		
		Assert.fail("재고 수량 부족 예외가 발생해야 한다.");
	}
}
