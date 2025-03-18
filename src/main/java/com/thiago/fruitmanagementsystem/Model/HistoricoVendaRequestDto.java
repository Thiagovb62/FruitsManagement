package com.thiago.fruitmanagementsystem.Model;
public record HistoricoVendaRequestDto(
        Integer frutaID,
        float discount,
        int qtdEscolhida
) {
}
