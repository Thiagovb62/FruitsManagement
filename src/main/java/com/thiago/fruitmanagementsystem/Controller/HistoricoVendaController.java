package com.thiago.fruitmanagementsystem.Controller;

import com.thiago.fruitmanagementsystem.Model.HistoricoResponseDTO;
import com.thiago.fruitmanagementsystem.Model.HistoricoVendaRequestDto;
import com.thiago.fruitmanagementsystem.Service.HistoricoVendaService;
import jakarta.transaction.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/historicoVenda")
public class HistoricoVendaController {

    private final HistoricoVendaService historicoVendaService;

    public HistoricoVendaController(HistoricoVendaService historicoVendaService) {
        this.historicoVendaService = historicoVendaService;
    }

    @GetMapping("/all")
    public List<HistoricoResponseDTO> findAllHistoricos() {
        return historicoVendaService.findAllHistoricos();
    }

    @PostMapping("/add")
    @Transactional
    public void addHistoricoVenda(@RequestBody List<HistoricoVendaRequestDto> dtos) {
        historicoVendaService.saveHistoricoVendaWithDiscount(dtos);
    }
}
