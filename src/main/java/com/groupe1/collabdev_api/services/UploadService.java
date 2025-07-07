package com.groupe1.collabdev_api.services;

import com.groupe1.collabdev_api.entities.enums.TypeFichier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;

@Service
public class UploadService {
    String userHome = System.getProperty("user.home");
    String desktopPath = userHome + File.separator + "Desktop";

    public String uploadFile(MultipartFile file, String fileName, TypeFichier fileType) {
        boolean isCreatedDirectory;
        switch (fileType) {
            case BADGE -> {
                File directory = new File(desktopPath + "/uploads/BADGEs");
                if (!directory.exists()) {
                    isCreatedDirectory = directory.mkdirs();
                } else {
                    isCreatedDirectory = true;
                }
            }
            case CDC -> {
                File directory = new File(desktopPath + "/uploads/CDCs");
                if (!directory.exists()) {
                    isCreatedDirectory = directory.mkdirs();
                } else {
                    isCreatedDirectory = true;
                }
            }
            case CV -> {
                File directory = new File(desktopPath + "/uploads/CVs");
                if (!directory.exists()) {
                    isCreatedDirectory = directory.mkdirs();
                } else {
                    isCreatedDirectory = true;
                }
            }
            default -> isCreatedDirectory = false;
        }
        if (isCreatedDirectory) {
            String filePath = getFilePath(fileName, fileType);
            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                fos.write(file.getBytes());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return filePath;
        } else {
            return null;
        }
    }

    private String getFilePath(String fileName, TypeFichier fileType) {
        String filePath;
        switch (fileType) {
            case BADGE -> {
                filePath = desktopPath + File.separator + "uploads" + File.separator + "BADGEs" + File.separator + fileName + ".pdf";
            }
            case CDC -> {
                filePath = desktopPath + File.separator + "uploads" + File.separator + "CDCs" + File.separator + fileName + ".pdf";
            }
            case CV -> {
                filePath = desktopPath + File.separator + "uploads" + File.separator + "CVs" + File.separator + fileName + ".pdf";
            }
            default -> filePath = desktopPath + File.separator + "uploads" + File.separator + fileName + ".pdf";
        }
        return filePath;
    }
}
