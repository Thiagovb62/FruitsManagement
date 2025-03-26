package com.thiago.fruitmanagementsystem.Model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "FrutasFindBysDTO", description = "DTO para busca de frutas")
public record FrutasFindBysDTO(

        String nome,

        Integer classificacao,

        Boolean fresca
) {
}
