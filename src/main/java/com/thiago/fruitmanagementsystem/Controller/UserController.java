package com.thiago.fruitmanagementsystem.Controller;
import com.thiago.fruitmanagementsystem.Model.UserDTO;
import com.thiago.fruitmanagementsystem.Repository.UserRepository;
import com.thiago.fruitmanagementsystem.Service.AuthenticationService;
import jakarta.validation.Valid;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final AuthenticationService authenticationService;

    public UserController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public void cadastrarUsuario(@Valid @RequestBody UserDTO dto) throws AuthenticationException {
        authenticationService.CreateUser(dto);
    }

    @PostMapping("/login")
    public String autenticarUsuario(@Valid @RequestBody UserDTO dto) throws AuthenticationException {
        return authenticationService.authenticate(dto);
    }
}