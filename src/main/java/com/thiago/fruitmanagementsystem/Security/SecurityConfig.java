package com.thiago.fruitmanagementsystem.Security;

import com.thiago.fruitmanagementsystem.Security.Filter.SecurityFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    private final SecurityFilter securityFilter;

    public SecurityConfig(SecurityFilter securityFilter) {
        this.securityFilter = securityFilter;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors(withDefaults());
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(
                        auth -> {
                            auth
                                    .requestMatchers("/historicoVenda/**").permitAll()
                                    .requestMatchers("/frutas/**").permitAll();
                        })
         .addFilterBefore(securityFilter, BasicAuthenticationFilter.class);
        return http.build();
    }
}