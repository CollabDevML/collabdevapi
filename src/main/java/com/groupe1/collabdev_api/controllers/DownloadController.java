package com.groupe1.collabdev_api.controllers;

import com.groupe1.collabdev_api.entities.enums.TypeFichier;
import com.groupe1.collabdev_api.services.DownloadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.IOException;

@RestController
@RequestMapping("/download")
@Tag(name = "Fichier Api",
        description = "l'enregistrement des fichiers")
@CrossOrigin(origins = "http://localhost:4200")
public class DownloadController {

    @Autowired
    private DownloadService downloadService;

    @Operation(summary = "pour le téléchargement des fichiers .pdf ")
    @GetMapping("/{fileType}/{fileName}")
    public ResponseEntity<Resource> downloadFile(
            @PathVariable TypeFichier fileType,
            @PathVariable String fileName
    ) {
        try {
            byte[] data = downloadService.downloadFile(fileName, fileType);
            ByteArrayResource resource = new ByteArrayResource(data);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileName + ".pdf")
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .contentLength(data.length)
                    .body(resource);
        } catch (FileNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IOException e) {
            System.err.println("Erreur lors du téléchargement du fichier : " + e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
}