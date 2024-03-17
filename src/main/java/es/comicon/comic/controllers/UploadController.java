package es.comicon.comic.controllers;


import es.comicon.comic.models.Category;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import lombok.extern.slf4j.Slf4j;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

@Tag(name = "UploadController", description = "Controlador para operaciones relacionadas con la subida de ficheros")
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
@Slf4j//libreria para crear log (eventos,errores,etc)
public class UploadController {

    private final Path rootLocation = Paths.get("pollas");//indicamos con Path donde esta el directorio en raiz donde se guarda lo que suba

    // Constructor para crear el directorio si no existe
    public UploadController() {
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new RuntimeException("No se pudo inicializar el directorio de almacenamiento.", e);
        }
    }
    @Operation(summary = "Realiza la subida de un fichero")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fichero subido exitosamente",
                    content = @Content(schema = @Schema(implementation = Category.class))),
            @ApiResponse(responseCode = "500", description = "Error al subir el fichero")
    })
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/upload")
    //ResponseEntity transforma en json las peticiones...el forntal no se enteraria si le envias un int o un string, por eso lo transforma
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
            Files.copy(file.getInputStream(), this.rootLocation.resolve(fileName));//le dice la ruta donde copiar el archivo
            log.info("Archivo subido correctamente: {}", fileName);//sistema de log que viene por la notacion @Slf4j
            return ResponseEntity.ok("Archivo subido con éxito: " + fileName);
        } catch (IOException e) {
            log.error("Error al guardar el archivo", e);
            return ResponseEntity.internalServerError().body("Error al subir el archivo: " + e.getMessage());
        }
    }
}