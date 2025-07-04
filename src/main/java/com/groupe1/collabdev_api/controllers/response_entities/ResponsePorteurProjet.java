package com.groupe1.collabdev_api.controllers.response_entities;

import com.groupe1.collabdev_api.entities.enums.Genre;
import com.groupe1.collabdev_api.entities.enums.Role;

public class ResponsePorteurProjet extends ResponseUtilisateur{
    public ResponsePorteurProjet(int id, String prenom, String nom, String email, String motDePasse, Genre genre) {
        super(id, prenom, nom, email, motDePasse, genre);
    }
}
