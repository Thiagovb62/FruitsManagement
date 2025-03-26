package com.thiago.fruitmanagementsystem.Model;


import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(name = "VendaRequestDTO", description = "DTO para requisição de vendas")
public record VendaRequestDTO(

     List<FrutasVendasDTO> frutasVendasDTO
     ) {
}
