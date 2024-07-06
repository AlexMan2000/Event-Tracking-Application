package com.example.emsbackend;


import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import java.util.concurrent.atomic.AtomicLong;

@SpringBootApplication
//@EntityScan(basePackages = {"com.example.emsbackend.entity.primary", "com.example.emsbackend.entity.secondary"})
public class EmsBackendApplication {


	/* Inject beans */

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	};

	@Bean
	public AtomicLong getTimeCounter() { return new AtomicLong(); }

	public static void main(String[] args) {
		SpringApplication.run(EmsBackendApplication.class, args);
	}

}
