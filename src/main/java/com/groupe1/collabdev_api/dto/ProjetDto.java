package com.groupe1.collabdev_api.dto;

import com.groupe1.collabdev_api.entities.enums.Niveau;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ProjetDto {
    private String titre;
    private String description;
    private boolean estFini;
    private LocalDate dateDebut;
    private LocalDate dateFin;

    public ProjetDto() {
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

    public boolean isEstFini() {
        return estFini;
    }

    public void setEstFini(boolean estFini) {
        this.estFini = estFini;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public Niveau getNiveauDAcces() {
        return niveauDAcces;
    }

    public void setNiveauDAcces(Niveau niveauDAcces) {
        this.niveauDAcces = niveauDAcces;
    }

    public boolean isEtat() {
        return etat;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
    }

    public int getIdGestionnaire() {
        return idGestionnaire;
    }

    public void setIdGestionnaire(int idGestionnaire) {
        this.idGestionnaire = idGestionnaire;
    }

    public int getPiecesDAcces() {
        return piecesDAcces;
    }

    public void setPiecesDAcces(int piecesDAcces) {
        this.piecesDAcces = piecesDAcces;
    }

    public int getIdIdeeProjet() {
        return idIdeeProjet;
    }

    public void setIdIdeeProjet(int idIdeeProjet) {
        this.idIdeeProjet = idIdeeProjet;
    }

    public ProjetDto(String titre, String description, boolean estFini, LocalDate dateDebut, LocalDate dateFin, Niveau niveauDAcces, boolean etat, int idGestionnaire, int piecesDAcces, int idIdeeProjet) {
        this.titre = titre;
        this.description = description;
        this.estFini = estFini;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.niveauDAcces = niveauDAcces;
        this.etat = etat;
        this.idGestionnaire = idGestionnaire;
        this.piecesDAcces = piecesDAcces;
        this.idIdeeProjet = idIdeeProjet;
    }

    private Niveau niveauDAcces;
    private boolean etat;
    private int idGestionnaire;
    private int piecesDAcces;
    private int idIdeeProjet;
}
