package jpabook.jpashop;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Jpashop11Application {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(Jpashop11Application.class, args);
	}

	
}
