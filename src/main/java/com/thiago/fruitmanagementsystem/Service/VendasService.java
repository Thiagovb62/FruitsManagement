package com.thiago.fruitmanagementsystem.Service;

import com.thiago.fruitmanagementsystem.Model.HistoricoVendaFrutas;
import com.thiago.fruitmanagementsystem.Model.VendaRequestDTO;
import com.thiago.fruitmanagementsystem.Model.HistoricoVendas;
import com.thiago.fruitmanagementsystem.Repository.HistoricoVendaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendasService  {

    private final HistoricoVendaRepository historicoVendaRepository;
    private final HistoricoVendaService historicoVendaService;


    public VendasService(HistoricoVendaRepository historicoVendaRepository, HistoricoVendaService historicoVendaService) {
        this.historicoVendaRepository = historicoVendaRepository;
        this.historicoVendaService = historicoVendaService;
    }

    public void executeSalesWithDiscoutOrNot(VendaRequestDTO dto) {

            HistoricoVendas historico = historicoVendaService.createHistoricoVendas(dto);

            List<HistoricoVendaFrutas> frutasVendidas = historicoVendaService.processFruitsSales(dto, historico);

            historico.setFrutasVendidas(frutasVendidas);

            historicoVendaRepository.save(historico);
        }

}
