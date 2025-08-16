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

    public String getUriCv() {
        return uriCv;
    }

    public void setUriCv(String uriCv) {
        this.uriCv = uriCv;
    }

    public boolean isEstValide() {
        return estValide;
    }

    public void setEstValide(boolean estValide) {
        this.estValide = estValide;
    }

    public int getIdGestionnaire() {
        return idGestionnaire;
    }

    public void setIdGestionnaire(int idGestionnaire) {
        this.idGestionnaire = idGestionnaire;
    }

    public ResponseGestionnaire(int id, String prenom, String nom, String email, Genre genre, List<String> preferences, String uriCv, boolean estValide, int idGestionnaire) {
        super(id, prenom, nom, email, genre, preferences);
        this.uriCv = uriCv;
        this.estValide = estValide;
        this.idGestionnaire = idGestionnaire;
    }
}
