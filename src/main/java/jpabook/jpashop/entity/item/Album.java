package jpabook.jpashop.entity.item;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Data;

@Entity
@Data
@DiscriminatorValue("A")
public class Album extends Items{
	private String artist;
	private String etc;
}
