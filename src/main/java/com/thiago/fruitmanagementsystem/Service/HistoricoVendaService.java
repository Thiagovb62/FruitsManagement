package com.thiago.fruitmanagementsystem.Service;

import com.thiago.fruitmanagementsystem.Model.*;
import com.thiago.fruitmanagementsystem.Repository.FrutaRepository;
import com.thiago.fruitmanagementsystem.Repository.HistoricoVendaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class HistoricoVendaService {

    private final HistoricoVendaRepository historicoVendaRepository;
    private final FrutaRepository frutaRepository;

    public HistoricoVendaService(HistoricoVendaRepository historicoVendaRepository, com.thiago.fruitmanagementsystem.Repository.FrutaRepository frutaRepository) {
        this.historicoVendaRepository = historicoVendaRepository;
        this.frutaRepository = frutaRepository;
    }

    public List<HistoricoResponseDTO> findAllHistoricos() {
        List<HistoricoVendaFrutas> historicoVendas = historicoVendaRepository.findAllHstoricos();
        Map<UUID, HistoricoResponseDTO> historicoMap = new HashMap<>();

        for (HistoricoVendaFrutas historicoVenda : historicoVendas) {
            UUID historicoId = historicoVenda.getHistoricoVendas().getId();
            if (!historicoMap.containsKey(historicoId)) {
                historicoMap.put(historicoId, new HistoricoResponseDTO(
                        historicoId,
                        historicoVenda.getHistoricoVendas().getValorTotal(),
                        new ArrayList<>()
                ));
            }
            HistoricoResponseDTO historicoResponseDTO = historicoMap.get(historicoId);
            historicoResponseDTO.frutasVendidas().add(new FrutaVendaResponseDTO(
                    historicoVenda.getHistoricoVendas().getQtdEscolhida(),
                    historicoVenda.getHistoricoVendas().getDataVenda(),
                    historicoVenda.getFruta()
            ));
        }
        return new ArrayList<>(historicoMap.values());
    }




    protected HistoricoVendas createHistoricoVendas(VendaRequestDTO dto) {
        HistoricoVendas historico = new HistoricoVendas();
        historico.setDataVenda(LocalDateTime.now());
        for (FrutasVendasDTO dto2 : dto.frutasVendasDTO()) {
            historico.setQtdEscolhida(dto2.qtdEscolhida());
        }
        return historico;
    }

    protected List<HistoricoVendaFrutas> processFruitsSales(VendaRequestDTO dto, HistoricoVendas historico) {
        List<HistoricoVendaFrutas> historicoVendaFruta = new ArrayList<>();


        Double totalVenda = 0.0;

        for (FrutasVendasDTO dto2 : dto.frutasVendasDTO()) {
            Fruta fruta = frutaRepository.findById(Long.valueOf(dto2.frutaID())).orElseThrow(() -> new RuntimeException("Fruta não encontrada"));
            Double valorVenda = fruta.getValorVenda() * dto2.qtdEscolhida();

            if (dto2.discount() != 0) {
                valorVenda -= (valorVenda * dto2.discount());
            }

            if (dto2.qtdEscolhida() > fruta.getQtdDisponivel()) {
                throw new RuntimeException("Quantidade de fruta Escolhida Maior Do que Quantidade Disponivel");
            }

            fruta.setQtdDisponivel(fruta.getQtdDisponivel() - dto2.qtdEscolhida());
            frutaRepository.save(fruta);


        HistoricoVendaFrutas historicoVendaFrutas = new HistoricoVendaFrutas();
        historicoVendaFrutas.setHistoricoVendas(historico);
        historicoVendaFrutas.setFruta(fruta);
        historicoVendaFruta.add(historicoVendaFrutas);


            totalVenda += valorVenda;
        }

        historico.setValorTotal(totalVenda);
        return historicoVendaFruta;
    }
}
