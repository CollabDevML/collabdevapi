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
    private String nom_projet;
    private String nom_contributeur;
    private String prenom_contributeur;

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

    public String getNom_projet() {
        return nom_projet;
    }

    public void setNom_projet(String nom_projet) {
        this.nom_projet = nom_projet;
    }

    public String getNom_contributeur() {
        return nom_contributeur;
    }

    public void setNom_contributeur(String nom_contributeur) {
        this.nom_contributeur = nom_contributeur;
    }

    public String getPrenom_contributeur() {
        return prenom_contributeur;
    }

    public void setPrenom_contributeur(String prenom_contributeur) {
        this.prenom_contributeur = prenom_contributeur;
    }

    public ContributionDto(int id, Boolean estValide,
                           int idTache, String descriptionTache,
                           int idContributeur,
                           String nom_projet, String nom_contributeur,String prenom_contributeur) {
        this.id = id;
        this.estValide = estValide;
        this.idTache = idTache;
        this.descriptionTache = descriptionTache;
        this.idContributeur = idContributeur;
        this.nom_projet = nom_projet;
        this.nom_contributeur = nom_contributeur;
        this.prenom_contributeur = prenom_contributeur;
    }

    private int idTache;
    private String descriptionTache;
    private int idContributeur;
}
