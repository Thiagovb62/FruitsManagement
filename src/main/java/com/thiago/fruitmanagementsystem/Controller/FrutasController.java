package com.thiago.fruitmanagementsystem.Controller;

import com.thiago.fruitmanagementsystem.Model.FrutasFindBysDTO;
import com.thiago.fruitmanagementsystem.Model.FrutaRequestDTO;
import com.thiago.fruitmanagementsystem.Service.FrutaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/frutas")
@EnableMethodSecurity(securedEnabled = true)
@Tag(name = "Frutas", description = "Rotas Para Frutas")
public class FrutasController {

    private final FrutaService frutaService;

    public FrutasController(FrutaService frutaService) {
        this.frutaService = frutaService;
    }

    @GetMapping(value = "/findByName",consumes = "application/json", produces = "application/json")
    @Secured("VENDEDOR")
    @Operation(summary = "Busca frutas pelo nome", description = "Busca frutas pelo nome",
            tags = {"Frutas"},
            operationId = "findByName",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Erro na requisição"),
                    @ApiResponse(responseCode = "401", description = "Sem autorização"),
                    @ApiResponse (responseCode = "403", description = "Acesso negado"),
                    @ApiResponse(responseCode = "404", description = "Fruta não encontrada")

            })
    @Parameter(name = "dto", description = "DTO para busca de frutas pelo nome")
    public ResponseEntity findFruitByName(@RequestBody  FrutasFindBysDTO dto) {
        var frutas = frutaService.findFruitByName(dto);
        return ResponseEntity.ok(frutas);
    }

    @GetMapping(value = "/getAll", produces = "application/json")
    @Secured("VENDEDOR")
    @Operation(summary = "Busca todas as frutas", description = "Busca todas as frutas",
            tags = {"Frutas"},
            operationId = "getAll",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Erro na requisição"),
                    @ApiResponse(responseCode = "401", description = "Sem autorização"),
                    @ApiResponse (responseCode = "403", description = "Acesso negado"),
                    @ApiResponse(responseCode = "404", description = "Fruta não encontrada")

            })
    public ResponseEntity getAllFruits() {
        return ResponseEntity.ok(frutaService.getAllFruits());
    }

    @GetMapping(value = "/getByClassification",consumes = "application/json", produces = "application/json")
    @Secured("VENDEDOR")
    @Operation(summary = "Busca frutas pela classificação", description = "Busca frutas pela classificação",
            tags = {"Frutas"},
            operationId = "getByClassification",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Erro na requisição"),
                    @ApiResponse(responseCode = "401", description = "Sem autorização"),
                    @ApiResponse (responseCode = "403", description = "Acesso negado"),
                    @ApiResponse(responseCode = "404", description = "Fruta não encontrada")

            })
    public ResponseEntity getFruitsByClassification(@RequestBody  @Parameter(name = "dto", description = "DTO para busca de frutas pela classificação") FrutasFindBysDTO dto){
        return ResponseEntity.ok(frutaService.getFruitsByClassification(dto));
    }

    @GetMapping(value = "/getByFreshness",consumes = "application/json", produces = "application/json")
    @Secured("VENDEDOR")
    @Operation(summary = "Busca frutas pela frescura", description = "Busca frutas pela frescura",
            tags = {"Frutas"},
            operationId = "getByFreshness",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Erro na requisição"),
                    @ApiResponse(responseCode = "401", description = "Sem autorização"),
                    @ApiResponse (responseCode = "403", description = "Acesso negado"),
                    @ApiResponse(responseCode = "404", description = "Fruta não encontrada")

            })
    public ResponseEntity getFruitsByFreshness(@RequestBody FrutasFindBysDTO dto){
        return ResponseEntity.ok(frutaService.getFruitsByFreshness(dto));
    }

    @GetMapping(value = "/getByAvailableQuantity", produces = "application/json")
    @Secured("VENDEDOR")
    @Operation(summary = "Busca frutas pela quantidade disponível", description = "Busca frutas pela quantidade disponível",
            tags = {"Frutas"},
            operationId = "getByAvailableQuantity",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Erro na requisição"),
                    @ApiResponse(responseCode = "401", description = "Sem autorização"),
                    @ApiResponse (responseCode = "403", description = "Acesso negado"),
                    @ApiResponse(responseCode = "404", description = "Fruta não encontrada")

            })
    public ResponseEntity getFruitsByAvailableQuantity(){
        return ResponseEntity.ok(frutaService.getFruitsByAvailableQuantity());
    }

    @GetMapping(value = "/getBySaleValueAsc", produces = "application/json")
    @Secured("VENDEDOR")
    @Operation(summary = "Busca frutas pelo valor de venda crescente", description = "Busca frutas pelo valor de venda crescente",
            tags = {"Frutas"},
            operationId = "getBySaleValueAsc",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Erro na requisição"),
                    @ApiResponse(responseCode = "401", description = "Sem autorização"),
                    @ApiResponse (responseCode = "403", description = "Acesso negado"),
                    @ApiResponse(responseCode = "404", description = "Fruta não encontrada")

            })
    public ResponseEntity getFruitsBySaleValueAsc(){
        return ResponseEntity.ok(frutaService.getFruitsBySaleValueAsc());
    }

    @GetMapping("/getBySaleValueDesc")
    @Secured("VENDEDOR")
    @Operation(summary = "Busca frutas pelo valor de venda decrescente", description = "Busca frutas pelo valor de venda decrescente",
            tags = {"Frutas"},
            operationId = "getBySaleValueDesc",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Erro na requisição"),
                    @ApiResponse(responseCode = "401", description = "Sem autorização"),
                    @ApiResponse (responseCode = "403", description = "Acesso negado"),
                    @ApiResponse(responseCode = "404", description = "Fruta não encontrada")

            })
    public ResponseEntity getFruitsBySaleValueDesc(){
        return ResponseEntity.ok(frutaService.getFruitsBySaleValueDesc());
    }

    @GetMapping(value = "/getByParams",consumes = "application/json", produces = "application/json")
    @Secured("VENDEDOR")
    @Operation(summary = "Busca frutas por parâmetros", description = "Busca frutas por parâmetros",
            tags = {"Frutas"},
            operationId = "getByParams",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Erro na requisição"),
                    @ApiResponse(responseCode = "401", description = "Sem autorização"),
                    @ApiResponse (responseCode = "403", description = "Acesso negado"),
                    @ApiResponse(responseCode = "404", description = "Fruta não encontrada")

            })
    public ResponseEntity getFruitsByParams(@RequestBody FrutasFindBysDTO dto){
        return ResponseEntity.ok(frutaService.findAllByClassificacaoOrFrescaAndOrderByValorVendaIdAsc(dto));
    }

    @PostMapping(value = "/save",consumes = "application/json")
    @Secured("ADMIN")
    @Operation(summary = "Salva fruta", description = "Salva fruta",
            tags = {"Frutas"},
            operationId = "save",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Fruta salva com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Erro na requisição"),
                    @ApiResponse(responseCode = "401", description = "Sem autorização"),
                    @ApiResponse (responseCode = "403", description = "Acesso negado"),
                    @ApiResponse(responseCode = "404", description = "Fruta não encontrada")

            })

    public ResponseEntity saveFruit(@Valid @RequestBody  @Parameter(name = "dto", description = "DTO para criação de frutas")  FrutaRequestDTO dto) throws MessagingException {
        frutaService.saveFruit(dto);
        return ResponseEntity.ok().build();
    }
}
