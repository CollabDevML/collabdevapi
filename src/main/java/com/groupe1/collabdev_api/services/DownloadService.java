package com.groupe1.collabdev_api.services;

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

    public byte[] downloadFile(String fileName) throws IOException {
        String filePath = uploadDirectoryPath + File.separator + fileName + ".pdf";
        File file = new File(filePath);

        if (file.exists() && file.isFile()) {
            return Files.readAllBytes(file.toPath());
        } else {
            throw new FileNotFoundException("Fichier non trouvé : " + fileName + ".pdf");
        }
    }
}
