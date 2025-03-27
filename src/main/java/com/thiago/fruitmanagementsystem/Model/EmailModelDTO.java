package com.thiago.fruitmanagementsystem.Model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(name = "EmailModelDTO")
public record EmailModelDTO(
        @NotBlank(message = "O campo email não pode ser vazio")
        String emailFrom,
        @NotBlank(message = "O Destinatario não pode ser vazio")
        String emailTo,
        @NotBlank(message = "O campo assunto não pode ser vazio")
        String subject,
        @NotBlank(message = "O campo mensagem não pode ser vazio")
        String message
) {
}
