package com.williams.multipledatasource.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.williams.multipledatasource.repository",
        entityManagerFactoryRef = "defaultEntityManagerFactory"
)
public class FirstConfig {

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.primary")
    public DataSourceProperties todosDataSourceProperties() {
        return new DataSourceProperties();
    }
    @Bean(name = "datasource1")
    @Primary
    public DataSource topicsDataSource() {
        return todosDataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }
    @Primary
    @Bean(name = "defaultEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean defaultEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("datasource1") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("com.williams.multipledatasource.model")
                .persistenceUnit("default")
                .build();
    }

    @Primary
    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManagerForDataSource1(
            @Qualifier("defaultEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

}
