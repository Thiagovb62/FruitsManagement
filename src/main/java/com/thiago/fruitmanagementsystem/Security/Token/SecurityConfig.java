package com.thiago.fruitmanagementsystem.Security.Token;

import com.thiago.fruitmanagementsystem.Security.Filter.SecurityFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
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
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        auth -> {
                            auth
                                    .requestMatchers("/historicoVenda/**").authenticated()
                                    .requestMatchers("/frutas/**").authenticated()
                                    .requestMatchers("/venda/**").authenticated()
                                    .requestMatchers("/user/login").permitAll()
                                    .requestMatchers("/user/register").permitAll()
                                    .requestMatchers(
                                                    "/v1/api/**",
                                                    "/v2/api-docs",
                                                    "/v3/api-docs",
                                                    "/v3/api-docs/**",
                                                    "/swagger-resources",
                                                    "/swagger-resources/**",
                                                    "/configuration/ui",
                                                    "/configuration/security",
                                                    "/swagger-ui/**",
                                                    "/webjars/**",
                                                    "/swagger-ui.html/**"
                                            ).permitAll();

                        })
         .addFilterBefore(securityFilter, BasicAuthenticationFilter.class);
        return http.build();
    }

}