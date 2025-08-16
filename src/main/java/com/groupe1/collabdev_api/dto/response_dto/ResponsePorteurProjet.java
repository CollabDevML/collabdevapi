package com.groupe1.collabdev_api.dto.response_dto;

import com.groupe1.collabdev_api.entities.enums.Genre;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResponsePorteurProjet extends ResponseUtilisateur {
    private int idPorteurProjet;

    public int getIdPorteurProjet() {
        return idPorteurProjet;
    }

    public void setIdPorteurProjet(int idPorteurProjet) {
        this.idPorteurProjet = idPorteurProjet;
    }

    public ResponsePorteurProjet(int id, String prenom, String nom, String email, Genre genre, List<String> preferences, int idPorteurProjet) {
        super(id, prenom, nom, email, genre, preferences);
        this.idPorteurProjet = idPorteurProjet;
    }
}
