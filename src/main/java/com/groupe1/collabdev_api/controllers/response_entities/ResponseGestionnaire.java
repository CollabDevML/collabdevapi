package com.groupe1.collabdev_api.controllers.response_entities;

import com.groupe1.collabdev_api.entities.enums.Genre;
import com.groupe1.collabdev_api.entities.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseGestionnaire extends ResponseUtilisateur {
    private String uriCv;

    public ResponseGestionnaire(int id, String prenom, String nom, String email, String motDePasse, Genre genre, String uriCv) {
        super(id, prenom, nom, email, motDePasse, genre);
        this.uriCv = uriCv;
    }
}
