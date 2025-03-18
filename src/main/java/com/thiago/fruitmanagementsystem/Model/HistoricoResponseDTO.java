package com.thiago.fruitmanagementsystem.Model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record HistoricoResponseDTO(
        UUID  id,
        LocalDateTime dataVenda,
        Double valorTotal,
        int qtdEscolhida,
        List<Fruta> frutasVendidas
) {
    public HistoricoResponseDTO(UUID id, LocalDateTime dataVenda, Double valorTotal, int qtdEscolhida, Fruta frutasVendidas) {
        this(id, dataVenda, valorTotal, qtdEscolhida, List.of(frutasVendidas));
    }

}
