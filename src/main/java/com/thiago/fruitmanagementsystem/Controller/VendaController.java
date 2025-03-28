package com.thiago.fruitmanagementsystem.Controller;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import com.thiago.fruitmanagementsystem.Model.FrutasVendasDTO;
import com.thiago.fruitmanagementsystem.Model.VendaRequestDTO;
import com.thiago.fruitmanagementsystem.Service.VendasService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Collections;

@RestController
@RequestMapping("/venda")
@Tag(name = "Venda", description = "Rotas Para Vendas")
public class VendaController {

    private final VendasService vendasService;


    public VendaController(VendasService vendasService) {
        this.vendasService = vendasService;
    }


    @PostMapping("/add")
    @Transactional
    @CrossOrigin
    @Operation(summary = "Adiciona uma nova venda", description = "Adiciona uma nova venda",
            tags = {"Venda"},
            operationId = "add",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Venda adicionada com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Erro na requisição"),
                    @ApiResponse(responseCode = "500", description = "Venda não adicionada")

            })
    @Parameters({
            @Parameter(name = "frutaID", description = "ID da fruta", required = true, schema = @Schema(type = "integer")),
            @Parameter(name = "discount", description = "Desconto", required = true, schema = @Schema(type = "float")),
            @Parameter(name = "qtdEscolhida", description = "Quantidade escolhida", required = true, schema = @Schema(type = "integer"))
    })
    public RedirectView executeSale(@RequestParam Integer frutaID, @RequestParam float discount, @RequestParam int qtdEscolhida) {
        try {

            FrutasVendasDTO dto = new FrutasVendasDTO( frutaID, discount, qtdEscolhida);
            VendaRequestDTO vendaRequestDTO = new VendaRequestDTO(Collections.singletonList(dto));
            Payment payment = vendasService.executeSalesWithDiscoutOrNot(vendaRequestDTO);
            for (Links link : payment.getLinks()) {
                if (link.getRel().equals("approval_url")) {
                    System.out.println("Redirecting to: " + link.getHref());
                    return new RedirectView(link.getHref());
                }
            }
        } catch (PayPalRESTException e) {
            throw new RuntimeException(e);
        }
        return new RedirectView("http://localhost:8080/payment/error");
    }
}
