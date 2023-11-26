package org.example.Service;

import org.example.DTO.AgendamentoDTO;
import org.example.DTO.UsuarioDTO;
import org.example.Domain.ArquivoBanco;
import org.example.Domain.Paciente;
import org.example.Domain.Usuario;
import org.example.Records.Usuario.RecordUsuario;
import org.example.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.zip.DataFormatException;

@Service
public class ArquivoCsvService {


    @Autowired
    private AgendaRepository serviceRepository;
    private AgendaRepository repository;
    @Autowired
    private ImagensRepository imagensRepository;
    @Autowired
    private  UsuarioRepository usuarioRepository;


    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");


    public void recuperarValores() {

        List<RecuperarValoresAgendamento> resultados = serviceRepository.buscaDoadorAgendado();

        List<AgendamentoDTO> agendamentos = resultados.stream()
                .map(result -> new AgendamentoDTO(result.getNome(), result.getHorario(), result.getCpf()))
                .collect(Collectors.toList());

        for (int i = 0; i < agendamentos.size() - 1; i++) {
            for (int j = i + 1; j < agendamentos.size(); j++) {
                int hora1 = agendamentos.get(i).getHorario().getHour();
                int hora2 = agendamentos.get(j).getHorario().getHour();
                if (hora1 > hora2) {
                    AgendamentoDTO aux = agendamentos.get(i);
                    agendamentos.set(i, agendamentos.get(j));
                    agendamentos.set(j, aux);
                }
            }
        }

        gravaArquivoCsv(agendamentos,"Agendamentos csv Do Dia ");
        gravaArquivoTxt(agendamentos,"Agendamentos txt Do Dia ");
    }

    public static void gravaArquivoCsv(List<AgendamentoDTO> lista, String nomeArq) {
        FileWriter arq = null;
        Formatter saida = null;
        Boolean deuRuim = false;

        nomeArq += LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + ".csv";

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
    }

    public static void gravaArquivoTxt(List<AgendamentoDTO> lista, String nomeArq) {
        FileWriter arq = null;
        Formatter saida = null;
        Boolean deuRuim = false;

        nomeArq += LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + ".txt";

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

        UsuarioDTO paciente = new UsuarioDTO( usuario.getNome(), usuario.getEmail(),usuario.getCpf());
        System.out.println(paciente);
        return paciente;
    }
    public String uploadImagem(MultipartFile file,Long id) throws IOException {
        Optional<Usuario> usuario = usuarioRepository.findById(id);


        ArquivoBanco imagem =    imagensRepository.save(ArquivoBanco
                .builder()
                .nome(file.getOriginalFilename())
                .foto(Utils.compressaoImagem(file.getBytes())).fkUsuario(id).build());

        if(imagem!=null){
            return "upload com sucesso : "+ file.getOriginalFilename();
        }
        return  null;
    }
    public byte[] dowloadImagem(Long id) throws DataFormatException {

        Optional<ArquivoBanco>  foto = imagensRepository.findByFkUsuario(id);
       byte[] imagem =  Utils.descomprimirImagem(foto.get().getFoto());
       return imagem;
    }

}


