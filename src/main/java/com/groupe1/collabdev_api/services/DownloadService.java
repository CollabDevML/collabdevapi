package com.groupe1.collabdev_api.services;

import com.groupe1.collabdev_api.entities.enums.TypeFichier;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;

@Service
public class DownloadService {

    String userHome = System.getProperty("user.home");
    String desktopPath = userHome + File.separator + "Desktop";
    String uploadDirectoryPath = desktopPath + File.separator + "uploads";

    public byte[] downloadFile(String fileName, TypeFichier fileType) throws IOException {
        String filePath;
        switch (fileType) {
            case BADGE -> {
                filePath = uploadDirectoryPath + File.separator + "BADGEs" + File.separator + fileName + ".pdf";
            }
            case CDC -> {
                filePath = uploadDirectoryPath + File.separator + "CDCs" + File.separator + fileName + ".pdf";
            }
            case CV -> {
                filePath = uploadDirectoryPath + File.separator + "CVs" + File.separator + fileName + ".pdf";
            }
            default -> filePath = uploadDirectoryPath + File.separator + fileName + ".pdf";
        }
        File file = new File(filePath);

        if (file.exists() && file.isFile()) {
            return Files.readAllBytes(file.toPath());
        } else {
            throw new FileNotFoundException("Fichier non trouvé : " + fileName + ".pdf");
        }
    }
}
