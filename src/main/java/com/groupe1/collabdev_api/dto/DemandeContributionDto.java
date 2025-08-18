package com.groupe1.collabdev_api.dto;

import com.groupe1.collabdev_api.entities.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class DemandeContributionDto {
    private int id;
    private Boolean estAccepte;
    private LocalDateTime dateEnvoi;
    private Type profileContributeur;
    private String nom_projet;
    private String nom_contributeur;
    private String prenom_contributeur;
    private int contributeur_id;

    public DemandeContributionDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public DemandeContributionDto(int id, Boolean estAccepte, LocalDateTime dateEnvoi, Type profileContributeur
            , String nom_projet, String nom_contributeur, String prenom_contributeur,
                                  int contributeur_id) {
        this.id = id;
        this.estAccepte = estAccepte;
        this.dateEnvoi = dateEnvoi;
        this.profileContributeur = profileContributeur;
        this.nom_projet = nom_projet;
        this.prenom_contributeur = prenom_contributeur;
        this.nom_contributeur = nom_contributeur;
        this.contributeur_id = contributeur_id;
    }

    public int getContributeur_id() {
        return contributeur_id;
    }

    public void setContributeur_id(int contributeur_id) {
        this.contributeur_id = contributeur_id;
    }

    public Boolean getEstAccepte() {
        return estAccepte;
    }

    public void setEstAccepte(Boolean estAccepte) {
        this.estAccepte = estAccepte;
    }

    public LocalDateTime getDateEnvoi() {
        return dateEnvoi;
    }

    public void setDateEnvoi(LocalDateTime dateEnvoi) {
        this.dateEnvoi = dateEnvoi;
    }

    public Type getProfileContributeur() {
        return profileContributeur;
    }

    public void setProfileContributeur(Type profileContributeur) {
        this.profileContributeur = profileContributeur;
    }
}
