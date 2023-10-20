package org.example.Service;

import org.example.Domain.Enfermeira;
import org.example.Domain.Paciente;
import org.example.Domain.Recepcao;
import org.example.Domain.Usuario;
import org.example.Enums.Usuarios.UserRole;
import org.example.Records.Autorizacao.RecordAuth;
import org.example.Records.Usuario.AtualizarUser;
import org.example.Records.Usuario.RecordUsuario;
import org.example.infra.Security.TokenService;
import org.example.interfaces.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collections;
import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private AuthenticationManager autencador;
    @Autowired
    private  UsuarioRepository repository;
    @Autowired
    private TokenService tokenService;




    public ResponseEntity<?> cadastrar(@Validated RecordUsuario dados) {
        if (repository.findByEmail(dados.email()) != null) {
            return ResponseEntity.badRequest().build();
        }
        String encriptando = new BCryptPasswordEncoder().encode(dados.senha());
        Usuario usuario;

        if (dados.role() == UserRole.PACIENTE) {
            usuario = new Paciente(dados.email(), encriptando, dados.role(), dados.nome());
        } else if (dados.role() == UserRole.ENFERMEIRA) {
            usuario = new Enfermeira(dados.email(), encriptando, dados.role(), dados.nome());
        } else if (dados.role() == UserRole.RECEPCAO) {
            usuario = new Recepcao(dados.email(), encriptando, dados.role(), dados.nome());
        } else {
            return ResponseEntity.status(400).build();
        }
        return salvarUsuario(usuario);
    }

    public List<Usuario> listarUsuarios() {
        List<Usuario> usuarios = repository.findAll();
        if (usuarios.isEmpty()) {
            return Collections.emptyList();
        }
        return usuarios;
    }

    public ResponseEntity<?> atualizarUsuario(@Validated AtualizarUser dados, long id) {
        if(dados.role() == UserRole.PACIENTE){
            var usuario = repository.getReferenceById(id);
            var paciente = new Paciente(dados.nome());
            return ResponseEntity.ok(new AtualizarUser(usuario,paciente));
        }
        if(dados.role() == UserRole.ENFERMEIRA){
            var usuario = repository.getReferenceById(id);
            var ENFERMEIRA = new Enfermeira(dados.nome());
            return ResponseEntity.ok(new AtualizarUser(usuario,ENFERMEIRA));
        }
        if(dados.role() == UserRole.RECEPCAO){
            var usuario = repository.getReferenceById(id);
            var recepcao = new Recepcao(dados.nome());
            return ResponseEntity.ok(new AtualizarUser(usuario,recepcao));
        }
        return ResponseEntity.badRequest().build();

    }

    public ResponseEntity<?> deletarUsuario(long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private ResponseEntity<?> salvarUsuario(@Validated Usuario usuario) {
        try {
            Usuario savedUser = repository.save(usuario);
            return ResponseEntity.status(201).body(savedUser);
        } catch (Exception e) {
            return ResponseEntity.status(400).build();
        }
    }
}

