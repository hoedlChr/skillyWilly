package org.backend.skillywilly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point for the Backend Application.
 *
 * This class is annotated with @SpringBootApplication, which serves as a
 * convenience annotation that adds all of the following:
 * - @Configuration: Tags the class as a source of bean definitions.
 * - @EnableAutoConfiguration: Tells Spring Boot to start adding beans based on classpath settings and other variables.
 * - @ComponentScan: Enables scanning for components, configurations, and services in the package of the application.
 *
 * The main method uses SpringApplication.run() to launch the application.
 */
@SpringBootApplication
public class BackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

}
