package com.groupe1.collabdev_api.utilities;

import com.groupe1.collabdev_api.dto.ContributeurDto;
import com.groupe1.collabdev_api.entities.Contributeur;
import com.groupe1.collabdev_api.entities.Utilisateur;

public class MappingContributeur {
    public static ContributeurDto contributeurToDto(Contributeur contributeur)
    {
        Utilisateur utilisateur = contributeur.getUtilisateur();
        return  new ContributeurDto(
                utilisateur.getPrenom(),
                utilisateur.getNom(),
                utilisateur.getEmail(),
                utilisateur.getMotDePasse(),
                utilisateur.getGenre(),
                contributeur.getNiveau(),
                contributeur.getSpecialite(),
                contributeur.getType(),
                contributeur.getPieces(),
               contributeur.getUriCv()
        );
    }



}
