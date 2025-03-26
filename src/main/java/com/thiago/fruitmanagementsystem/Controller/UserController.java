package com.thiago.fruitmanagementsystem.Controller;
import com.thiago.fruitmanagementsystem.Model.UserDTO;
import com.thiago.fruitmanagementsystem.Repository.UserRepository;
import com.thiago.fruitmanagementsystem.Service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Tag(name = "User", description = "Rotas Para Usuários")
public class UserController {

    private final AuthenticationService authenticationService;

    public UserController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping(value = "/register", consumes = "application/json")
    @Operation(summary = "Cadastra um novo usuário", description = "Cadastra um novo usuário",
            tags = {"User"},
            operationId = "cadastrarUsuario",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Usuário cadastrado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Erro na requisição"),
            })
    public void cadastrarUsuario(@Valid @RequestBody @Parameter(name = "dto", description = "DTO para cadastro de usuários") UserDTO dto) throws AuthenticationException {
        authenticationService.CreateUser(dto);
    }

    @PostMapping(value = "/login", consumes = "application/json")
    @Operation(summary = "Autentica um usuário", description = "Autentica um usuário",
            tags = {"User"},
            operationId = "autenticarUsuario",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Usuário autenticado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Erro na requisição"),

                    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
            })
    public String autenticarUsuario(@Valid @RequestBody @Parameter(name = "dto", description = "DTO para autenticação de usuários") UserDTO dto) throws AuthenticationException {
        return authenticationService.authenticate(dto);
    }
}