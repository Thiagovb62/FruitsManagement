package com.thiago.fruitmanagementsystem.Model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

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
