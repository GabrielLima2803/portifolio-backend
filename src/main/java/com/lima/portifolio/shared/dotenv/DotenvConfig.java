package com.lima.portifolio.shared.dotenv;

import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.cdimascio.dotenv.Dotenv;

@Configuration
public class DotenvConfig {
    private static final String MAIL_USERNAME_KEY = "MAIL_USERNAME";
    private static final String MAIL_SENDER_KEY = "MAIL_SENDER";
    private static final String MAIL_PASSWORD_KEY = "MAIL_PASSWORD";

    @Bean
    public ServletContextInitializer servletContextInitializer() {
        return servletContext -> {
            String activeProfile = System.getProperty("spring.profiles.active", "default");
            
            if (!"test".equalsIgnoreCase(activeProfile)) {
                Dotenv dotenv = Dotenv.configure().load();
                System.setProperty(MAIL_USERNAME_KEY, dotenv.get(MAIL_USERNAME_KEY));
                System.setProperty(MAIL_SENDER_KEY, dotenv.get(MAIL_SENDER_KEY));
                System.setProperty(MAIL_PASSWORD_KEY, dotenv.get(MAIL_PASSWORD_KEY));
            } else {
                System.setProperty(MAIL_SENDER_KEY, "test@example.com");
            }
        };
    }
}