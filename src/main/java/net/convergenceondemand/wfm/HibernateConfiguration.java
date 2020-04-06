/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.convergenceondemand.wfm;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.util.Properties;
import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author Ojo Sola Rachael
 */
@Configuration
@EnableTransactionManagement
@ComponentScan({"net.convergenceondemand.wfm"})
@PropertySource(value = {"file:/var/config/wfmfiles/application.properties"})
//@PropertySource(value = {"file:C:/usr/share/wfm/application.properties"})
public class HibernateConfiguration {

    @Autowired
    private Environment environment;

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(c3poDataSource());
        sessionFactory.setPackagesToScan(new String[]{"net.convergenceondemand.wfm"});
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    @Bean
    @Primary
    public DataSource c3poDataSource() {
//        try {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        //dataSource.setDriverClass(environment.getRequiredProperty("jdbc.driverClassName"));
        dataSource.setJdbcUrl(environment.getRequiredProperty("jdbc.url"));
        dataSource.setUser(environment.getRequiredProperty("jdbc.username"));
        dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
        dataSource.setTestConnectionOnCheckin(true);
        dataSource.setTestConnectionOnCheckout(true);
        dataSource.setInitialPoolSize(10);
        dataSource.setMinPoolSize(10);
        dataSource.setMaxPoolSize(100);
        dataSource.setMaxStatements(100);
        dataSource.setUnreturnedConnectionTimeout(120);
        //dataSource.setDebugUnreturnedConnectionStackTraces(true);

        return dataSource;
//        } catch (PropertyVetoException ex) {
//            Logger.getLogger(HibernateConfiguration.class.getName()).log(Level.SEVERE, null, ex);
//            System.out.println("datasource initialisation issues");
//            return null;
//        }
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", true);
        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
        properties.put("hibernate.jdbc.batch_size", "5000");
        properties.put("hibernate.order_inserts", "true");
        return properties;
    }

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory s) {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(s);
        return txManager;
    }

}
