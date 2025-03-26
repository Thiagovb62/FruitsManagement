package com.thiago.fruitmanagementsystem.Model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


@Schema(name = "FrutaRequestDTO", description = "DTO para criação de frutas")
public record FrutaRequestDTO(
        @NotBlank(message = "O nome da fruta é obrigatório")
        String nome,
        @NotNull(message = "A classificação da fruta é obrigatória")
        Integer classificacao,
        @NotNull(message = "O valor de venda da fruta é obrigatório")
        Double valorVenda,

        @NotNull(message = "A quantidade disponível da fruta é obrigatória")
        @Min(value = 0, message = "A quantidade disponível da fruta deve ser maior que 0")
        Integer qtdDisponivel,

        @NotNull(message = "A fruta é fresca?")
        Boolean fresca
) {
}
