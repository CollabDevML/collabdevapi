package com.groupe1.collabdev_api.entities.response_dto;

import com.groupe1.collabdev_api.entities.enums.Genre;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseGestionnaire extends ResponseUtilisateur {
    private String uriCv;
    private boolean estValide;

    public ResponseGestionnaire(int id, String prenom, String nom, String email, String motDePasse, Genre genre, String uriCv, boolean estValide) {
        super(id, prenom, nom, email, motDePasse, genre);
        this.uriCv = uriCv;
        this.estValide = estValide;
    }
}
