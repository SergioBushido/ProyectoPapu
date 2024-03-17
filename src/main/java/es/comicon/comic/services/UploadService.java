package es.comicon.comic.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.nio.file.StandardCopyOption;

@Service
public class UploadService {

    private final Path rootLocation = Paths.get("uploads");

    public UploadService() {
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new RuntimeException("No se pudo inicializar el directorio de almacenamiento.", e);
        }
    }

    public String store(MultipartFile file) throws IOException {
        String fileName = sanitizeFileName(file.getOriginalFilename());
        Files.copy(file.getInputStream(), this.rootLocation.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
        return fileName;
    }

    private String sanitizeFileName(String fileName) {
        return fileName != null ? fileName.replaceAll("[^a-zA-Z0-9\\.\\-]", "_") : "defaultName";
    }
}

