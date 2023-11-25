package org.example.Controller;

import jakarta.validation.Valid;
import org.example.Domain.Email;
import org.example.Domain.FilaObj;
import org.example.Domain.PilhaObg;
import org.example.Domain.Usuario;
import org.example.Records.emailDto;
import org.example.Service.EmailService;
import org.example.infra.Security.TokenService;
import org.example.interfaces.EmailRepository;
import org.example.interfaces.UsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicInteger;
@RequestMapping("/fila")
@RestController
public class FilaEmalsController {


    @Autowired
    EmailService emailService;
    @Autowired
    private UsuarioRepository repository;
    @Autowired
    EmailRepository emailRepository;
    FilaObj<String> enviados = new FilaObj<>();


    @PostMapping("/enviarEmails")
    public ResponseEntity<AtomicInteger> enviarEmails(@RequestBody @Valid emailDto requestEmail) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        List<Usuario> usuarios = repository.findAll();
        AtomicInteger quantidadeEmail = new AtomicInteger();

        while (!usuarios.isEmpty()) {
            List<CompletableFuture<Void>> tasks = new ArrayList<>();


                for (int i = 0; i < usuarios.size(); i++) {
                    Usuario usuario = usuarios.get(i);
                    String usuarioEmail = usuario.getEmail();
                    enviados.enfileirar(usuarioEmail);
                    System.out.println(usuarioEmail);

                    usuarios.remove(usuarios.get(i));
                    System.out.println(enviados + "menor que 10");
                }
                envioEmail(requestEmail,enviados,quantidadeEmail);



            CompletableFuture<Void> allOf = CompletableFuture.allOf(tasks.toArray(new CompletableFuture[0]));

            // Aguarde até que todas as tarefas tenham sido concluídas
            try {
                allOf.get(); // Isso garante que você aguarde a conclusão de todas as tarefas antes de prosseguir
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        // Encerre o scheduler
        scheduler.shutdown();

        return ResponseEntity.status(200).body(quantidadeEmail);
    }

    private void envioEmail(emailDto requestEmail, FilaObj<String> usuarios, AtomicInteger quantidadeEmail) {

        for (int i = 0; i < usuarios.tamanho(); i++) {
            try {
                Email email = new Email();
                requestEmail.setEmailTo(usuarios.pegaPrimeiro());
                usuarios.desenfileirar();
                BeanUtils.copyProperties(requestEmail, email);
                emailService.sendEmail(email);
                emailRepository.save(email);
                quantidadeEmail.incrementAndGet();
            } catch (Exception e) {
                e.printStackTrace(); // Lide com exceções apropriadas aqui
            }
        }
    }
}
