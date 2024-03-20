package org.example.Controller;

import org.example.Domain.Enfermeira;
import org.example.Domain.Usuario;
import org.example.Enums.Usuarios.UserRole;
import org.example.Records.Autorizacao.recordAuth;
import org.example.Records.Autorizacao.recordRegister;
import org.example.Records.Login;
import org.example.infra.Security.TokenService;
import org.example.interfaces.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class AuthencationController {

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
        Usuario usuario =  repository.findByEmail(dados.email());
        String nome  = repository.findByNome(usuario.getIdUsuario());
        var tokenRetorno = new Login(token, usuario.getIdUsuario(),usuario.getRole(),nome,usuario.getFkHospital());

        return ResponseEntity.ok(tokenRetorno);
    }
}
