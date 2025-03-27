package com.thiago.fruitmanagementsystem.Controller;

import com.itextpdf.text.DocumentException;
import com.thiago.fruitmanagementsystem.Model.HistoricoResponseDTO;
import com.thiago.fruitmanagementsystem.Service.HistoricoVendaService;
import com.thiago.fruitmanagementsystem.Utils.PdfUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/historicoVenda")
@EnableMethodSecurity(securedEnabled = true)
@Tag(name = "HistoricoVenda", description = "Rotas Para Histórico de Vendas")
public class HistoricoVendaController {

    private final HistoricoVendaService historicoVendaService;

    public HistoricoVendaController(HistoricoVendaService historicoVendaService) {
        this.historicoVendaService = historicoVendaService;
    }

    @GetMapping(value = "/all",produces = "application/json")
    @Secured("VENDEDOR")
    @Operation(summary = "Busca todos os históricos de vendas", description = "Busca todos os históricos de vendas",
            tags = {"HistoricoVenda"},
            operationId = "all",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Erro na requisição"),
                    @ApiResponse(responseCode = "401", description = "Sem autorização"),
                    @ApiResponse (responseCode = "403", description = "Acesso negado"),
                    @ApiResponse(responseCode = "404", description = "Histórico de vendas não encontrado")

            })
    public List<HistoricoResponseDTO> findAllHistoricos() {
        return historicoVendaService.findAllHistoricos();
    }

    @GetMapping(value = "/all/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    @Secured("VENDEDOR")
    @Operation(summary = "Gera um PDF com todos os históricos de vendas", description = "Gera um PDF com todos os históricos de vendas",
            tags = {"HistoricoVenda"},
            operationId = "allPdf",
            responses = {
                    @ApiResponse(responseCode = "200", description = "PDF gerado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Erro na requisição"),
                    @ApiResponse(responseCode = "401", description = "Sem autorização"),
                    @ApiResponse(responseCode = "404", description = "Histórico de vendas não encontrado")
            })
    public ResponseEntity<String> findAllHistoricosPdf() throws DocumentException, IOException, MessagingException {
        historicoVendaService.generatePdf();
        return ResponseEntity.ok()
                .headers(new HttpHeaders())
                .contentType(MediaType.APPLICATION_PDF)
                .body("PDF gerado com sucesso");
    }
}
