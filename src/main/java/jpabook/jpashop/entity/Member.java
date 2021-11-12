package jpabook.jpashop.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.MetaValue;

import lombok.Data;

@Entity
@Table(name="member")
@Data
public class Member {
	
	@Id @GeneratedValue 
	@Column(name = "member_id")
	private Long id;
	
	@NotEmpty(message = "이름은 필수로 넣어야 합니다.")
	private String name;
	
	@Embedded
	private Address address;
	
	@OneToMany(mappedBy = "member")
	private List<Order> orders = new ArrayList<>();
}
