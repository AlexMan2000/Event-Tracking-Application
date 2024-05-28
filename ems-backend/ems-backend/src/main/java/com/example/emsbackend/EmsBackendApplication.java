package com.example.emsbackend;


import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.concurrent.atomic.AtomicLong;

@SpringBootApplication
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
