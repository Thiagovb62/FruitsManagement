package com.thiago.fruitmanagementsystem.Model;
public record VendaRequestDTO(
        Integer frutaID,
        float discount,
        int qtdEscolhida
) {
}
