package com.thiago.fruitmanagementsystem.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.thiago.fruitmanagementsystem.Enums.RoleEnum;
import com.thiago.fruitmanagementsystem.Model.UserDTO;
import com.thiago.fruitmanagementsystem.Repository.UserRepository;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.thiago.fruitmanagementsystem.Model.User;

import java.time.Duration;
import java.time.Instant;


@Service
public class AuthenticationService {

    @Value("${security.token.secret}")
    String secretKey;

    private final PasswordEncoder encoder;

    private final UserRepository userRepository;

    public AuthenticationService(PasswordEncoder encoder, UserRepository userRepository) {
        this.encoder = encoder;
        this.userRepository = userRepository;
    }

    public void CreateUser(UserDTO dto) throws AuthenticationException {
        if (dto.email().isEmpty() || dto.password().isEmpty()) {
            throw new IllegalArgumentException("Username and password are required");
        }

        var user = userRepository.findByEmailEqualsIgnoreCase(dto.email());

        if (user != null) {
            throw new AuthenticationException("User already exists");
        }

        var password = encoder.encode(dto.password());

        var newUser = new User(dto.email(), password);
        if (dto.email().equalsIgnoreCase("thiagovbAdm@gmail.com"))
         newUser.setRole(RoleEnum.ADMIN);
        else if(dto.email().equalsIgnoreCase("thiagovbfazendas@gmail.com"))
         newUser.setRole(RoleEnum.VENDEDOR);


        userRepository.save(newUser);
    }

    public String authenticate(UserDTO dto) throws AuthenticationException {
        if (dto.email().isEmpty() || dto.password().isEmpty()) {
            throw new IllegalArgumentException("Username and password are required");
        }

        var user = userRepository.findByEmailEqualsIgnoreCase(dto.email());

        if (user == null) {
            throw new AuthenticationException("Invalid username or password");
        }

        var passwordMatches = encoder.matches(dto.password(), user.getPassword());

        if (!passwordMatches) {
            throw new AuthenticationException("Invalid username or password");
        }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        return JWT.create()
                .withSubject(dto.email())
                .withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
                .withClaim("authorities", user.getAuthorities().toString())
                .sign(algorithm);

    }

    public String getSubject(String tokenJWT) {
        try {
            var algoritmo = Algorithm.HMAC256(secretKey);
            return JWT.require(algoritmo)
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Token JWT inválido ou expirado!");
        }
    }

}
