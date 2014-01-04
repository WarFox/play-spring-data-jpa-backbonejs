package configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate3.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.inject.Named;
import javax.inject.Singleton;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * This configuration establishes Spring Data concerns including those of JPA.
 */
@Named
@Singleton
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"repositories"})
public class SpringDataJpaConfig {

    /**
     * The name of the persistence unit we will be using.
     */
    static final String DEFAULT_PERSISTENCE_UNIT = "default";

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        return Persistence.createEntityManagerFactory(DEFAULT_PERSISTENCE_UNIT);
    }

    @Bean
    public HibernateExceptionTranslator hibernateExceptionTranslator() {
        return new HibernateExceptionTranslator();
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        return new JpaTransactionManager();
    }

}
