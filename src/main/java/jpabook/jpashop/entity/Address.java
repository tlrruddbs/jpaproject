package jpabook.jpashop.entity;

import javax.persistence.Embeddable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
public class Address {
	private String city;
	private String street;
	private String zipCode;
	
	public Address(String city, String street, String zipCode) {
		this.city = city;
		this.street = street;
		this.zipCode = zipCode;
	}
	
	protected Address() {}
}
