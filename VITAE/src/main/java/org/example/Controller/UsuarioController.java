package org.example.Controller;

import jakarta.transaction.Transactional;

import org.example.DTO.DadosUserDTO;
import org.example.DTO.FuncionarioHospitalDTO;
import org.example.DTO.RecuperaValoresConfirmaDoacaoDTO;
import org.example.Domain.*;
import org.example.Enums.Usuarios.UserRole;
import org.example.Records.Usuario.AtualizarUser;
import org.example.Records.Usuario.RecordUsuario;
import org.example.Service.ArquivoCsvService;
import org.example.Service.EmailService;
import org.example.infra.Security.TokenService;
import org.example.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@RequestMapping("/usuario")
@RestController
public class UsuarioController {
    @Autowired
    private AuthenticationManager autenticador;
    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private EmailRepository repositoryEmail;

    @Autowired
    private TokenService tokenService;
    @Autowired
    EmailService emailService;
    @Autowired
    EmailRepository emailRepository;
    @Autowired
    ArquivoCsvService service;


    @PostMapping("/register/lista")
    public ResponseEntity CadastroLista(@RequestBody List<RecordUsuario> dados) {
        List<Object> lista = new ArrayList<>();

        for (int i = 0; i < dados.size(); i++) {

            String encripitando = new BCryptPasswordEncoder().encode((dados.get(i).senha()));
            String email = dados.get(i).email();
            UserRole role = dados.get(i).role();
            String nome = (dados.get(i).nome());
            String cpf = dados.get(i).cpf();
            int fkHospital = dados.get(i).fkHospital();

            try {

                if (role == UserRole.PACIENTE) {
                    var usuario = new Paciente(email, encripitando, role, nome, fkHospital, cpf);
                    lista.add(repository.save(usuario));

                }
                if (role == UserRole.ENFERMEIRA) {
                    var ENFERMEIRA = new Enfermeira(email, encripitando, role, nome, fkHospital, cpf);
                    lista.add(repository.save(ENFERMEIRA));

                }
                if (role == UserRole.RECEPCAO) {
                    var recepcao = new Recepcao(email, encripitando, role, nome, fkHospital, cpf);
                    lista.add(repository.save(recepcao));

                }

            } catch (Exception e) {
                return ResponseEntity.status(400).build();
            }
        }

        return ResponseEntity.status(200).body(lista);
    }

    @PostMapping("/register")
    public ResponseEntity Cadastro(@RequestBody RecordUsuario dados) {
        if (this.repository.findByEmail(dados.email()) != null) {
            this.repository.findByEmail(dados.email());

            return ResponseEntity.badRequest().build();
        }
        String encripitando = new BCryptPasswordEncoder().encode(dados.senha());
        if (dados.role() == UserRole.PACIENTE) {
            var usuario = new Paciente(dados.email(), encripitando, dados.role(), dados.nome(), dados.fkHospital(), dados.cpf());
            return ResponseEntity.status(201).body(repository.save(usuario));
        }
        if (dados.role() == UserRole.ENFERMEIRA) {
            var ENFERMEIRA = new Enfermeira(dados.email(), encripitando, dados.role(), dados.nome(), dados.fkHospital(), dados.cpf());
            return ResponseEntity.status(201).body(repository.save(ENFERMEIRA));
        }
        if (dados.role() == UserRole.RECEPCAO) {
            var recepcao = new Recepcao(dados.email(), encripitando, dados.role(), dados.nome(), dados.fkHospital(), dados.cpf());
            return ResponseEntity.status(201).body(repository.save(recepcao));
        }

        return ResponseEntity.status(400).build();
    }

    @GetMapping
    @Transactional
    @PreAuthorize("hasRole('RECEPCAO') || hasRole('PACIENTE') || hasRole('ENFERMEIRA') || hasRole('ADMIN') ")

    public ResponseEntity<List<Usuario>> listar() {
        if (repository.findAll().isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(repository.findAll());
    }

    @GetMapping("/detalhes/{id}")
    public ResponseEntity<Object> detalhesUser(@PathVariable Integer id) {
        Integer quantidadeDoacao = repository.quantidadeDoacao(id);
        if (quantidadeDoacao != 0) {
            RecuperaDetalhesUsuario usuario = repository.findByDetalhesUser(id);
            LocalDate apenasData = usuario.getNascimento();
            String dataFormatada = apenasData.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            DadosUserDTO user = new DadosUserDTO(usuario.getQuantidade(), usuario.getTipo(), usuario.getNome(), usuario.getCpf(), quantidadeDoacao, usuario.getSexo(), dataFormatada, usuario.getPeso(), usuario.getAltura(), usuario.getEmail(), usuario.getApto(), usuario.getCep(), usuario.getNumeroCasa());
            return ResponseEntity.status(200).body(user);
        } else {
            RecuperaDetalhesUsuarioSemDoacao usuario = repository.findByDetalhesUserSemDoacao(id);
            LocalDate apenasData = usuario.getNascimento();
            String dataFormatada = apenasData.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            DadosUserDTO user = new DadosUserDTO(0.0, "", usuario.getNome(), usuario.getCpf(), 0, usuario.getSexo(), dataFormatada, usuario.getPeso(), usuario.getAltura(), usuario.getEmail(), usuario.getApto(), usuario.getCep(), usuario.getNumeroCasa());
            return ResponseEntity.status(200).body(user);
        }


    }

    @GetMapping("/detalhesDoacao/{cpf}")
    public ResponseEntity<Object> detalhesDoacao(@PathVariable String cpf) {

        RecuperaDetalhesUsuarioDoaco usuario = repository.findByCpf(cpf);
        LocalDate apenasData = usuario.getNascimento();
        String dataFormatada = apenasData.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        RecuperaValoresConfirmaDoacaoDTO user = new RecuperaValoresConfirmaDoacaoDTO(usuario.getIdAgenda(), usuario.getTipo(), usuario.getNome(), usuario.getCpf(), usuario.getSexo(), dataFormatada, usuario.getEmail(), usuario.getCep(), usuario.getNumeroCasa());
        return ResponseEntity.status(200).body(user);


    }

    @PutMapping("/detalhesUser/{id}")
    public ResponseEntity atualizarInfoUser(@PathVariable Long id, @RequestBody String dados) {
        if (repository.existsById(id)) {
            System.out.println(dados);
            repository.AtualizaEmail(dados, id);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).build();
    }


    @PutMapping("/{id}")
    @PreAuthorize("hasRole('RECEPCAO') || hasRole('PACIENTE') || hasRole('ENFERMEIRA') || hasRole('ADMIN') ")
    public ResponseEntity atualizarUser(@RequestBody AtualizarUser dados, @PathVariable long id) {

        try {
            var usuario = repository.getReferenceById(id);
            usuario.Atualiza(dados, usuario.getSenha());
            return ResponseEntity.ok(repository.save(usuario));
        } catch (Exception e) {

            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    @PreAuthorize("hasRole('RECEPCAO') || hasRole('PACIENTE') || hasRole('ENFERMEIRA') || hasRole('ADMIN') ")
    public ResponseEntity DeletaUser(@PathVariable long id) {
        repository.deleteById(id);
        return ResponseEntity.status(204).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Usuario>> usuarioId(@PathVariable Long id) {
        Optional<Usuario> usuario = repository.findById(id);
        if (usuario.isPresent()) {
            return ResponseEntity.ok(usuario);
        }
        return ResponseEntity.badRequest().build();

    }

    @GetMapping("/arquivoTxT")
    public ResponseEntity<List<FuncionarioHospitalDTO>> recuperaFuncionarioHospital() {
        List<RecuperaValoresFuncionarioHospital> funcionarios = repository.findByFuncionario();

        if (!funcionarios.isEmpty()) {
            List<FuncionarioHospitalDTO> funcionariosHospitais = funcionarios.stream()
                    .map(result -> new FuncionarioHospitalDTO(
                            result.getEmail(), result.getSenha(), result.getRole(), result.getNome(),
                            result.getFk_Hospital(), result.getCpf(), result.getEmailHospital(),
                            result.getNomeHospital(), result.getSenhaHospital(), result.getCnpj()))
                    .collect(Collectors.toList());
            service.gravaArquivoTxt(funcionariosHospitais, "arquivo teste");

            return ResponseEntity.ok().body(funcionariosHospitais);
        }

        return ResponseEntity.badRequest().build();

    }

    @PostMapping("/ler")
    public ResponseEntity<List<Usuario>> handleFileUpload(@RequestParam MultipartFile file, @RequestParam String nome) throws IOException {


        // Crie o diretório se não existir
        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
        // Obtenha o nome do arquivo original
        String originalFileName = nome;

        // Construa o caminho completo para o arquivo
        Path filePath = Paths.get(UPLOAD_DIR, originalFileName);

        // Salve o arquivo no diretório
        file.transferTo(filePath.toFile());

        List<Usuario> teste = service.importarTxt(nome);
        for (Usuario a : teste) {
            repository.save(a);
        }
        return ResponseEntity.status(200).body(teste);


    }

    private static final String UPLOAD_DIR = "C:\\Users\\jvtenorio\\OneDrive - Stefanini\\Desktop\\sprint_api\\Back-End\\VITAE"; // Caminho do diretório onde os arquivos serão salvos


}





