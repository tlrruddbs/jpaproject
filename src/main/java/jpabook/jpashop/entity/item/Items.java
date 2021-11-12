package jpabook.jpashop.entity.item;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Data;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) //상속 전략
@DiscriminatorColumn(name = "dtype") //book이면 어떻게할꺼야
@Data
public abstract class Items {
	@Id @GeneratedValue
	@Column(name = "item_id")
	private Long id;
	private String name;
	private int price;
	private int stockQuantity;
	
	//비즈니스 로직 (재고 늘고 줄고) 도메인 주도  설계 (엔티티가 해결할 수 있는건 엔티티안에 넣어야됨)
	public void addStock(int quantity) {
		this.stockQuantity += quantity;
	}
	
	public void removeStock(int quantity) {
		int result = this.stockQuantity - quantity; 
		if(result < 0) {
			throw new NotEnoughStockException("need more stock");
		}
		this.stockQuantity = result;
	}
}
