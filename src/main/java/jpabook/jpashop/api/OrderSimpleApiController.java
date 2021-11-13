package jpabook.jpashop.api;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jpabook.jpashop.entity.Address;
import jpabook.jpashop.entity.Order;
import jpabook.jpashop.entity.OrderSearch;
import jpabook.jpashop.entity.OrderStatus;
import jpabook.jpashop.repository.OrderRepository;
import lombok.Data;

@RestController
public class OrderSimpleApiController {
	@Autowired
	OrderRepository orderRepository;
	
	
	@GetMapping("/api/v2/simple-orders")
	public List<SimpleOrderDto> ordersV2(){
		List<Order> orders = orderRepository.findAllByString(new OrderSearch());
		
		List<SimpleOrderDto> result = orders.stream()
				.map(o->new SimpleOrderDto(o))
				.collect(Collectors.toList());
		
		return result;
	}
	
	@GetMapping("/api/v3/simple-orders")
	public List<SimpleOrderDto> orderV3(){
		List<Order> orders = orderRepository.findAllWithMemberDelivery();
		
		return orders.stream()
			.map(o->new SimpleOrderDto(o))
			.collect(Collectors.toList());
	}
	
	
	@Data
	static class SimpleOrderDto{
		private Long orderId;
		private String name;
		private LocalDateTime orderDate;
		private OrderStatus orderStatus;
		private Address address;
		
		public SimpleOrderDto(Order order) {
			orderId = order.getId();
			name = order.getMember().getName();
			orderDate = order.getOrderDate();
			orderStatus = order.getStatus();
			address = order.getDelivery().getAddress();
		}
	}

}
