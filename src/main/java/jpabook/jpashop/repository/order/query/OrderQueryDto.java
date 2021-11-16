package jpabook.jpashop.repository.order.query;

import java.time.LocalDateTime;
import java.util.List;

import jpabook.jpashop.entity.Address;
import jpabook.jpashop.entity.OrderStatus;
import lombok.Data;

@Data
public class OrderQueryDto {
	private Long orderId;
	private String name;
	private LocalDateTime orderDate;
	private OrderStatus orderStatus;
	private Address address;
	private List<OrderItemQueryDto> orderItems;
}
