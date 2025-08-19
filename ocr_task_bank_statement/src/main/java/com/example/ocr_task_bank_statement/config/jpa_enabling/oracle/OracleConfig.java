package com.example.ocr_task_bank_statement.config.jpa_enabling.oracle;

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
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.example.ocr_task_bank_statement.repository.oraclerepo",
        entityManagerFactoryRef = "oracleEntityManager",
        transactionManagerRef = "oraclePlatformTransactionManager"
)
public class OracleConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.oracle")
    public DataSourceProperties oracleDataSourceProperties(){
        return new DataSourceProperties();
    }

    @Bean
    public DataSource oracleDataSource(@Qualifier("oracleDataSourceProperties") DataSourceProperties dataSourceProperties){

        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setUrl(dataSourceProperties.getUrl());
        driverManagerDataSource.setUsername(dataSourceProperties.getUsername());
        driverManagerDataSource.setPassword(dataSourceProperties.getPassword());
        driverManagerDataSource.setDriverClassName(dataSourceProperties.getDriverClassName());

        return driverManagerDataSource;
    }

    @Bean
    public JpaVendorAdapter oracleJpaVendorAdaptor(){

        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setShowSql(true);
        jpaVendorAdapter.setGenerateDdl(true);
        return jpaVendorAdapter;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean oracleEntityManager(@Qualifier("oracleDataSource") DataSource dataSource,@Qualifier("oracleJpaVendorAdaptor") JpaVendorAdapter jpaVendorAdapter){

        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        localContainerEntityManagerFactoryBean.setPackagesToScan("com.example.ocr_task_bank_statement.entity.oracleentity");
        localContainerEntityManagerFactoryBean.setDataSource(dataSource);
        localContainerEntityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);
        return localContainerEntityManagerFactoryBean;
    }

    @Bean
    public PlatformTransactionManager oraclePlatformTransactionManager(@Qualifier("oracleEntityManager") LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean){

        assert localContainerEntityManagerFactoryBean.getObject() != null;
        return new JpaTransactionManager(localContainerEntityManagerFactoryBean.getObject());
    }
}
