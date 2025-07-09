package com.groupe1.collabdev_api.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class UploadFichier {

    static String userHome = System.getProperty("user.home");
    static String desktopPath = userHome + File.separator + "Desktop";

    public static String upload(MultipartFile fichierTrans, String chemin) throws IOException {
        String dossierReception = desktopPath + File.separator + "uploads" + File.separator + chemin + File.separator;

        // Création du dossier de réception si nécessaire
        File dir = new File(dossierReception);
        if (!dir.exists()) dir.mkdirs();

        // Récupération du nom et de l'extension du fichier
        String nomOriginal = fichierTrans.getOriginalFilename();
        if (nomOriginal == null || !nomOriginal.contains(".")) return "";

        String extension = nomOriginal.substring(nomOriginal.lastIndexOf('.') + 1).toLowerCase();

        // Vérification de l'extension
        if (extension.equals("jpg") || extension.equals("jpeg") || extension.equals("png")) {
            File fichier = new File(dossierReception + nomOriginal);
            fichierTrans.transferTo(fichier);

            // Retourne un chemin relatif (recommandé pour la BDD)
            return "uploads/" + chemin + "/" + nomOriginal;
        }
        return "";
    }

}
