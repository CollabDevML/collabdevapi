package com.groupe1.collabdev_api.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;

@Service
public class UploadService {
    String userHome = System.getProperty("user.home");
    String desktopPath = userHome + File.separator + "Desktop";

    public String uploadFile(MultipartFile file, String fileName) {
        File directory = new File(desktopPath + "/uploads");
        boolean isCreatedDirectory;
        if(!directory.exists()){
            isCreatedDirectory = directory.mkdirs();
        } else {
            isCreatedDirectory = true;
        }
        if (isCreatedDirectory) {
            String filePath = desktopPath + File.separator + "uploads" + File.separator + fileName + ".pdf";
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
}
