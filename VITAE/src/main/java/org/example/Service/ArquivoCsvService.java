package org.example.Service;

import org.example.DTO.AgendamentoDTO;
import org.example.DTO.UsuarioDTO;
import org.example.Domain.Paciente;
import org.example.Domain.Usuario;
import org.example.Records.Usuario.RecordUsuario;
import org.example.interfaces.AgendaRepository;
import org.example.interfaces.RecuperaValoresUsuario;
import org.example.interfaces.RecuperarValoresAgendamento;
import org.example.interfaces.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArquivoCsvService {


    @Autowired
    private AgendaRepository serviceRepository;
    private AgendaRepository repository;


    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");


    public void recuperarValores() {

        List<RecuperarValoresAgendamento> resultados = serviceRepository.buscaDoadorAgendado();

        List<AgendamentoDTO> testes = resultados.stream()
                .map(result -> new AgendamentoDTO(result.getNome(), result.getHorario(), result.getCpf()))
                .collect(Collectors.toList());
        for(var i = 0; i < testes.size(); i++){
            System.out.println(testes.get(i));

        }

        for (int i = 0; i < testes.size() - 1; i++) {
            for (int j = i + 1; j < testes.size(); j++) {
                int hora1 = testes.get(i).getHorario().getHour();
                int hora2 = testes.get(j).getHorario().getHour();
                if (hora1 > hora2) {
                    AgendamentoDTO aux = testes.get(i);
                    testes.set(i, testes.get(j));
                    testes.set(j, aux);
                }
            }
        }

        gravaArquivoCsv(testes,"Agendamentos csv Do Dia");
        gravaArquivoTxt(testes,"Agendamentos txt Do Dia");
    }

    public static void gravaArquivoCsv(List<AgendamentoDTO> lista, String nomeArq) {
        FileWriter arq = null;
        Formatter saida = null;
        Boolean deuRuim = false;

        nomeArq += LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + ".csv";

        try {
            arq = new FileWriter(nomeArq);
            saida = new Formatter(arq);
            saida.format("%s,%s,%s\n", "CPF:", "NOME:", "HORÁRIO:");
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
    }

    public static void gravaArquivoTxt(List<AgendamentoDTO> lista, String nomeArq) {
        FileWriter arq = null;
        Formatter saida = null;
        Boolean deuRuim = false;

        nomeArq += LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + ".txt";

        try {
            arq = new FileWriter(nomeArq);
            saida = new Formatter(arq);
            saida.format("%s,%s,%s\n", "CPF", "NOME", "HORÁRIO:");
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
    }

public int doadorAgendamento(LocalTime horarioBuscado) {
    List<RecuperarValoresAgendamento> resultados = serviceRepository.buscaDoadorAgendado();
    List<LocalTime> horarios = new ArrayList<>();

    for (RecuperarValoresAgendamento resultado : resultados) {
        LocalTime hora = resultado.getHorario().toLocalTime();
        horarios.add(hora);
    }

    int indInf = 0;
    int indSup = horarios.size() - 1;

    while (indInf <= indSup) {
        int meio = (indInf + indSup) / 2;
        if (horarios.get(meio).equals(horarioBuscado)) {
            return meio; // Encontrou o horário exato
        } else if (horarioBuscado.isAfter(horarios.get(meio))) {
            indSup = meio - 1;
        } else {
            indInf = meio + 1;
        }
    }

    return -1; // Horário não encontrado
}

    public UsuarioDTO trazerInformacoesAgendamento(int i){
        System.out.println("o indice é "+ i);
        List<RecuperarValoresAgendamento> resultados = serviceRepository.buscaDoadorAgendado();
        for(int j = 0; j < resultados.size();j++){
            System.out.println(resultados.get(j).getId_usuario());
            System.out.println(resultados.get(j).getNome());
            System.out.println(resultados.get(j).getHorario());
            System.out.println(resultados.get(j).getCpf());
        }

        Long id = 0l;
        id = resultados.get(i).getId_usuario();
        System.out.println(id);

        RecuperaValoresUsuario usuario = serviceRepository.recupera(id);
        UsuarioDTO paciente = new UsuarioDTO( usuario.getEmail(), usuario.getCpf(),usuario.getNome());
        System.out.println(paciente);
        return paciente;
    }

}
//    public int doadorAgendamento(int horario){
//        List<RecuperarValoresAgendamento> resultados = serviceRepository.buscaDoadorAgendado();
//        List<Integer> horarios = new ArrayList<>();
//        for(int i = 0; i < resultados.size();i++){
//            int hora = resultados.get(i).getHorario().getHour();
//            horarios.add(hora);
//            System.out.println(hora);
//        }
//        int indInf,indSup, meio;
//
//
//          indInf = 0;
//          indSup = horarios.size() - 1; /* índice superior */
//        while (indInf <= indSup){
//            meio = (indInf+ indSup)/2;
//            if(horarios.get(meio) == horario) {
//                return meio;
//            } else if (horario < horarios.get(meio)) {
//                indSup = meio - 1;
//            }else {
//                indInf = meio+1;
//            }
//        }
//        return -1;
//
//    }

