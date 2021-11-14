package jpabook.jpashop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;

@SpringBootApplication
public class Jpashop11Application {

	public static void main(String[] args) {
		SpringApplication.run(Jpashop11Application.class, args);
	}
	
	@Bean
	Hibernate5Module hibernate5Module() {
		return new Hibernate5Module();
	}

}
