package org.example.Service;

import jakarta.transaction.Transactional;
import org.example.Domain.Enfermeira;
import org.example.Domain.Paciente;
import org.example.Domain.Recepcao;
import org.example.Domain.Usuario;
import org.example.Enums.Usuarios.UserRole;
import org.example.Records.Usuario.AtualizarUser;
import org.example.Records.Usuario.RecordUsuario;
import org.example.infra.Security.TokenService;
import org.example.interfaces.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

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

    @Transactional
    public Usuario cadastrar(@Validated RecordUsuario dados) {
        //valida campo email
        if (dados.email() == null){
            return null;
        }
        // valida campo senha
        if (dados.senha().length() >= 3){
            return null;
        }
        // valida se email ja existe
        if (userAlreadyRegistered(dados)){
            return null;
        }

        // SUCESSO TODOS OS DADOS FUNCIONARAM :D
        String encryptPassword = new BCryptPasswordEncoder().encode(dados.senha());

        Usuario newUserAndRole = roleUsuario(dados, encryptPassword);

        if (newUserAndRole == null) {
            return null;
        }

        return repository.save(newUserAndRole);
    }

    private static Usuario roleUsuario(RecordUsuario dados, String encryptPassword) {

        return switch (dados.role()) {
            case PACIENTE -> new Paciente(dados.email(), encryptPassword, dados.role(), dados.nome());
            case ENFERMEIRA -> new Enfermeira(dados.email(), encryptPassword, dados.role(), dados.nome());
            case RECEPCAO -> new Recepcao(dados.email(), encryptPassword, dados.role(), dados.nome());
            default -> null;
        };
    }

    private Boolean userAlreadyRegistered(RecordUsuario dados) {
        UserDetails userAlreadyExist = repository.findByEmail(dados.email());

        return !userAlreadyExist.getUsername().isEmpty();
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
}

