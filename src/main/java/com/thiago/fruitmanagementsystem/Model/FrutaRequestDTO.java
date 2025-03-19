package com.thiago.fruitmanagementsystem.Model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

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
