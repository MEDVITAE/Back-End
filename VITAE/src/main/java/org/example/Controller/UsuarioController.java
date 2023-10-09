package org.example.Controller;

import jakarta.transaction.Transactional;

import org.example.Domain.Enfermeira;
import org.example.Domain.Paciente;
import org.example.Domain.Recepcao;
import org.example.Domain.Usuario;
import org.example.Enums.Usuarios.UserRole;
import org.example.Records.Autorizacao.recordAuth;
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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/usuario")
@RestController
public class UsuarioController {
    private final AuthenticationManager autenticador;
    private final TokenService tokenService;
    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(
            AuthenticationManager autenticador,
            TokenService tokenService,
            UsuarioService usuarioService) {
        this.autenticador = autenticador;
        this.tokenService = tokenService;
        this.usuarioService = usuarioService;
    }

    @PostMapping("/login")
    public ResponseEntity<List<Usuario>> login(@RequestBody @Validated recordAuth dados) {
        UserDetails userDetails = usuarioService.login(dados.email(), dados.senha());
        if (userDetails != null) {
            var token = tokenService.gerarToken(userDetails);
            return ResponseEntity.ok(new Login(token));
        } else {
            return ResponseEntity.status(401).build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> cadastrar(@RequestBody RecordUsuario dados) {
        ResponseEntity<?> response = usuarioService.cadastrar(dados);
        if (UsuarioService.) {
            return ResponseEntity.badRequest().build();
        }
        return response;
    }

    @GetMapping
    @Transactional
<<<<<<< Updated upstream
    @PreAuthorize("hasRole('RECEPCAO') || hasRole('PACIENTE') || hasRole('ENFERMEIRA') ")

    public ResponseEntity<List<Usuario>> listar(){
        if(repository.findAll().isEmpty()) {
            return ResponseEntity.status(204).build();
=======
    @PreAuthorize("hasRole('RECEPCAO') || hasRole('PACIENTE') || hasRole('ENFERMEIRA') || hasRole('ADMIN')")
    public ResponseEntity<List<Usuario>> listar() {
        List<Usuario> usuarios = usuarioService.listarUsuarios();
        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
>>>>>>> Stashed changes
        }
        return ResponseEntity.ok(usuarios);
    }
<<<<<<< Updated upstream
   @PutMapping("{id}")
   @Transactional
   @PreAuthorize("hasRole('RECEPCAO') || hasRole('PACIENTE') || hasRole('ENFERMEIRA') ")
    public  ResponseEntity atualizarUser(@RequestBody AtualizarUser dados, @PathVariable long id){
=======
>>>>>>> Stashed changes

    @PutMapping("{id}")
    @Transactional
    @PreAuthorize("hasRole('RECEPCAO') || hasRole('PACIENTE') || hasRole('ENFERMEIRA') || hasRole('ADMIN')")
    public ResponseEntity<?> atualizarUser(@RequestBody AtualizarUser dados, @PathVariable long id) {
        ResponseEntity<?> response = usuarioService.atualizarUsuario(dados, id);
        if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
            return ResponseEntity.notFound().build();
        }
        return response;
    }

    @DeleteMapping("{id}")
    @Transactional
<<<<<<< Updated upstream
    @PreAuthorize("hasRole('RECEPCAO') || hasRole('PACIENTE') || hasRole('ENFERMEIRA') ")
    public  ResponseEntity DeletaUser(@PathVariable long id){
        repository.deleteById(id);
        return ResponseEntity.status(204).build();
=======
    @PreAuthorize("hasRole('RECEPCAO') || hasRole('PACIENTE') || hasRole('ENFERMEIRA') || hasRole('ADMIN')")
    public ResponseEntity<?> deletarUser(@PathVariable long id) {
        ResponseEntity<?> response = usuarioService.deletarUsuario(id);
        if (response.getStatusCode() == HttpStatus.NO_CONTENT) {
            return ResponseEntity.noContent().build();
        }
        return response;
>>>>>>> Stashed changes
    }
}

}
