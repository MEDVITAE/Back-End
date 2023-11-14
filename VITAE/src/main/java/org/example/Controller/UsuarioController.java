package org.example.Controller;

import jakarta.transaction.Transactional;

import org.example.DTO.DadosUserDTO;
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
import org.example.interfaces.RecuperaDetalhesUsuario;
import org.example.interfaces.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/usuario")
@RestController
public class UsuarioController {
    @Autowired
    private AuthenticationManager autenticador;
    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Validated recordAuth dados){
        var usuarioEmaileSenha = new UsernamePasswordAuthenticationToken(dados.email(),dados.senha());
        var auth = this.autenticador.authenticate(usuarioEmaileSenha);
        var token = tokenService.gerarToken((Usuario) auth.getPrincipal());

        return ResponseEntity.ok(new Login(token));
    }
    @PostMapping("/register/lista")
    public ResponseEntity CadastroLista(@RequestBody List<RecordUsuario> dados){
        List<Object> lista= new ArrayList<>();
        System.out.println("ssssssss");
        for(int i = 0;i < dados.size();i++) {

            String encripitando = new BCryptPasswordEncoder().encode((dados.get(i).senha()));
            String email = dados.get(i).email();
            UserRole role = dados.get(i).role();
            String nome = (dados.get(i).nome());
            String cpf = dados.get(i).cpf();

            try {

                if (role == UserRole.PACIENTE) {
                    var usuario = new Paciente(email, encripitando, role, nome, cpf);
                    lista.add(repository.save(usuario));

                }
                if (role == UserRole.ENFERMEIRA) {
                    var ENFERMEIRA = new Enfermeira(email, encripitando, role, nome, cpf);
                    lista.add(repository.save(ENFERMEIRA));



                }
                if (role == UserRole.RECEPCAO) {
                    var recepcao = new Recepcao(email, encripitando, role, nome, cpf);
                    lista.add(repository.save(recepcao));

                }

            }catch (Exception e){
                return  ResponseEntity.status(400).build();
            }
        }

        return  ResponseEntity.status(200).body(lista);
    }

    @PostMapping("/register")
    public ResponseEntity Cadastro(@RequestBody RecordUsuario dados){
             if(this.repository.findByEmail(dados.email()) != null) {
                 this.repository.findByEmail(dados.email());
                 
                return ResponseEntity.badRequest().build();
        }
        String encripitando = new BCryptPasswordEncoder().encode(dados.senha());
        if(dados.role() == UserRole.PACIENTE){
            var usuario = new Paciente(dados.email(), encripitando,dados.role(),dados.nome(),dados.cpf());
        return ResponseEntity.status(201).body(repository.save(usuario));
        }
        if(dados.role() == UserRole.ENFERMEIRA){
            var ENFERMEIRA = new Enfermeira(dados.email(), encripitando,dados.role(),dados.nome(),dados.cpf());
            return ResponseEntity.status(201).body(repository.save(ENFERMEIRA));
        }
        if(dados.role() == UserRole.RECEPCAO){
            var recepcao = new Recepcao(dados.email(), encripitando,dados.role(),dados.nome(),dados.cpf());
            return ResponseEntity.status(201).body(repository.save(recepcao));
        }

        return  ResponseEntity.status(400).build();
    }
    @GetMapping
    @Transactional
    @PreAuthorize("hasRole('RECEPCAO') || hasRole('PACIENTE') || hasRole('ENFERMEIRA') || hasRole('ADMIN') ")

    public ResponseEntity<List<Usuario>> listar(){
        if(repository.findAll().isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(repository.findAll());
    }
    @GetMapping("/detalhes/{id}")
    public ResponseEntity<DadosUserDTO> detalhesUser(@PathVariable Integer id){
        Integer quantidadeDoacao = repository.quantidadeDoacao(id);
        RecuperaDetalhesUsuario usuario = repository.findByDetalhesUser(id);

        LocalDate apenasData = usuario.getNascimento().toLocalDate();
        String dataFormatada = apenasData.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        System.out.println(repository.findByDetalhesUser(id));
        DadosUserDTO user = new DadosUserDTO(usuario.getQuantidade(),usuario.getTipo(),usuario.getNome(),usuario.getCpf(),quantidadeDoacao,usuario.getSexo(),dataFormatada,usuario.getPeso(),usuario.getAltura(),usuario.getEmail(),usuario.getApto());
        return ResponseEntity.status(200).body(user);
    }


    @PutMapping("{id}")
   @Transactional
   @PreAuthorize("hasRole('RECEPCAO') || hasRole('PACIENTE') || hasRole('ENFERMEIRA') || hasRole('ADMIN') ")
    public  ResponseEntity atualizarUser(@RequestBody AtualizarUser dados, @PathVariable long id){

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
    @DeleteMapping("{id}")
    @Transactional
    @PreAuthorize("hasRole('RECEPCAO') || hasRole('PACIENTE') || hasRole('ENFERMEIRA') || hasRole('ADMIN') ")
    public  ResponseEntity DeletaUser(@PathVariable long id){
        repository.deleteById(id);
        return ResponseEntity.status(204).build();
    }

}
