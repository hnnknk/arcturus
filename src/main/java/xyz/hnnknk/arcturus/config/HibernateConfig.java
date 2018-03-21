package xyz.hnnknk.arcturus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class HibernateConfig {

    @Bean
    public HibernateTransactionManager getTransactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(hibernateSessionFactory(dataSource()).getObject());
        return transactionManager;
    }

    @Bean
    public LocalSessionFactoryBean hibernateSessionFactory(DataSource dataSource) {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan(new String[] {"xyz.hnnknk.arcturus.model"});
        sessionFactory.setHibernateProperties(additionalProperties());
        return sessionFactory;
    }


    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.hsqldb.jdbcDriver");
        dataSource.setUrl("jdbc:hsqldb:file:C:/hsqldb/public");
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        return dataSource;
    }

    private Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.archive.autodetection", "class,hbm");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");

        properties.setProperty("hibernate.hikari.connectionTimeout", "20000");
        properties.setProperty("hibernate.hikari.minimumIdle", "10");
        properties.setProperty("hibernate.hikari.maximumPoolSize", "20");
        properties.setProperty("hibernate.hikari.idleTimeout", "300000");

        return properties;
    }
}
