package com.thiago.fruitmanagementsystem.Model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserDTO(
        @NotBlank(message = "O nome do usuário é obrigatório")
        String email,
        @NotBlank(message = "A senha do usuário é obrigatória")
        String password
) {

}
