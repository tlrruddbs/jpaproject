package jpabook.jpashop.entity.item;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Data;

@Entity
@Data
@DiscriminatorValue("M")
public class Movie extends Items {
	private String director;
	private String actor;
}
