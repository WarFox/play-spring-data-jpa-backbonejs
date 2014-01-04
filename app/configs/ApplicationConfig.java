package configs;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.inject.Named;
import javax.inject.Singleton;

/**
 * Application configuration where we specify packages to scan
 * and configure other beans.
 */
@Named
@Singleton
@Configuration
@ComponentScan({"controllers", "services"})
public class ApplicationConfig {
}
