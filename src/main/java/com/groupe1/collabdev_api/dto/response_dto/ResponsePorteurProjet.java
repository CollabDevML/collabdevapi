package com.groupe1.collabdev_api.dto.response_dto;

import com.groupe1.collabdev_api.entities.enums.Genre;

public class ResponsePorteurProjet extends ResponseUtilisateur{
    public ResponsePorteurProjet(int id, String prenom, String nom, String email, String motDePasse, Genre genre) {
        super(id, prenom, nom, email, motDePasse, genre);
    }
}
