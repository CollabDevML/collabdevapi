package com.groupe1.collabdev_api.controllers;

import com.groupe1.collabdev_api.entities.enums.TypeFichier;
import com.groupe1.collabdev_api.services.UploadService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
@Tag(name="Fichier Api",
        description="Gestion des fichiers")
public class UploadController {

    @Autowired
    private UploadService uploadService;

    @PostMapping
    public ResponseEntity<?> uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("fileName") String fileName,
            @RequestParam("type") TypeFichier fileType
    ) {
        String filePath = uploadService.uploadFile(file, fileName, fileType);
        if (filePath == null) {
            return
                    new ResponseEntity<>(
                            "Impossible d'insérer le fichier",
                            HttpStatus.INTERNAL_SERVER_ERROR
                    );
        } else {
            return
                    new ResponseEntity<>(
                            fileName,
                            HttpStatus.CREATED
                    );
        }
    }
}
