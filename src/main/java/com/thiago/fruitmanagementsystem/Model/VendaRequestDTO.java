package com.thiago.fruitmanagementsystem.Model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record VendaRequestDTO(
        @NotNull(message = "O ID da fruta é obrigatório")
        Integer frutaID,
        @Min(value = 0)
        float discount,
        @NotNull(message = "A quantidade escolhida é obrigatória")
        @Min(value = 1, message = "A quantidade escolhida deve ser maior que 0")
        int qtdEscolhida
) {
        void validDiscount() {
                if (discount != 0 && discount != 0.05 && discount != 0.1 && discount != 0.15 && discount != 0.2 && discount != 0.25) {
                        throw new IllegalArgumentException("O desconto deve ser um dos seguintes valores: 0, 0.05, 0.1, 0.15, 0.2, 0.25");
                }
        }
}
