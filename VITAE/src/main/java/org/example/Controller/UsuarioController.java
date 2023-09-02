package org.example.Controller;

import jakarta.transaction.Transactional;
import org.example.Domain.Usuario;
import org.example.Records.Usuario.AtualizarUser;
import org.example.Records.Usuario.RecordUsuario;
import org.example.interfaces.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/usuario")
@RestController
public class UsuarioController {
    @Autowired
  private UsuarioRepository repository;


    @PostMapping
    public ResponseEntity Cadastro(@RequestBody RecordUsuario dados){

        return ResponseEntity.status(201).body(repository.save(new Usuario(dados))
        );
    }
    @GetMapping
    @Transactional
    public ResponseEntity<List<Usuario>> listar(){
        if(repository.findAll().isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(repository.findAll());

    }
   @PutMapping("{id}")
   @Transactional
    public  ResponseEntity atualizarUser(@RequestBody AtualizarUser dados, @PathVariable long id){
        var usuario = repository.getReferenceById(id);
       usuario.Atualiza(dados);
       return ResponseEntity.ok(new AtualizarUser(usuario));
    }
    @DeleteMapping("{id}")
    @Transactional
    public  ResponseEntity DeletaUser(@PathVariable long id){
        repository.deleteById(id);
        return ResponseEntity.status(204).build();
    }

}
