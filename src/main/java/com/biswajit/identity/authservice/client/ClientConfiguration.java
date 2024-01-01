package com.biswajit.identity.authservice.client;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.JdbcRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;

import java.util.Set;
import java.util.UUID;

@Configuration
public class ClientConfiguration {
    @Bean
    RegisteredClientRepository registeredClientRepository(JdbcTemplate jdbcTemplate) {
        return new JdbcRegisteredClientRepository(jdbcTemplate);
    }

    @Bean
    ApplicationRunner clientRunner(RegisteredClientRepository repository) {
//        todo - this will be captured through a form from a developer portal where clients will register
        return args -> {
            var clientId = "crm";
            if (repository.findByClientId(clientId) == null) {
                repository.save(
                        RegisteredClient
                                .withId(UUID.randomUUID().toString())
                                .clientId(clientId)
                                .clientSecret("{bcrypt}$2a$10$E00oLdbSIrPnVkLPa.24AezV3DdQyQaH5FHrrKytIZW2r4OsAotXC")
                                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                                .authorizationGrantTypes(grantTypes -> grantTypes.addAll(Set.of(
                                        AuthorizationGrantType.CLIENT_CREDENTIALS,
                                        AuthorizationGrantType.AUTHORIZATION_CODE,
                                        AuthorizationGrantType.REFRESH_TOKEN
                                )))
//                                .redirectUri("http://127.0.0.1:8080/login/oauth2/code/spring")
                                .redirectUri("https://oauth.pstmn.io/v1/callback")
                                .scopes((scopes) -> scopes.addAll(Set.of("user.read", "user.write", OidcScopes.OPENID)))
                                .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
                                .build()
                );
            }
        };
    }
}
