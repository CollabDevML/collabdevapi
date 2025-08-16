package com.groupe1.collabdev_api.dto.request_dto;

import com.groupe1.collabdev_api.entities.enums.NiveauTache;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class RequestTache {
    private int idProjet;
    private int idGestionnaire;
    private String titre;

    public int getIdProjet() {
        return idProjet;
    }

    public void setIdProjet(int idProjet) {
        this.idProjet = idProjet;
    }

    public int getIdGestionnaire() {
        return idGestionnaire;
    }

    public void setIdGestionnaire(int idGestionnaire) {
        this.idGestionnaire = idGestionnaire;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDateTime dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDateTime getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDateTime dateFin) {
        this.dateFin = dateFin;
    }

    public int getPiecesAGagner() {
        return piecesAGagner;
    }

    public void setPiecesAGagner(int piecesAGagner) {
        this.piecesAGagner = piecesAGagner;
    }

    public int getIdContributeur() {
        return idContributeur;
    }

    public void setIdContributeur(int idContributeur) {
        this.idContributeur = idContributeur;
    }

    public NiveauTache getNiveau() {
        return niveau;
    }

    public void setNiveau(NiveauTache niveau) {
        this.niveau = niveau;
    }

    private String description;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private int piecesAGagner;
    private int idContributeur = 0;
    private NiveauTache niveau;
}
