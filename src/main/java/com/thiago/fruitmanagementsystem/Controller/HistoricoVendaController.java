package com.thiago.fruitmanagementsystem.Controller;

import com.thiago.fruitmanagementsystem.Model.HistoricoResponseDTO;
import com.thiago.fruitmanagementsystem.Service.HistoricoVendaService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/historicoVenda")
@EnableMethodSecurity(securedEnabled = true)
public class HistoricoVendaController {

    private final HistoricoVendaService historicoVendaService;

    public HistoricoVendaController(HistoricoVendaService historicoVendaService) {
        this.historicoVendaService = historicoVendaService;
    }

    @GetMapping("/all")
    @Secured("VENDEDOR")
    public List<HistoricoResponseDTO> findAllHistoricos() {
        return historicoVendaService.findAllHistoricos();
    }
}
