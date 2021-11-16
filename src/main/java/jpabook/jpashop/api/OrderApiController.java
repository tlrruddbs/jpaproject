package jpabook.jpashop.api;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jpabook.jpashop.entity.Address;
import jpabook.jpashop.entity.Order;
import jpabook.jpashop.entity.OrderItem;
import jpabook.jpashop.entity.OrderSearch;
import jpabook.jpashop.entity.OrderStatus;
import jpabook.jpashop.repository.OrderRepository;
import jpabook.jpashop.repository.order.query.OrderQueryRepository;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class OrderApiController {
	private final OrderRepository orderRepository;
	private final OrderQueryRepository orderQueryRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	@GetMapping("/api/v1/orders")
	public List<Order> ordersV1(){
		List<Order> all = orderRepository.findAllByString(new OrderSearch());
		for(Order order : all) {
			order.getMember().getName();
			order.getDelivery().getAddress();
			List<OrderItem> orderItems = order.getOrderItems();
			for(OrderItem orderItem : orderItems) {
				orderItem.getItem().getName();
			}
		}
		return all;
	}
	
	@GetMapping("/api/v2/orders")
	public List<OrderDto> ordersV2(){
		List<Order> orders = orderRepository.findAllByString(new OrderSearch());
		List<OrderDto> collect = orders.stream()
				.map(o-> new OrderDto(o))
				.collect(Collectors.toList());
		
		return collect;
	}
	
	@GetMapping("/api/v3/orders")
	public List<OrderDto> ordersV3(){
		List<Order> orders = orderRepository.findAllWithItem();
		List<OrderDto> result = orders.stream()
				.map(o-> new OrderDto(o))
				.collect(Collectors.toList());
		return result;
	}
	
	@GetMapping("/api/v3_1/orders")
	public List<OrderDto> orderstests(
			@RequestParam(value = "offset", defaultValue = "0") int offset,
			@RequestParam(value = "limit", defaultValue = "100") int limit) {
		List<Order> orders = orderRepository.findAllWithMemberDelivery(offset, limit);
		List<OrderDto> result = orders.stream()
				.map(o-> new OrderDto(o))
				.collect(Collectors.toList());

		return result;
	}
	
	@GetMapping("/api/v3_2/orders")
	public List<OrderDto> orderstests2(
			@RequestParam(value = "offset", defaultValue = "0") int offset,
			@RequestParam(value = "limit", defaultValue = "100") int limit) {
		List<Order> orders = orderRepository.findAllWithMemberDelivery(offset, limit);
		List<OrderDto> result = Arrays.asList(modelMapper.map(orders, OrderDto[].class));
		return result;
	}
	
	@GetMapping("/api/v3_3/orders")
	public List<OrderDto> orderstests3(
			@RequestParam(value = "offset", defaultValue = "0") int offset,
			@RequestParam(value = "limit", defaultValue = "100") int limit) {
		List<Order> orders = orderRepository.findAllWithMemberDelivery(offset, limit);
		List<OrderDto> orderDto = new ArrayList<>();
		
		orders.forEach(o->{
			orderDto.add(new OrderDto(o));
		});
		return orderDto;
	}

	@Data
	static class OrderDto{
		private Long orderId;
		private String name;
		private LocalDateTime orderDate;
		private OrderStatus orderStatus;
		private Address address;
		private List<OrderItemDto> orderItems;
		
		public OrderDto(Order order) {
			orderId = order.getId();
			name = order.getMember().getName();
			orderDate = order.getOrderDate();
			orderStatus = order.getStatus();
			address = order.getDelivery().getAddress();
			
			for (OrderItem orderItem : order.getOrderItems()) {
				orderItem.getItem().getName();
			}
			
			//orderItems = order.getOrderItems();
			orderItems = order.getOrderItems().stream()
					.map(d -> new OrderItemDto(d))
					.collect(Collectors.toList());
		}
	}
	
	@Getter
	static class OrderItemDto{
		private String name;
		private int orderPrice;
		private int count;
		
		public OrderItemDto(OrderItem orderItem) {
			name = orderItem.getItem().getName();
			orderPrice = orderItem.getOrderPrice();
			count = orderItem.getCount();
		}
	}
}
