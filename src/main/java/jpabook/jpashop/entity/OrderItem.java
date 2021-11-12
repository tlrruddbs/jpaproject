package jpabook.jpashop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import jpabook.jpashop.entity.item.Items;
import lombok.Data;

@Entity
@Table(name="ORDER_ITEM")
@Data
public class OrderItem {
	
	@Id @GeneratedValue
	@Column(name = "order_item_id")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="order_id")
	private Order order;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "item_id")
	private Items item;
	
	@Column(name = "orderprice")
	private int orderPrice;
	
	@Column(name = "count")
	private int count;
	
	//생성메서드
	public static OrderItem createOrderItem(Items item, int orderPrice, int count) {
		OrderItem orderItem = new OrderItem();
		orderItem.setItem(item);
		orderItem.setOrderPrice(orderPrice);
		orderItem.setCount(count);
		
		item.removeStock(count);
		
		return orderItem;
	}
	
	//비즈니스 로직
	public void cancel() {
		getItem().addStock(count);
	}
	
	//조회 로직
	//주문상품 전체 가격 조회
	public int getTotalPrice() {
		return getOrderPrice() * getCount();
	}
}
