package com.groupe1.collabdev_api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter

public class ContributionDto {
    private int id;
    private Boolean estValide;

    public ContributionDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getEstValide() {
        return estValide;
    }

    public void setEstValide(Boolean estValide) {
        this.estValide = estValide;
    }

    public int getIdTache() {
        return idTache;
    }

    public void setIdTache(int idTache) {
        this.idTache = idTache;
    }

    public String getDescriptionTache() {
        return descriptionTache;
    }

    public void setDescriptionTache(String descriptionTache) {
        this.descriptionTache = descriptionTache;
    }

    public int getIdContributeur() {
        return idContributeur;
    }

    public void setIdContributeur(int idContributeur) {
        this.idContributeur = idContributeur;
    }

    public ContributionDto(int id, Boolean estValide, int idTache, String descriptionTache, int idContributeur) {
        this.id = id;
        this.estValide = estValide;
        this.idTache = idTache;
        this.descriptionTache = descriptionTache;
        this.idContributeur = idContributeur;
    }

    private int idTache;
    private String descriptionTache;
    private int idContributeur;
}
