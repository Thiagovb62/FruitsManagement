package com.thiago.fruitmanagementsystem.Controller;

import com.thiago.fruitmanagementsystem.Model.Fruta;
import com.thiago.fruitmanagementsystem.Model.FrutaFindaBysDTO;
import com.thiago.fruitmanagementsystem.Model.FrutaRequestDTO;
import com.thiago.fruitmanagementsystem.Service.FrutaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriBuilder;

import java.net.URI;

@RestController
@RequestMapping("/fruta")
public class FrutaControler {

    private final FrutaService frutaService;

    public FrutaControler(FrutaService frutaService) {
        this.frutaService = frutaService;
    }

    @GetMapping("/findByName")
    public ResponseEntity findFruitByName(@RequestBody FrutaFindaBysDTO dto){
        Fruta fruta = frutaService.findFruitByName(dto);
        return ResponseEntity.ok(fruta);
    }

    @GetMapping("/getAll")
    public ResponseEntity getAllFruits(){
        return ResponseEntity.ok(frutaService.getAllFruits());
    }

    @GetMapping("/getByClassification")
    public ResponseEntity getFruitsByClassification(@RequestBody FrutaFindaBysDTO dto){
        return ResponseEntity.ok(frutaService.getFruitsByClassification(dto));
    }

    @GetMapping("/getByFreshness")
    public ResponseEntity getFruitsByFreshness(@RequestBody FrutaFindaBysDTO dto){
        return ResponseEntity.ok(frutaService.getFruitsByFreshness(dto));
    }

    @GetMapping("/getByAvailableQuantity")
    public ResponseEntity getFruitsByAvailableQuantity(){
        return ResponseEntity.ok(frutaService.getFruitsByAvailableQuantity());
    }

    @GetMapping("/getBySaleValueAsc")
    public ResponseEntity getFruitsBySaleValueAsc(){
        return ResponseEntity.ok(frutaService.getFruitsBySaleValueAsc());
    }

    @GetMapping("/getBySaleValueDesc")
    public ResponseEntity getFruitsBySaleValueDesc(){
        return ResponseEntity.ok(frutaService.getFruitsBySaleValueDesc());
    }

    @GetMapping("/getByParams")
    public ResponseEntity getFruitsByParams(@RequestBody FrutaFindaBysDTO dto){
        return ResponseEntity.ok(frutaService.findAllByClassificacaoOrFrescaAndOrderByValorVendaIdAsc(dto));
    }

    @PostMapping("/save")
    public ResponseEntity saveFruit(@RequestBody FrutaRequestDTO dto){
        frutaService.saveFruit(dto);
        return ResponseEntity.ok().build();
    }
}
