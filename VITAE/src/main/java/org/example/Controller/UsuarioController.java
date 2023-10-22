package org.example.Controller;

import jakarta.transaction.Transactional;

import jakarta.validation.Valid;
import org.example.Domain.*;
import org.example.Enums.Usuarios.UserRole;
import org.example.Records.Autorizacao.RecordAuth;
import org.example.Records.Login;
import org.example.Records.Usuario.AtualizarUser;
import org.example.Records.Usuario.RecordUsuario;
import org.example.Service.UsuarioService;
import org.example.infra.Security.TokenService;
import org.example.interfaces.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/usuario")
@RestController
public class UsuarioController {
    @Autowired
    private AuthenticationManager autencador;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid RecordAuth dados){
        var usuarioEmaileSenha = new UsernamePasswordAuthenticationToken(dados.getEmail(),dados.getSenha());
        var auth = this.autencador.authenticate(usuarioEmaileSenha);
        var token = tokenService.gerarToken((Usuario) auth.getPrincipal());
        return ResponseEntity.ok(new Login(token));
    }

    @PostMapping("/register")
    public ResponseEntity<String> Cadastro(@RequestBody RecordUsuario dados) {

        Usuario user = usuarioService.cadastrar(dados);

        return ResponseEntity.ok().body("Usuario cadastrado! UserEmail: " + user.getEmail());
    }

    @GetMapping
    @Transactional
    @PreAuthorize("hasRole('RECEPCAO') || hasRole('PACIENTE') || hasRole('ENFERMEIRA') || hasRole('ADMIN') ")

    public ResponseEntity<List<Usuario>> listar(){
        if(usuarioService.listarUsuarios().isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(usuarioService.listarUsuarios());
    }
   @PutMapping("{id}")
   @Transactional
   @PreAuthorize("hasRole('RECEPCAO') || hasRole('PACIENTE') || hasRole('ENFERMEIRA') || hasRole('ADMIN') ")
    public  ResponseEntity atualizarUser(@RequestBody AtualizarUser dados, @PathVariable Long id){

       ResponseEntity<AtualizarUser> listUsuario = usuarioService.atualizarUsuario(dados, id);
       return ResponseEntity.ok().build();
    }
    @DeleteMapping("{id}")
    @Transactional
    @PreAuthorize("hasRole('RECEPCAO') || hasRole('PACIENTE') || hasRole('ENFERMEIRA') || hasRole('ADMIN') ")
    public  ResponseEntity DeletaUser(@PathVariable Long id){
        usuarioService.deletarUsuario(id);
        return ResponseEntity.status(204).build();
    }

}
