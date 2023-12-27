package com.biswajit.identity.authservice.user;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

import javax.sql.DataSource;
import java.util.Map;

@Configuration
public class UserConfiguration {

//    @Bean
//    UserDetailsService inMemoryUserDetailsService() {
//        User.UserBuilder userBuilder = User.builder();
//        return new InMemoryUserDetailsManager(
//                userBuilder.username("hanika")
//                        .password("{bcrypt}$2a$10$MF0Gxa/NxOxhq5vK.2oPa.qKJYvtkjdu4Ay6cv0MEqqAqPNibQJCK").roles("USER").build(),
//                userBuilder.username("manika")
//                        .password("{bcrypt}$2a$10$apys5L1ZWbI.va5MzkLtEe3Cp1udm7zGM0rVsw/VfFQZZeOKop0eW").roles("USER", "ADMIN").build()
//        );
//    }

    @Bean
    JdbcUserDetailsManager userDetailsManager(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    ApplicationRunner userRunner(PasswordEncoder passwordEncoder, UserDetailsManager userDetailsManager) {

        return args -> {
            var builder = User.builder().roles("USER");
            var users = Map.of("hanika", passwordEncoder.encode("pihu"),
                    "manika", passwordEncoder.encode("kuhu"));
            users.forEach((username, password) -> {
                if (!userDetailsManager.userExists(username)) {
                    var user = builder.username(username).password(password).build();
//                    todo - jdbc manager that saves into database
                    userDetailsManager.createUser(user);
                }
            });
        };
    }
}
