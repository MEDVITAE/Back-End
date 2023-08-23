package org.example.Controller;

import org.example.Domain.Usuario;
import org.example.Records.RecordUsuario;
import org.example.interfaces.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/usuario")
@RestController
public class UsuarioController {
    @Autowired
  private UsuarioRepository repository;


    @PostMapping("/Cadastrar")
    public void Cadastro(@RequestBody RecordUsuario dados){
        repository.save(new Usuario(dados));
    }
}
