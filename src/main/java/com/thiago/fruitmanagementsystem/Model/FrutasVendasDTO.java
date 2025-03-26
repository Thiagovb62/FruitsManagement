package com.thiago.fruitmanagementsystem.Model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Schema(name = "FrutasVendasDTO", description = "DTO para vendas de frutas")
public record FrutasVendasDTO(
        @NotNull(message = "O ID da fruta é obrigatório")
        Integer frutaID,
        @Min(value = 0)
        float discount,
        @NotNull(message = "A quantidade escolhida é obrigatória")
        @Min(value = 1, message = "A quantidade escolhida deve ser maior que 0")
        int qtdEscolhida
) {
}
