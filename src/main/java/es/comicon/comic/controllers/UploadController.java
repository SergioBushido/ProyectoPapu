package es.comicon.comic.controllers;


import es.comicon.comic.models.Category;
import es.comicon.comic.services.UploadService;
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

import java.io.IOException;

@Tag(name = "UploadController", description = "Controlador para operaciones relacionadas con la subida de ficheros")
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
@Slf4j//libreria para crear log (eventos,errores,etc)
public class UploadController {

    private UploadService uploadService;

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
        if (!isSupportedContentType(contentType)) {
            return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body("Tipo de archivo no permitido.");
        }

        try {
            String fileName = uploadService.store(file);
            log.info("Archivo subido correctamente: {}", fileName);
            return ResponseEntity.ok("Archivo subido con éxito: " + fileName);
        } catch (IOException e) {
            log.error("Error al guardar el archivo", e);
            return ResponseEntity.internalServerError().body("Error al subir el archivo: " + e.getMessage());
        }
    }

    private boolean isSupportedContentType(String contentType) {
        return "application/pdf".equals(contentType) ||
                "application/msword".equals(contentType) ||
                "application/vnd.openxmlformats-officedocument.wordprocessingml.document".equals(contentType);
    }
}