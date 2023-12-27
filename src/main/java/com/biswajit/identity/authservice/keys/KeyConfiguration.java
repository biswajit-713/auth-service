package com.biswajit.identity.authservice.keys;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

// static key generator
@Configuration
public class KeyConfiguration {
    @Bean
    JWKSource<SecurityContext> jwkSource(
            @Value("${jwt.key.id}") String id,
            @Value("${jwt.key.private}") RSAPrivateKey privateKey,
            @Value("${jwt.key.public}") RSAPublicKey publicKey
    ) {
        RSAKey rsaKey = new RSAKey.Builder(publicKey)
                .privateKey(privateKey)
                .keyID(id)
                .build();
        JWKSet jwkSet = new JWKSet(rsaKey);
        return new ImmutableJWKSet<>(jwkSet);
    }
}
