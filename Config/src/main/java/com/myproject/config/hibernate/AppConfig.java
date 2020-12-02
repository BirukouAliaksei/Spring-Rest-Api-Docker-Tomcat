package com.myproject.config.hibernate;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

import static org.modelmapper.config.Configuration.AccessLevel;


@Configuration
@EnableJpaRepositories
@EnableTransactionManagement
@ComponentScan(basePackages = "com.myproject")
//@PropertySource(value = {"application.properties"})
public class AppConfig {
    //    @Value("${jdbc.driverClassName}")
    private String driverClassName = "com.mysql.jdbc.Driver";
    //    @Value("${jdbc.url}")
    private String jdbcURL = "jdbc:mysql://localhost:3306/scooterservice";
    //    @Value("${jdbc.username}")
    private String username = "root";
    //    @Value("${jdbc.password}")
    private String password = "qwerty123";
    //    @Value("${jdbc.dialect}")
    private String dialect = "hibernate.dialect";
    //    @Value("${jdbc.dialectName}")
    private String dialectName = "org.hibernate.dialect.MySQL5Dialect";

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }


    @Bean
//    @Qualifier(value = "entityManager")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean emfb =
                new LocalContainerEntityManagerFactoryBean();
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        emfb.setJpaVendorAdapter(vendorAdapter);
        emfb.setDataSource(dataSource());
        emfb.setPackagesToScan("com.myproject.domain");
        System.out.println(emfb);
        return emfb;
    }


    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(jdbcURL);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setConnectionProperties(additionalProperties());
        return dataSource;
    }

    @Bean
    Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty(dialect, dialectName);
        return properties;
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldMatchingEnabled(true)
                .setSkipNullEnabled(true)
                .setFieldAccessLevel(AccessLevel.PRIVATE);
        return modelMapper;
    }

}
