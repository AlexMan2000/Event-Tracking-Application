package com.example.emsbackend.config.database.mysql;


import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.example.emsbackend.repository.users",
        entityManagerFactoryRef = "userEntityManagerFactory",
        transactionManagerRef = "userTransactionManager"
)
public class UserRepositoryConfig {

    @Primary
    @Bean(name = "userDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.users")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    /**
     *
     * @param builderFactory
     * @param dataSource
     * @return
     *
     * 建造者模式和工厂模式混合
     */
    @Bean(name = "userEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean primaryEntityManagerFactory(
            EntityManagerFactoryBuilder builderFactory, @Qualifier("userDataSource") DataSource dataSource) {
        return builderFactory
                .dataSource(dataSource)
                .packages("com.example.emsbackend.entity.users")  // package to scan for entities
                .persistenceUnit("user")
                .build();
    }

    @Primary
    @DependsOn("userEntityManagerFactory")
    @Bean(name = "userTransactionManager")
    public PlatformTransactionManager primaryTransactionManager(
            @Qualifier("userEntityManagerFactory") EntityManagerFactory primaryEntityManagerFactory) {
        return new JpaTransactionManager(primaryEntityManagerFactory);
    }

}
