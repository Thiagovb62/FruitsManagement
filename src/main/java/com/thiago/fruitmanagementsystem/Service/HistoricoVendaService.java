package com.thiago.fruitmanagementsystem.Service;

import com.thiago.fruitmanagementsystem.Model.*;
import com.thiago.fruitmanagementsystem.Repository.FrutaRepository;
import com.thiago.fruitmanagementsystem.Repository.HistoricoVendaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class HistoricoVendaService {

    private final HistoricoVendaRepository historicoVendaRepository;
    private final FrutaRepository frutaRepository;

    public HistoricoVendaService(HistoricoVendaRepository historicoVendaRepository, com.thiago.fruitmanagementsystem.Repository.FrutaRepository frutaRepository) {
        this.historicoVendaRepository = historicoVendaRepository;
        this.frutaRepository = frutaRepository;
    }

    public List<HistoricoResponseDTO> findAllHistoricos() {
        List<HistoricoVendaFrutas> historicoVendaFrutas = historicoVendaRepository.findAllHstoricos();
        List<HistoricoResponseDTO> historicoResponseDTOS = new ArrayList<>();
        for (HistoricoVendaFrutas historicoVendaFruta : historicoVendaFrutas) {
            HistoricoResponseDTO historicoResponseDTO = new HistoricoResponseDTO(
                    historicoVendaFruta.getHistoricoVendas().getId(),
                    historicoVendaFruta.getHistoricoVendas().getDataVenda(),
                    historicoVendaFruta.getHistoricoVendas().getValorTotal(),
                    historicoVendaFruta.getHistoricoVendas().getQtdEscolhida(),
                    historicoVendaFruta.getFruta()
            );
            historicoResponseDTOS.add(historicoResponseDTO);
        }
        return historicoResponseDTOS;
    }

    public void saveHistoricoVendaWithDiscount(List<HistoricoVendaRequestDto> dtos) {
        for (HistoricoVendaRequestDto dto : dtos) {
            HistoricoVendas historico = createHistoricoVendas(dto);


            List<HistoricoVendaFrutas> frutasVendidas = processFruitsSales(dto, historico);
            historico.setFrutasVendidas(frutasVendidas);

            historicoVendaRepository.save(historico);
        }
    }

    private HistoricoVendas createHistoricoVendas(HistoricoVendaRequestDto dto) {
        HistoricoVendas historico = new HistoricoVendas();
        historico.setDataVenda(LocalDateTime.now());
        historico.setQtdEscolhida(dto.qtdEscolhida());
        return historico;
    }

    private List<HistoricoVendaFrutas> processFruitsSales(HistoricoVendaRequestDto dto, HistoricoVendas historico) {
        List<HistoricoVendaFrutas> frutasVendidas = new ArrayList<>();
        Double totalVenda = 0.0;

        Fruta fruta = frutaRepository.findById(Long.valueOf(dto.frutaID()))
                .orElseThrow(() -> new RuntimeException("Fruta não encontrada"));
        totalVenda += fruta.getValorVenda() * dto.qtdEscolhida();

        if (dto.discount() != 0) {
            totalVenda -= (totalVenda * dto.discount());
        }

        if (dto.qtdEscolhida() > fruta.getQtdDisponivel()) {
            throw new RuntimeException("Quantidade de fruta Escolhida Maior Do que Quantidade Disponivel");
        }

        fruta.setQtdDisponivel(fruta.getQtdDisponivel() - dto.qtdEscolhida());
        frutaRepository.save(fruta);

        HistoricoVendaFrutas historicoVendaFruta = new HistoricoVendaFrutas();
        historicoVendaFruta.setHistoricoVendas(historico);
        historicoVendaFruta.setFruta(fruta);
        frutasVendidas.add(historicoVendaFruta);

        historico.setValorTotal(totalVenda);
        return frutasVendidas;
    }
}
