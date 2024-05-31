package org.example.Service;

import org.example.DTO.AgendamentoDTO;
import org.example.DTO.FuncionarioHospitalDTO;
import org.example.DTO.UsuarioDTO;
import org.example.Domain.ArquivoBanco;
import org.example.Domain.Recepcao;
import org.example.Domain.Usuario;
import org.example.Enums.Usuarios.UserRole;
import org.example.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
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
                .map(funcionario -> new AgendamentoDTO(funcionario.getNome(), funcionario.getHorario(), funcionario.getCpf()))
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
                AgendamentoDTO funcionario = lista.get(i);
                String horarioFormatado = funcionario.getHorario().format(formatter); // Formatar a data e hora
                saida.format("%s;%s;%s\n",funcionario.getCpf(), funcionario.getNome(), horarioFormatado);
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
    public static void gravaRegistro(String registro, String nomeArq) {
        BufferedWriter saida = null;

        // Abre o arquivo
        try {
            saida = new BufferedWriter(new FileWriter(nomeArq,true));
        }
        catch (IOException erro) {
            System.out.println("Erro na abertura do arquivo");
        }

        // Grava o registro e fecha o arquivo
        try {
            saida.append(registro + "\n");
            saida.close();
        }
        catch (IOException erro) {
            System.out.println("Erro ao gravar o arquivo");
            erro.printStackTrace();
        }
    }

    public  void gravaArquivoTxt(List<FuncionarioHospitalDTO> lista, String nomeArq) {

        int contaRegDados = 0;

        nomeArq += ".txt";
        String header = "00";
        header += "cadastrarFuncionario";
        header += LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        header += "01";
        gravaRegistro(header, nomeArq);
        for(FuncionarioHospitalDTO f : lista){
            String corpo = "01";
            corpo += String.format("%-39s", f.getEmail());
            corpo += String.format("%-15s", f.getSenha());
            corpo += String.format("%-10s", f.getRole());
            corpo += String.format("%-31s", f.getNome());
            corpo += String.format("%-25s", f.getCpf());
            corpo += String.format("%-24s", f.getFkHospital());
            corpo += String.format("%-35s", f.getEmailHospital());
            corpo += String.format("%-30s", f.getNomeHospital());
            corpo += String.format("%-31s", f.getSenhaHospital());
            corpo += String.format("%-25s", f.getCnpj());
            gravaRegistro(corpo, nomeArq);
            contaRegDados++;

        }
        String trailer = "02";
        trailer += String.format("%010d", contaRegDados);
        gravaRegistro(trailer, nomeArq);



   /*     try {
          *//*  for (int i = 0; i < lista.size(); i++) {
                FuncionarioHospitalDTO funcionario = lista.get(i);

                saida.format("%-39s;%-15s;%-10s;%-31s;%-25s;%-24s;%-35s;%-30s;%-31s;%-25s\n",
                        funcionario.getEmail(), funcionario.getSenha(), funcionario.getRole(),funcionario.getNome(),
                        funcionario.getCpf(),funcionario.getFkHospital(), funcionario.getEmailHospital(),
                        funcionario.getNomeHospital(), funcionario.getSenhaHospital(), funcionario.getCnpj());
            }*//*
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
        }*/

    }
 public List<Usuario> importarTxt(String nomeArq){
        {

            BufferedReader entrada = null;
            String tipoRegistro;
            String registro, tipoArquivo,dataHora,versaoLayout;
            String  nome,email,senha,cpf;
            UserRole role;
            String nomeHospital,emailHospital,senhaHospital,cnpj;

            Integer fk;

            int contaRegDadosLidos = 0;
            int qtdRegDadosGravados;


            List<Usuario> listaLida = new ArrayList();


            try {
                entrada = new BufferedReader(new FileReader(nomeArq));
            }
            catch (IOException erro) {
                System.out.println("Erro na abertura do arquivo");
            }

            // Leitura do arquivo
            try {
                registro = entrada.readLine();

                while (registro != null) {
                    // obtem os 2 primeiros caracteres do registro lido
                    // 1o argumento do substring eh o indice do que se quer obter, iniciando de zero
                    // 2o argumento do substring eh o indice final do que se deseja, MAIS UM
                    // 012345
                    // 00NOTA
                    tipoRegistro = registro.substring(0,2);

                    if (tipoRegistro.equals("00")) {
                        System.out.println("É um registro de header");
                        System.out.println("Tipo de arquivo: " + registro.substring(2,25));
                        System.out.println("Data e hora de geração do arquivo: " + registro.substring(30,49));
                        System.out.println("Versão do documento de layout: " + registro.substring(49,51));

                    }
                    else if (tipoRegistro.equals("02")) {
                        System.out.println("É um registro de trailer");
                        qtdRegDadosGravados= Integer.parseInt(registro.substring(0,2));
                        if (contaRegDadosLidos == qtdRegDadosGravados) {
                            System.out.println("Quantidade de reg de dados gravados é compatível com " +
                                    "a quantidade de reg de dados lidos");
                        }
                        else {
                            System.out.println("Quantidade de reg de dados gravados é incompatível com " +
                                    "a quantidade de reg de dados lidos");
                        }
                    }
                    else if (tipoRegistro.equals("01")) {
                        System.out.println("É um registro de corpo");
                        nome= registro.substring(2,24).trim();
                        email= registro.substring(24,64).trim();
                        senha=registro.substring(64,79).trim();
                        role= UserRole.valueOf(registro.substring(79,89).trim());
                        cpf = registro.substring(89,100).trim();
                        fk = Integer.parseInt(registro.substring(100,102));
                        nomeHospital = registro.substring(102,124).trim();
                        emailHospital = registro.substring(124,164).trim();
                        cnpj = registro.substring(164,178).trim();


                        // Incrementa o contador de reg de dados lidos
                        contaRegDadosLidos++;
                        String encripitando = new BCryptPasswordEncoder().encode(senha);

                        // Cria um objeto Aluno com os dados lidos do registro
                        Recepcao usuario = new Recepcao(email, encripitando, role, nome, fk, cpf);

                        // Se estivesse conectado a um banco de dados
                        // repository.save(a);

                        // Como não estamos conectados a um BD, vamos adicionar
                        // na listaLida
                        listaLida.add(usuario);
                    }
                    else {
                        System.out.println("Registro inválido");
                    }

                    // Le o proximo registro
                    registro = entrada.readLine();

                }  // fim do while
                // Fecha o arquivo
                entrada.close();
            } // fim do try
            catch (IOException erro) {
                System.out.println("Erro ao ler o arquivo");
                erro.printStackTrace();
            }

            // Exibe a lista lida
            System.out.println("\nLista lida do arquivo:");
            for (Usuario a : listaLida) {
                System.out.println(a);
            }

            // Aqui tb seria possível salvar a lista no BD
            // repository.saveAll(listaLida);
            return listaLida;
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
        byte[] imagemCompressada = Utils.compressaoImagem(
                file.getBytes());
        String caminho = "\\home\\ubuntu\\teste.jpeg";
        Boolean fotoValida = false;

        if (Utils.salvarArquivo(file, caminho) > 0) {
            fotoValida = Utils.lerArquivo(caminho);

            if (fotoValida) {
                ArquivoBanco imagem = imagensRepository.save(ArquivoBanco
                        .builder()
                        .nome(file.getOriginalFilename())
                        .foto(imagemCompressada).
                        fkUsuario(id).
                        build());


                if (imagem != null) {
                    Utils.apagarArquivo(caminho);
                    return "upload com sucesso : " + file.getOriginalFilename();
                }

            }
        }

        return  null;
    }
    public byte[] dowloadImagem(Long id) throws DataFormatException {

        Optional<ArquivoBanco>  foto = imagensRepository.findTopByFkUsuarioOrderByIdDesc(id);

        if (foto.isEmpty()){
            return null;
        }

       byte[] imagem =  Utils.descomprimirImagem(foto.get().getFoto());
       return imagem;
    }

}


