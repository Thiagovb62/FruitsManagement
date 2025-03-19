package com.thiago.fruitmanagementsystem.Controller;

import com.thiago.fruitmanagementsystem.Model.Fruta;
import com.thiago.fruitmanagementsystem.Model.FrutasFindBysDTO;
import com.thiago.fruitmanagementsystem.Model.FrutaRequestDTO;
import com.thiago.fruitmanagementsystem.Service.FrutaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/frutas")
@EnableMethodSecurity(securedEnabled = true)
public class FrutasControler {

    private final FrutaService frutaService;

    public FrutasControler(FrutaService frutaService) {
        this.frutaService = frutaService;
    }

    @GetMapping("/findByName")
    @Secured("VENDEDOR")
    public ResponseEntity findFruitByName(@RequestBody FrutasFindBysDTO dto){
        Fruta fruta = frutaService.findFruitByName(dto);
        return ResponseEntity.ok(fruta);
    }

    @GetMapping("/getAll")
    @Secured("VENDEDOR")
    public ResponseEntity getAllFruits(){
        return ResponseEntity.ok(frutaService.getAllFruits());
    }

    @GetMapping("/getByClassification")
    @Secured("VENDEDOR")
    public ResponseEntity getFruitsByClassification(@RequestBody FrutasFindBysDTO dto){
        return ResponseEntity.ok(frutaService.getFruitsByClassification(dto));
    }

    @GetMapping("/getByFreshness")
    @Secured("VENDEDOR")
    public ResponseEntity getFruitsByFreshness(@RequestBody FrutasFindBysDTO dto){
        return ResponseEntity.ok(frutaService.getFruitsByFreshness(dto));
    }

    @GetMapping("/getByAvailableQuantity")
    @Secured("VENDEDOR")
    public ResponseEntity getFruitsByAvailableQuantity(){
        return ResponseEntity.ok(frutaService.getFruitsByAvailableQuantity());
    }

    @GetMapping("/getBySaleValueAsc")
    @Secured("VENDEDOR")
    public ResponseEntity getFruitsBySaleValueAsc(){
        return ResponseEntity.ok(frutaService.getFruitsBySaleValueAsc());
    }

    @GetMapping("/getBySaleValueDesc")
    @Secured("VENDEDOR")
    public ResponseEntity getFruitsBySaleValueDesc(){
        return ResponseEntity.ok(frutaService.getFruitsBySaleValueDesc());
    }

    @GetMapping("/getByParams")
    @Secured("VENDEDOR")
    public ResponseEntity getFruitsByParams(@RequestBody FrutasFindBysDTO dto){
        return ResponseEntity.ok(frutaService.findAllByClassificacaoOrFrescaAndOrderByValorVendaIdAsc(dto));
    }

    @PostMapping("/save")
    @Secured("ADMIN")
    public ResponseEntity saveFruit(@Valid @RequestBody FrutaRequestDTO dto){
        frutaService.saveFruit(dto);
        return ResponseEntity.ok().build();
    }
}
