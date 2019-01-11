package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

@SpringBootApplication
@EntityScan(basePackageClasses = {
		SpringJwtDemoAppApplication.class,
		Jsr310JpaConverters.class
})
public class SpringJwtDemoAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringJwtDemoAppApplication.class, args);
	}

}

