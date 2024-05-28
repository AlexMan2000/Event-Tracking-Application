package com.example.emsbackend;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.spi.PersistenceUnitInfo;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
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
