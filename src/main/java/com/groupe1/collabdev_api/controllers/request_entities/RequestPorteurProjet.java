package com.groupe1.collabdev_api.controllers.request_entities;

import com.groupe1.collabdev_api.entities.enums.Genre;
import com.groupe1.collabdev_api.entities.enums.Role;

public class RequestPorteurProjet extends RequestUtilisateur {
    public RequestPorteurProjet(String prenom, String nom, String email, String motDePasse, Genre genre) {
        super(prenom, nom, email, motDePasse, genre);
    }
}
