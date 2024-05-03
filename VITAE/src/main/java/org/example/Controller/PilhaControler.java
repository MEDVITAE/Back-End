package org.example.Controller;

import org.example.Domain.PilhaObg;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@RequestMapping(("/Api/pilha"))
@RestController
@CrossOrigin(origins = "http://52.204.157.192:3000/",allowedHeaders = "*")
public class PilhaControler {

    PilhaObg<String> pilhaNegativos = new PilhaObg<>();
    @PostMapping
    public void adicionarLista(@RequestBody String url){
        pilhaNegativos.empilhar(url);
    }
    @GetMapping
    public ResponseEntity<String> retirar(){
        if(pilhaNegativos.estaVazia()){
           return ResponseEntity.status(200).body("pilha esta vazia");
        }
        var ultima = pilhaNegativos.trazerUltimo();
        pilhaNegativos.desempilhar();
            System.out.println(pilhaNegativos.getPilha());
        return ResponseEntity.status(200).body(ultima);

    }


}
