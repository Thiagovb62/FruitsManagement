package com.thiago.fruitmanagementsystem.Security.Token;

import com.thiago.fruitmanagementsystem.Security.Filter.SecurityFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors(withDefaults());
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(
                        auth -> {
                            auth
                                    .requestMatchers("/historicoVenda/**").authenticated()
                                    .requestMatchers("/fruta/**").authenticated()
                                    .requestMatchers("/venda/**").authenticated()
                                    .requestMatchers("/user/login").permitAll()
                                    .requestMatchers("/user/register").permitAll();
                        })
         .addFilterBefore(securityFilter, BasicAuthenticationFilter.class);
        return http.build();
    }

}