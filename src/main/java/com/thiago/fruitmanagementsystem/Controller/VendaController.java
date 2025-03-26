package com.thiago.fruitmanagementsystem.Controller;

import com.thiago.fruitmanagementsystem.Model.VendaRequestDTO;
import com.thiago.fruitmanagementsystem.Service.VendasService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Venda", description = "Rotas Para Vendas")
public class VendaController {

    private final VendasService vendasService;

    public VendaController(VendasService vendasService) {
        this.vendasService = vendasService;
    }

    @PostMapping("/add")
    @Transactional
    @Secured("VENDEDOR")
    @Operation(summary = "Adiciona uma nova venda", description = "Adiciona uma nova venda",
            tags = {"Venda"},
            operationId = "add",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Venda adicionada com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Erro na requisição"),
                    @ApiResponse(responseCode = "401", description = "Sem autorização"),
                    @ApiResponse (responseCode = "403", description = "Acesso negado"),
                    @ApiResponse(responseCode = "404", description = "Venda não encontrada")

            })
    public void executeSale(@RequestBody @Parameter(name = "dto", description = "DTO para requisição de vendas") VendaRequestDTO dtos) {
        vendasService.executeSalesWithDiscoutOrNot(dtos);

    }

}
