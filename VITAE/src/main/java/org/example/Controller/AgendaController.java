package org.example.Controller;

import jakarta.transaction.Transactional;
import org.example.DTO.AgendamentoDTO;
import org.example.DTO.InfoAgendamentosHospitaisDTO;
import org.example.DTO.UsuarioDTO;
import org.example.Domain.Agenda;
import org.example.Domain.Hospital;
import org.example.Domain.Usuario;
import org.example.Records.Agenda.AtualizaAgenda;
import org.example.Records.Agenda.RecordAgenda;
import org.example.Service.ArquivoCsvService;
import org.example.interfaces.AgendaRepository;
import org.example.interfaces.RecuperaNomeHospital;
import org.example.interfaces.RecuperaValoresAgendamentosDoacoes;
import org.example.interfaces.RecuperarValoresAgendamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.*;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/Agenda")

public class AgendaController {
    @Autowired
    private AgendaRepository repository;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");


    @Autowired
    private ArquivoCsvService serviceRepository;
    //private Path diretorioBase = Path.of(System.getProperty("user.dir") + "/VITAE"); // projeto
    private Path diretorioBase = Path.of(System.getProperty("java.io.tmpdir") + "/arquivos"); // temporario

    @GetMapping
    @PreAuthorize("hasRole('RECEPCAO') || hasRole('PACIENTE') || hasRole('ENFERMEIRA') ")
    public ResponseEntity<List<Agenda>> listar() {
        return ResponseEntity.status(200).body(repository.findAll());
    }
    @GetMapping("/Agendamentos/{id}")
    @PreAuthorize("hasRole('RECEPCAO') || hasRole('PACIENTE') || hasRole('ENFERMEIRA') ")
    public ResponseEntity<InfoAgendamentosHospitaisDTO> listarAgendamentosHosp(@PathVariable Long id) {

        RecuperaValoresAgendamentosDoacoes quantidade = repository.quantidadeDoacao(id);
        System.out.println(quantidade.getDoacao());
        List<RecuperaNomeHospital> hospital = repository.hospital(id);
        for(var i = 0; i < hospital.size(); i++){
            System.out.println(hospital.get(i).getNome());
        }
        List<Agenda> agenda = repository.agenda(id);
        InfoAgendamentosHospitaisDTO infoAgendamentosHospitaisDTO = new InfoAgendamentosHospitaisDTO(quantidade.getQuantidade(), quantidade.getDoacao(), agenda,hospital);

        return ResponseEntity.status(200).body(infoAgendamentosHospitaisDTO);
    }


    @PostMapping
    @PreAuthorize("hasRole('RECEPCAO') || hasRole('PACIENTE')")
    public ResponseEntity cadastrar(@RequestBody RecordAgenda dados) {
        int hospital = dados.fkHospital();
        LocalDateTime hora = dados.Horario();
        Optional<Agenda> horarioExiste = repository.findByHorarioExiste(hospital,hora);
        if(horarioExiste.isPresent()){
            return ResponseEntity.status(200).body("Agendamento ja existe");
        }
        return ResponseEntity.status(201).body(repository.save(new Agenda(dados)));
    }

    @PutMapping("/{id}")
    @Transactional
    @PreAuthorize("hasRole('RECEPCAO') || hasRole('PACIENTE')")
    public ResponseEntity atualizar(@PathVariable Long id, @RequestBody AtualizaAgenda dados) {
        if (repository.existsById(id)) {
            var agenda = repository.getReferenceById(id);
            agenda.atualizaAgenda(dados);
            return ResponseEntity.status(200).body((new AtualizaAgenda(agenda)));
        }
        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("{id}")
    @Transactional
    @PreAuthorize("hasRole('RECEPCAO') || hasRole('PACIENTE') || hasRole('ENFERMEIRA') || hasRole('ADMIN') ")
    public ResponseEntity DeletaUser(@PathVariable long id) {
        repository.deleteById(id);
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/gerarArquivoCsv")
    @PreAuthorize("hasRole('RECEPCAO')")
    public ResponseEntity<Void> getUsuariosEAgendas() {
        serviceRepository.recuperarValores();

        return ResponseEntity.status(200).build();
    }
    @GetMapping("/buscarDoadorAgendado/{hora}")

    public ResponseEntity<Object> pesquisaHorario(@PathVariable LocalTime hora) {

        if(serviceRepository.doadorAgendamento(hora) != -1){
          UsuarioDTO doador = serviceRepository.trazerInformacoesAgendamento(serviceRepository.doadorAgendamento(hora));
            return ResponseEntity.status(200).body(doador);
        } else {
            return ResponseEntity.badRequest().body("Usuario nao esta agendado");
        }


    }
    @GetMapping(value = "/dowload/{nomeArq}",produces ="text/csv")
    public ResponseEntity<Resource> download(@PathVariable String nomeArq){

        List<RecuperarValoresAgendamento> resultados = repository.buscaDoadorAgendado();

        List<AgendamentoDTO> lista = resultados.stream()
                .map(result -> new AgendamentoDTO(result.getNome(), result.getHorario(), result.getCpf()))
                .collect(Collectors.toList());
        for(var i = 0; i < lista.size(); i++){
            System.out.println(lista.get(i));

        }

        for (int i = 0; i < lista.size() - 1; i++) {
            for (int j = i + 1; j < lista.size(); j++) {
                int hora1 = lista.get(i).getHorario().getHour();
                int hora2 = lista.get(j).getHorario().getHour();
                if (hora1 > hora2) {
                    AgendamentoDTO aux = lista.get(i);
                    lista.set(i, lista.get(j));
                    lista.set(j, aux);
                }
            }
        }
        FileWriter arq = null;
        Formatter saida = null;
        Boolean deuRuim = false;

        nomeArq +=  LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + ".csv";

        try {
            arq = new FileWriter(nomeArq);
            saida = new Formatter(arq);
            saida.format("%s;%s;%s\n", "CPF:", "NOME:", "HORÁRIO:");
        } catch (IOException erro) {
            System.out.println("Erro ao abrir o arquivo");
            System.exit(1);
        }

        try {
            for (int i = 0; i < lista.size(); i++) {
                AgendamentoDTO agendamento = lista.get(i);
                String horarioFormatado = agendamento.getHorario().format(formatter); // Formatar a data e hora
                saida.format("%s;%s;%s\n",agendamento.getCpf(), agendamento.getNome(), horarioFormatado);
            }
        } catch (FormatterClosedException erro) {
            System.out.println("Erro ao gravar o arquivo");
            erro.printStackTrace();
            deuRuim = true;
        } finally {
            saida.close();
            try {
                arq.close();
            } catch (IOException erro) {
                System.out.println("Erro ao fechar o arquivo");
                deuRuim = true;
            }
            if (deuRuim) {
                System.exit(1);
            }
        }

        File file = new File(nomeArq);
        try {
            InputStream fileInputStream = new FileInputStream(file);
            InputStreamResource resource = new InputStreamResource(fileInputStream);

            return ResponseEntity.status(200)
                    .header("Content-Disposition",
                            "attachment; filename=" + nomeArq)
                    .body(resource);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new ResponseStatusException(422, "Diretório não encontrado", null);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ResponseStatusException(422, "Não foi possível converter para byte[]", null);
        }
    }




}
