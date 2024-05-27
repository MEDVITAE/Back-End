package org.example.Controller;

import org.example.Service.CalculadoraService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculadoraController {

    private final CalculadoraService service;

    public CalculadoraController(CalculadoraService calculadoraService){
        this.service = calculadoraService;
    }

    @PostMapping("/divisao/{a}/{b}")
    public ResponseEntity<Double> divisao(@PathVariable double a, @PathVariable double b){
        return ResponseEntity.ok(service.dividir(a, b));
    }

}
