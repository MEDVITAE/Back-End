package org.example.Service;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.HashSet;
import java.util.Set;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;


public class Utils {
    public static byte[] compressaoImagem(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setLevel(Deflater.BEST_COMPRESSION);
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] tmp = new byte[50 * 1024 * 1024];
        while (!deflater.finished()) {
            int size = deflater.deflate(tmp);
            outputStream.write(tmp, 0, size);
        }
        try {
            outputStream.close();
        } catch (Exception ignorar) {

        }
        return outputStream.toByteArray();

    }

    public static byte[] descomprimirImagem(byte[] data) throws DataFormatException {
        Inflater Inflater = new Inflater();
        Inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] tmp = new byte[50 * 1024 * 1024];


        while (!Inflater.finished()) {
            int count = Inflater.inflate(tmp);
            outputStream.write(tmp, 0, count);
        }
        try {
            outputStream.close();
        } catch (Exception ignorar) {

        }
        return outputStream.toByteArray();

    }

    public static Boolean lerArquivo(String caminho) {
        File imageFile = new File(caminho);
        Tesseract tess4j = new Tesseract();
        tess4j.setDatapath("\\home\\ubuntu\\Tesseract-OCR\\tessdata");
        String textoTesseract = "";
        String palavrasDoc = palavras();
        try {
            String result = tess4j.doOCR(imageFile);
            textoTesseract = result;
            System.out.println(result);
        } catch (TesseractException e) {
            System.out.println(e + "TESSERACT NAO CONSEGUIU LER A IMAGEM");
        }

        String[] palavras1 = palavrasDoc.toLowerCase().split("\\s+");
        String[] palavras2 = textoTesseract.toLowerCase().split("\\s+");


        Set<String> set1 = new HashSet<>();
        Set<String> set2 = new HashSet<>();

        for (String palavra : palavras1) {
            set1.add(palavra);
        }
        for (String palavra : palavras2) {
            set2.add(palavra);
        }

        Set<String> intersecao = new HashSet<>(set1);
        intersecao.retainAll(set2);

        // Se houver interseção, há palavras repetidas
        if (!intersecao.isEmpty() && intersecao.size() > 5) {
            System.out.println("As strings têm palavras repetidas.");
            System.out.println("Palavras repetidas: " + intersecao);
            return true;
        } else {
            System.out.println("As strings não têm palavras repetidas.");
            return false;
        }

    }

    public static boolean apagarArquivo(String caminhoArquivo) {
        File arquivo = new File(caminhoArquivo);

        if (arquivo.exists()) {
            if (arquivo.delete()) {
                System.out.println("Arquivo deletado com sucesso: " + caminhoArquivo);
                return true;
            } else {
                System.err.println("Falha ao deletar o arquivo: " + caminhoArquivo);
                return false;
            }
        } else {
            System.err.println("O arquivo não existe: " + caminhoArquivo);
            return false;
        }
    }
    public static String palavras() {
        return
                "documento informações conteúdo texto página título autor data assunto parágrafo introdução conclusão referência bibliografia "
                        + "sumário capítulo subtítulo nota citação figura tabela apêndice referências glossário índice anexo revisão versão "
                        + "edição publicação editor revisor data informação detalhes nota rodapé cabeçalho marcador link hiperlink resumo "
                        + "abstrato introdução discussão resultado conclusão metodologia objetivo pesquisa estudo análise discussão "
                        + "conclusão recomendação conclusivo conclusivamente consideração considerações agradecimento agradecimentos "
                        + "observação observações discussões referências bibliográficas revisões compilação conclusões referentes "
                        + "direcionadas referida referidas referente referentes referidos devido conforme mencionado mencionada "
                        + "mencionadas mencionado mencionados alvo destino expediente essencial base primordial fundamental significado "
                        + "significativo importante relevante relevância necessidade requisição necessária necessário obrigatório "
                        + "obrigação requerimento requerida requerido requeridos requerimento demanda demandado demandada demandas "
                        + "solicitação solicitada solicitado solicitados solicitante observar atentar estar estarão estavam "
                        + "encontrar encontrado encontrada encontrados encontradas considerando pressupondo prévio prévia previamente "
                        + "previsto definido estabelecido acordado acordo concordado pactuado descrito descritos descrita descritas "
                        + "devidamente conforme mencionado conforme mencionados conforme mencionada conforme citado conforme citados "
                        + "conforme citada mencionados anteriormente mencionada anteriormente citados anteriormente citadas "
                        + "citadas anteriormente conforme discutido discutida conforme discutidos conforme discutidas conforme abordado "
                        + "conforme abordados conforme abordada conforme abordadas conforme referido conforme referidos conforme referida "
                        + "conforme referidas conforme explicitado conforme explicitada conforme explicitados conforme explicitadas "
                        + "seguinte seguintes adicional adicionalmente aprofundamento aprofundamentos informação informações "
                        + "adicionalmente procedimento procedimentos exposto exposta expostos expostas explica explicar explicação "
                        + "definição definir definido compreensão compreender compreensões exemplificação exemplificar exemplificado "
                        + "ilustração ilustrar ilustrado discorrer discorrerá discorrerão discorreu discorreram elaboração elaborar "
                        + "elaborado análise análises referente a de ou e para com no na se "
                        + "mas também porque porém contudo todavia entretanto então assim desse desta disso desde desse "
                        + "dessa esteis estivermos estiveram estivéssemos estejam estivesse estivesseis estivera estivéramos "
                        + "estiveram estiveramos estiver estivemos esteja estejais estivéssemos estivessem estiveres estiverem "
                        + "estarei estarás estará estaremos estareis estarão estejamos estivesse estivesseis sao paulo rio de janeiro caetano";


    }
    public static Integer salvarArquivo(MultipartFile arquivo, String caminho) {

        if (!arquivo.isEmpty()) {
            try {

                String savePath = caminho;

                File file = new File(savePath);

                arquivo.transferTo(file);
                System.out.println(("Arquivo posto na pasta: " + caminho));
                return 1;
            } catch (NoSuchFileException e) {
                System.err.println("Arquivo não encontrado: " + e.getMessage());

                return 0;
            } catch (FileNotFoundException e) {
                System.err.println("Arquivo não encontrado: " + e.getMessage());
                return 0;
            } catch (IOException e) {
                System.err.println("Arquivo não encontrado: " + e.getMessage());
            }
        } else {
            System.err.println("Arquivo nao surportado ");
            return 0;
        }
        return 0;
    }

}
