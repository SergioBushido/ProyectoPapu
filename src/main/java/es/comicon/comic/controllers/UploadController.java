package es.comicon.comic.controllers;


import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import lombok.extern.slf4j.Slf4j;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

@RestController
@RequestMapping("/api/files")
@Slf4j
public class UploadController {

    private final Path rootLocation = Paths.get("fileUploads");

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("No se ha seleccionado un archivo para subir.");
        }

        String contentType = file.getContentType();
        if (!"application/pdf".equals(contentType) && !"application/msword".equals(contentType) && !"application/vnd.openxmlformats-officedocument.wordprocessingml.document".equals(contentType)) {
            return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body("Tipo de archivo no permitido.");
        }

        try {
            // Sanitización del nombre del archivo
            String fileName = "";
            String originalName = file.getOriginalFilename();
            if (originalName != null) {
                fileName = originalName.replaceAll("[^a-zA-Z0-9\\.\\-]", "_");
            } else {
                fileName = "defaultName";
            }

            // Guardado del archivo
            // TODO TAREA - faltaría controlar que el archivo ya haya sido subido
            Files.copy(file.getInputStream(), this.rootLocation.resolve(fileName));
            log.info("Archivo subido correctamente: {}", fileName);
            return ResponseEntity.ok("Archivo subido con éxito: " + fileName);
        } catch (IOException e) {
            log.error("Error al guardar el archivo", e);
            return ResponseEntity.internalServerError().body("Error al subir el archivo: " + e.getMessage());
        }
    }

    // Constructor para crear el directorio si no existe
    public UploadController() {
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new RuntimeException("No se pudo inicializar el directorio de almacenamiento.", e);
        }
    }
}