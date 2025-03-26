package com.thiago.fruitmanagementsystem.Model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(name = "UserDTO", description = "DTO para criação de usuários")
public record UserDTO(
        @NotBlank(message = "O nome do usuário é obrigatório")
        String email,
        @NotBlank(message = "A senha do usuário é obrigatória")
        String password
) {

}
