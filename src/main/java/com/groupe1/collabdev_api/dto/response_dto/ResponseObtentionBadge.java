package com.groupe1.collabdev_api.dto.response_dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter

public class ResponseObtentionBadge {
    private int id;
    private String nomContributeur;

    public ResponseObtentionBadge() {
    }

    public ResponseObtentionBadge(int id, String nomContributeur, String titreBadge, LocalDate dateObtention) {
        this.id = id;
        this.nomContributeur = nomContributeur;
        this.titreBadge = titreBadge;
        this.dateObtention = dateObtention;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomContributeur() {
        return nomContributeur;
    }

    public void setNomContributeur(String nomContributeur) {
        this.nomContributeur = nomContributeur;
    }

    public String getTitreBadge() {
        return titreBadge;
    }

    public void setTitreBadge(String titreBadge) {
        this.titreBadge = titreBadge;
    }

    public LocalDate getDateObtention() {
        return dateObtention;
    }

    public void setDateObtention(LocalDate dateObtention) {
        this.dateObtention = dateObtention;
    }

    private String titreBadge;
    private LocalDate dateObtention;
}
