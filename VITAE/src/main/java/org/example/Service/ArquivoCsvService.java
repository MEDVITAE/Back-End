package org.example.Service;

import org.example.DTO.AgendamentoDTO;
import org.example.interfaces.AgendaRepository;
import org.example.interfaces.RecuperarValoresAgendamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArquivoCsvService {


    @Autowired
    private AgendaRepository serviceRepository;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");


    public void recupararValores() {

        List<RecuperarValoresAgendamento> resultados = serviceRepository.buscaDoadorAgendado();
        List<AgendamentoDTO> testes = resultados.stream()
                .map(result -> new AgendamentoDTO(result.getNome(), result.getHorario()))
                .collect(Collectors.toList());

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
            saida.format("%s,%s\n", "NOME:", "HORÁRIO:");
        } catch (IOException erro) {
            System.out.println("Erro ao abrir o arquivo");
            System.exit(1);
        }

        try {
            for (int i = 0; i < lista.size(); i++) {
                AgendamentoDTO agendamento = lista.get(i);
                String horarioFormatado = agendamento.getHorario().format(formatter); // Formatar a data e hora
                saida.format("%s;%s\n", agendamento.getNome(), horarioFormatado);
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
            saida.format("%s,%s\n", "NOME", "HORÁRIO:");
        } catch (IOException erro) {
            System.out.println("Erro ao abrir o arquivo");
            System.exit(1);
        }

        try {
            for (int i = 0; i < lista.size(); i++) {
                AgendamentoDTO agendamento = lista.get(i);
                String horarioFormatado = agendamento.getHorario().format(formatter); // Formatar a data e hora
                saida.format("%s;%s\n", agendamento.getNome(), horarioFormatado);
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
}

