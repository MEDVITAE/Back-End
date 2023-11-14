package org.example.Controller;

import org.example.DTO.AgendamentoDTO;
import org.example.Domain.Arquivo;
import org.example.interfaces.ArquivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.*;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.Optional;
import java.util.UUID;
@RestController
@RequestMapping("/arquivos")
@CrossOrigin(origins = "http://localhost:3000/",allowedHeaders = "*")

public class ArquivoController {

    @Autowired
    private ArquivoRepository arquivoRepository;

    private Path diretorioBase = Path.of(System.getProperty("user.dir") + "/VITAE"); // projeto
  //  private Path diretorioBase = Path.of(System.getProperty("java.io.tmpdir") + "/arquivos"); // temporario

//    @PostMapping("/upload")
//    public ResponseEntity<Arquivo> upload(@RequestParam("arquivo") MultipartFile file) {
//
//        if (file.isEmpty()){
//            return ResponseEntity.status(400).build();
//        }
//
//        if (!this.diretorioBase.toFile().exists()) {
//            this.diretorioBase.toFile().mkdir();
//        }
//
//
//        String nomeArquivoFormatado = formatarNomeArquivo(file.getOriginalFilename());
//
//        String filePath = this.diretorioBase + "/" + nomeArquivoFormatado;
//        File dest = new File(filePath);
//        try {
//            file.transferTo(dest);
//        } catch (IOException e) {
//            e.printStackTrace();
//            throw new ResponseStatusException(422, "Não foi possível salvar o arquivo", null);
//        }
//
//        Arquivo arquivo = new Arquivo();
//        arquivo.setDataUpload(LocalDate.now());
//        arquivo.setNomeArquivoOriginal(file.getOriginalFilename());
//        arquivo.setNomeArquivoSalvo(nomeArquivoFormatado);
//        Arquivo arquivoBanco = arquivoRepository.save(arquivo);
//
//        return ResponseEntity.status(200).body(arquivoBanco);
//    }


}
