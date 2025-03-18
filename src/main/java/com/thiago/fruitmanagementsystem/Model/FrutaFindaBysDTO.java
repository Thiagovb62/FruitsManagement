package com.thiago.fruitmanagementsystem.Model;

import jakarta.validation.constraints.NotBlank;

public record FrutaFindaBysDTO(

        String nome,

        Integer classificacao,

        Boolean fresca
) {
}
