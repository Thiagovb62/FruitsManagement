package com.thiago.fruitmanagementsystem.Controller;

import com.thiago.fruitmanagementsystem.Model.VendaRequestDTO;
import com.thiago.fruitmanagementsystem.Service.VendasService;
import jakarta.transaction.Transactional;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/venda")
@EnableMethodSecurity(securedEnabled = true)
public class VendaController {

    private final VendasService vendasService;

    public VendaController(VendasService vendasService) {
        this.vendasService = vendasService;
    }

    @PostMapping("/add")
    @Transactional
    @Secured("VENDEDOR")
    public void executeSale(@RequestBody List<VendaRequestDTO> dtos) {
        vendasService.executeSalesWithDiscoutOrNot(dtos);

    }

}
