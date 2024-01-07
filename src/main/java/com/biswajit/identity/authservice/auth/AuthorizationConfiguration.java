package com.biswajit.identity.authservice.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;

// the beans here deal with the user consent asked during the user authorization
@Configuration
public class AuthorizationConfiguration {

    @Bean
    OAuth2AuthorizationConsentService jdbcAuthorizationConsentService(
            JdbcOperations operations, RegisteredClientRepository repository
    ) {
        return new JdbcOAuth2AuthorizationConsentService(operations, repository);
    }

    @Bean
    OAuth2AuthorizationService jdbcAuthorizationService(JdbcOperations operations,
                                                        RegisteredClientRepository repository) {
        return new JdbcOAuth2AuthorizationService(operations, repository);
    }
}
