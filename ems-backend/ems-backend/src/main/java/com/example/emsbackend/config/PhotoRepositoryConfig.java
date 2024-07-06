package com.example.emsbackend.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.example.emsbackend.repository.photos",
        entityManagerFactoryRef = "photoEntityManagerFactory",
        transactionManagerRef = "photoTransactionManager"
)
public class PhotoRepositoryConfig {
    @Bean(name = "photoDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.photos")
    public DataSource secondaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "photoEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean secondaryEntityManagerFactory(
            EntityManagerFactoryBuilder builderFactory, @Qualifier("photoDataSource") DataSource dataSource) {
        return builderFactory
                .dataSource(dataSource)
                .packages("com.example.emsbackend.entity.photos")  // package to scan for entities
                .persistenceUnit("photo")
                .build();
    }

    @Bean(name = "photoTransactionManager")
    public PlatformTransactionManager secondaryTransactionManager(
            @Qualifier("photoEntityManagerFactory") EntityManagerFactory secondaryEntityManagerFactory) {
        return new JpaTransactionManager(secondaryEntityManagerFactory);
    }
}

