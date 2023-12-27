package com.biswajit.identity.authservice.baseconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ApplicationConfiguration {
    @Bean
    PasswordEncoder bcryptPasswordEndcoder() {
        return new BCryptPasswordEncoder();
    }

}
