package com.williams.multipledatasource.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.williams.multipledatasource.external.repository",
        entityManagerFactoryRef = "secondEntityManagerFactory"
)
public class SecondConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.secondary")
    public DataSourceProperties topicsDataSourceProperties() {
        return new DataSourceProperties();
    }
    @Bean(name = "datasource2")
    public DataSource todosDataSource() {
        return topicsDataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }
    @Bean(name = "secondEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean defaultEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("datasource2") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("com.williams.multipledatasource.external.model")
                .persistenceUnit("secondary")
                .build();
    }

    @Bean(name = "transactionManagerForDataSource2")
    public PlatformTransactionManager transactionManagerForDataSource1(
            @Qualifier("secondEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
