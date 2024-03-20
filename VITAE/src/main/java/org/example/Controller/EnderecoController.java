package org.example.Controller;

import jakarta.transaction.Transactional;
import org.example.DTO.CepsDTo;
import org.example.Domain.*;
import org.example.Enums.Usuarios.UserRole;
import org.example.Records.Caracteristicas.AtualizaCaracteresPesoAltura;
import org.example.Records.Endereco.AtualizaEndereco;
import org.example.Records.Endereco.AtualizaEnderecoNumeroCep;
import org.example.Records.Endereco.RecordEndereco;
import org.example.Records.Usuario.AtualizarUser;
import org.example.Records.Usuario.RecordUsuario;
import org.example.interfaces.EnderecoRepository;
import org.example.interfaces.RecuperaCeps;
import org.example.interfaces.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/Endereco")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class EnderecoController {
    @Autowired
    private EnderecoRepository repository;
    @Autowired
    private UsuarioRepository repositoryUsuario;
    @PreAuthorize("hasRole('RECEPCAO') || hasRole('PACIENTE') || hasRole('ENFERMEIRA') || hasRole('ADMIN') ")
    @GetMapping
    public ResponseEntity<List<Endereco>> listar() {
        return ResponseEntity.status(200).body(repository.findAll());
    }
    @PreAuthorize("hasRole('RECEPCAO') || hasRole('PACIENTE') || hasRole('ENFERMEIRA') || hasRole('ADMIN') ")
    @PostMapping
    public ResponseEntity cadastar(@RequestBody RecordEndereco dados) {
        return ResponseEntity.status(200).body(repository.save(new Endereco(dados)));
    }
    @PostMapping("/register/lista")

    public ResponseEntity CadastroLista(@RequestBody List<RecordEndereco> dados){
        List<Object> lista= new ArrayList<>();
        for(int i = 0;i < dados.size();i++) {
            Endereco endereco = new Endereco(dados.get(i));
            lista.add(endereco);
             repository.save(endereco);
        }
        return  ResponseEntity.status(200).body(lista);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('RECEPCAO') || hasRole('PACIENTE') || hasRole('ENFERMEIRA') || hasRole('ADMIN') ")
    @Transactional
    public ResponseEntity AtualizaEndereco(@PathVariable Long id,@RequestBody AtualizaEndereco dados) {

        var endereco = repository.getReferenceById(id);
        endereco.AtualizaEndereco(dados);
        return ResponseEntity.status(200).body((new AtualizaEndereco(endereco)));
    }



    @PutMapping("/detalhes/{id}")

    @Transactional
    public ResponseEntity<Endereco> AtualizaEnderecoDetalhes(@PathVariable Long id,@RequestBody AtualizaEnderecoNumeroCep dados) {
        Endereco usuario = repository.findByFkUsuario(id);
        var endereco = repository.getReferenceById(usuario.getId());
        endereco.AtualizaEnderecoNumeroCep(dados);
        new AtualizaEnderecoNumeroCep(endereco);
        repository.save(endereco);
        return ResponseEntity.status(200).body(endereco);
    }
    @Transactional
    @PreAuthorize("hasRole('RECEPCAO') || hasRole('PACIENTE') || hasRole('ENFERMEIRA') || hasRole('ADMIN') ")
    @DeleteMapping("{id}")
    public  ResponseEntity DeletaUser(@PathVariable long id){
        repository.deleteById(id);
        return ResponseEntity.status(204).build();
    }

    @Transactional
    @GetMapping("/mapa")
    public  ResponseEntity<List<CepsDTo>> Ceps(){
         List<RecuperaCeps> ceps = repository.findAllCep();

         List<CepsDTo> cepsDaVez = new ArrayList<>();
         for(RecuperaCeps c: ceps) {

             CepsDTo cep = new CepsDTo(c.getCep(),c.getFkHospital());
             System.out.println(c.getFkHospital());
             cepsDaVez.add(cep);
         }
        return ResponseEntity.status(200).body(cepsDaVez);
    }



}
