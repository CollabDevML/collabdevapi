package com.groupe1.collabdev_api.dto.response_dto;

import com.groupe1.collabdev_api.entities.enums.Genre;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResponseGestionnaire extends ResponseUtilisateur {
    private String uriCv;
    private boolean estValide;
    private int idGestionnaire;

    public ResponseGestionnaire(int id, String prenom, String nom, String email, Genre genre, List<String> preferences, String uriCv, boolean estValide, int idGestionnaire) {
        super(id, prenom, nom, email, genre, preferences);
        this.uriCv = uriCv;
        this.estValide = estValide;
        this.idGestionnaire = idGestionnaire;
    }
}
