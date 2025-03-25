package com.thiago.fruitmanagementsystem.Model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record HistoricoResponseDTO(
        UUID  id,
        Double valorTotal,
        List<FrutaVendaResponseDTO> frutasVendidas
) {
}
