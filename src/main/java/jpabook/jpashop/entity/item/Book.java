package jpabook.jpashop.entity.item;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Data;

@Entity
@Data
@DiscriminatorValue("B")
public class Book extends Items{
	private String director;
	private String actor;
}
