package org.example.Controller;

import org.example.Service.ArquivoCsvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.zip.DataFormatException;

@RestController
@RequestMapping("/Api/arquivos")
public class ArquivoController {

    @Autowired
    private ArquivoCsvService service;

    @PostMapping
    public ResponseEntity<?> upload(@RequestParam("image") MultipartFile file, @RequestParam Long id) throws IOException {

        String upload = service.uploadImagem(file, id);
        if (upload != null) {
            return ResponseEntity.ok().body(upload);
        }
        return ResponseEntity.ok().body("nao foi possivel ler a imagem");

    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> dowload(@PathVariable Long id) throws DataFormatException {

        byte[] imagem = service.dowloadImagem(id);

        if (imagem == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imagem);
    }


}
