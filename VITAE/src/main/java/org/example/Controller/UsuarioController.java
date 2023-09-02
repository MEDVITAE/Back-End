package org.example.Controller;

import jakarta.transaction.Transactional;
import jdk.swing.interop.SwingInterOpUtils;
import org.example.Domain.Emfermeira;
import org.example.Domain.Paciente;
import org.example.Domain.Recepcao;
import org.example.Domain.Usuario;
import org.example.Enums.Usuarios.UserRole;
import org.example.Records.Autorizacao.recordAuth;
import org.example.Records.Autorizacao.recordRegister;
import org.example.Records.Login;
import org.example.Records.Usuario.AtualizarUser;
import org.example.Records.Usuario.RecordUsuario;
import org.example.infra.Security.TokenService;
import org.example.interfaces.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/usuario")
@RestController
public class UsuarioController {
    @Autowired
    private AuthenticationManager autencador;
    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Validated recordAuth dados){
        var usuarioEmaileSenha = new UsernamePasswordAuthenticationToken(dados.email(),dados.senha());
        var auth = this.autencador.authenticate(usuarioEmaileSenha);
        var token = tokenService.gerarToken((Usuario) auth.getPrincipal());
        return ResponseEntity.ok(new Login(token));
    }
//    @PostMapping("/register")
//    public ResponseEntity register(@RequestBody @Validated RecordUsuario dados){
//        if(this.repository.findByEmail(dados.email()) != null) {
//            return ResponseEntity.badRequest().build();
//        }
//
//        String encripitando = new BCryptPasswordEncoder().encode(dados.senha());
//        Usuario usuario = new Usuario(dados.email(),encripitando,dados.role());
//        this.repository.save(usuario);
//        return ResponseEntity.ok().build();
//    }

    @PostMapping("/register")
    public ResponseEntity Cadastro(@RequestBody RecordUsuario dados){
             if(this.repository.findByEmail(dados.email()) != null) {
          return ResponseEntity.badRequest().build();
        }
        System.out.println(dados);
        String encripitando = new BCryptPasswordEncoder().encode(dados.senha());
        if(dados.role() == UserRole.PACIENTE){
            var usuario = new Paciente(dados.email(), encripitando,dados.role(),dados.nome(),dados.fkCaracteristicas());
        return ResponseEntity.status(201).body(repository.save(usuario));
        }
        if(dados.role() == UserRole.EMFERMEIRA){
            var emfermeira = new Emfermeira(dados.email(), encripitando,dados.role(),dados.nome());
            return ResponseEntity.status(201).body(repository.save(emfermeira));
        }
        if(dados.role() == UserRole.RECEPCAO){
            var recepcao = new Recepcao(dados.email(), encripitando,dados.role(),dados.nome());
            return ResponseEntity.status(201).body(repository.save(recepcao));
        }
        return  ResponseEntity.status(400).build();
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

       if(dados.role() == UserRole.PACIENTE){
           var usuario = repository.getReferenceById(id);
           var paciente = new Paciente(dados.nome());

           return ResponseEntity.ok(new AtualizarUser(usuario,paciente));
       }
       if(dados.role() == UserRole.EMFERMEIRA){
           var usuario = repository.getReferenceById(id);
           var emfermeira = new Emfermeira(dados.nome());
           return ResponseEntity.ok(new AtualizarUser(usuario,emfermeira));
       }
       if(dados.role() == UserRole.RECEPCAO){
           var usuario = repository.getReferenceById(id);
           var recepcao = new Recepcao(dados.nome());
           return ResponseEntity.ok(new AtualizarUser(usuario,recepcao));
       }
       return ResponseEntity.badRequest().build();
    }
    @DeleteMapping("{id}")
    @Transactional
    public  ResponseEntity DeletaUser(@PathVariable long id){
        repository.deleteById(id);
        return ResponseEntity.status(204).build();
    }

}
