package com.thiago.fruitmanagementsystem.Security.Filter;

import com.thiago.fruitmanagementsystem.Repository.UserRepository;
import com.thiago.fruitmanagementsystem.Service.AuthenticationService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.security.sasl.AuthenticationException;
import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final AuthenticationService authenticationService;
    private final UserRepository userRepository;

    public SecurityFilter(AuthenticationService authenticationService, UserRepository userRepository) {
        this.authenticationService = authenticationService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = recuperaToken(request);
        if (token != null) {
            token = token.replace("Bearer ", "").trim();
        }

        if (token != null) {
            String subject = null;

            subject = authenticationService.getSubject(token);

            var user = userRepository.findByEmailEqualsIgnoreCase(subject);

            if (user == null) {
                throw new AuthenticationException("Falha na autenticação");
            }

            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private String recuperaToken(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }
}