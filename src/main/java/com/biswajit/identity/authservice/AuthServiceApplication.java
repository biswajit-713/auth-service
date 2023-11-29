package com.biswajit.identity.authservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@SpringBootApplication
public class AuthServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthServiceApplication.class, args);
	}

	@Bean
	UserDetailsService inMemoryUserDetailsService() {
		User.UserBuilder userBuilder = User.builder();
		return new InMemoryUserDetailsManager(
				userBuilder.username("hanika")
						.password("{bcrypt}$2a$10$MF0Gxa/NxOxhq5vK.2oPa.qKJYvtkjdu4Ay6cv0MEqqAqPNibQJCK").roles("USER").build(),
				userBuilder.username("manika")
						.password("{bcrypt}$2a$10$apys5L1ZWbI.va5MzkLtEe3Cp1udm7zGM0rVsw/VfFQZZeOKop0eW").roles("USER", "ADMIN").build()
		);
	}
}
