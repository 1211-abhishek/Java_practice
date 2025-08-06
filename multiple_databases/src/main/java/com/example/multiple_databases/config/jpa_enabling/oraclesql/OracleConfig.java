package com.example.multiple_databases.config.jpa_enabling.oraclesql;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

    @Configuration
    @EnableJpaRepositories(
            basePackages = "com.example.multiple_databases.repositories.oraclerepo",
            entityManagerFactoryRef = "oracleLocalContainerEntityManagerFactoryBean",
            transactionManagerRef = "oraclePlatformTransactionManager"
    )
    public class OracleConfig {

        @Bean
        @ConfigurationProperties(prefix = "spring.datasource.oraclesql")
        public DataSourceProperties oracleSqlDataSourceProperties(){

            return new DataSourceProperties();
        }

        @Bean
        public DataSource oraclesqlDataSource(@Qualifier("oracleSqlDataSourceProperties") DataSourceProperties dataSourceProperties){

            DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
            driverManagerDataSource.setUrl(dataSourceProperties.getUrl());
            driverManagerDataSource.setUsername(dataSourceProperties.getUsername());
            driverManagerDataSource.setPassword(dataSourceProperties.getPassword());
            driverManagerDataSource.setDriverClassName(dataSourceProperties.getDriverClassName());

            return driverManagerDataSource;
        }

        @Bean
        public JpaVendorAdapter oracleJpaVendorAdapter(){

            HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
            hibernateJpaVendorAdapter.setShowSql(true);
            hibernateJpaVendorAdapter.setGenerateDdl(true);
            return hibernateJpaVendorAdapter;
        }

        @Bean
        public LocalContainerEntityManagerFactoryBean oracleLocalContainerEntityManagerFactoryBean(@Qualifier("oraclesqlDataSource") DataSource dataSource, @Qualifier("oracleJpaVendorAdapter") JpaVendorAdapter jpaVendorAdapter){

            LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
            localContainerEntityManagerFactoryBean.setDataSource(dataSource);
            localContainerEntityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);
            localContainerEntityManagerFactoryBean.setPackagesToScan("com.example.multiple_databases.entity.oraclesql");
            return localContainerEntityManagerFactoryBean;
        }

        @Bean
        public PlatformTransactionManager oraclePlatformTransactionManager(@Qualifier("oracleLocalContainerEntityManagerFactoryBean") LocalContainerEntityManagerFactoryBean localEntityManagerFactoryBean){

            assert localEntityManagerFactoryBean.getObject() != null;
            return new JpaTransactionManager(localEntityManagerFactoryBean.getObject());
        }
    }
