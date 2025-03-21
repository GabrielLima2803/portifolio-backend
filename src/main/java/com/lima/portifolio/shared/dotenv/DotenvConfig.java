package com.lima.portifolio.shared.dotenv;

import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.cdimascio.dotenv.Dotenv;

@Configuration
public class DotenvConfig {

    @Bean
    public ServletContextInitializer servletContextInitializer() {
        return servletContext -> {
            String activeProfile = System.getProperty("spring.profiles.active", "default");
            
            if (!"test".equalsIgnoreCase(activeProfile)) {
                Dotenv dotenv = Dotenv.configure().load();
                System.setProperty("DB_URL", dotenv.get("DB_URL"));
                System.setProperty("DB_USERNAME", dotenv.get("DB_USERNAME"));
                System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));
                System.setProperty("MAIL_USERNAME", dotenv.get("MAIL_USERNAME"));
                System.setProperty("MAIL_SENDER", dotenv.get("MAIL_SENDER"));
                System.setProperty("MAIL_PASSWORD", dotenv.get("MAIL_PASSWORD"));
            }
        };
    }
}
