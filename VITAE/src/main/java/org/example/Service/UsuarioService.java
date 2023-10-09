package org.example.Service;

import org.example.Domain.Enfermeira;
import org.example.Domain.Paciente;
import org.example.Domain.Recepcao;
import org.example.Domain.Usuario;
import org.example.Enums.Usuarios.UserRole;
import org.example.Records.Usuario.AtualizarUser;
import org.example.Records.Usuario.RecordUsuario;
import org.example.interfaces.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    @Autowired
    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public UserDetails login(String email, String senha) {
        Usuario usuario = repository.findByEmail(email);
        if (usuario == null) {
            return null;
        }

        if (new BCryptPasswordEncoder().matches(senha, usuario.getPassword())) {
            var usuarioEmaileSenha = new UsernamePasswordAuthenticationToken(email, senha);
            var auth = this.authenticador.authenticate(usuarioEmaileSenha);
            return (UserDetails) auth.getPrincipal();
        } else {
            return null;
        }
    }

    public ResponseEntity<?> cadastrar(RecordUsuario dados) {
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

    public ResponseEntity<?> atualizarUsuario(AtualizarUser dados, long id) {
        Usuario usuario = repository.findById(id).orElse(null);

        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }

        if (dados.role() == UserRole.PACIENTE) {
            usuario.Atualiza(new aciente(dados.nome()));
        } else if (dados.role() == UserRole.ENFERMEIRA) {
            usuario.Atualiza(new Enfermeira(dados.nome()));
        } else if (dados.role() == UserRole.RECEPCAO) {
            usuario.Atualiza(new Recepcao(dados.nome()));
        } else {
            return ResponseEntity.badRequest().build();
        }

        return salvarUsuario(usuario);
    }

    public ResponseEntity<?> deletarUsuario(long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private ResponseEntity<?> salvarUsuario(Usuario usuario) {
        try {
            Usuario savedUser = repository.save(usuario);
            return ResponseEntity.status(201).body(savedUser);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}

