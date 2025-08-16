package com.groupe1.collabdev_api.dto.response_dto;

import com.groupe1.collabdev_api.entities.enums.Niveau;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter

public class ResponseProjet {
    public ResponseProjet() {
    }

    private String titre;
    private String description;

    public ResponseProjet(String titre, String description, boolean estFini, LocalDate dateDebut, LocalDate dateFin, Niveau niveauDAcces, boolean etat, ResponseUserNames gestionnaire, int piecesDAcces, ResponseUserNames porteur, List<ResponseCommentaireProjet> commentaires, int nombreContributeurs) {
        this.titre = titre;
        this.description = description;
        this.estFini = estFini;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.niveauDAcces = niveauDAcces;
        this.etat = etat;
        this.gestionnaire = gestionnaire;
        this.piecesDAcces = piecesDAcces;
        this.porteur = porteur;
        this.commentaires = commentaires;
        this.nombreContributeurs = nombreContributeurs;
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

    public ResponseUserNames getGestionnaire() {
        return gestionnaire;
    }

    public void setGestionnaire(ResponseUserNames gestionnaire) {
        this.gestionnaire = gestionnaire;
    }

    public int getPiecesDAcces() {
        return piecesDAcces;
    }

    public void setPiecesDAcces(int piecesDAcces) {
        this.piecesDAcces = piecesDAcces;
    }

    public ResponseUserNames getPorteur() {
        return porteur;
    }

    public void setPorteur(ResponseUserNames porteur) {
        this.porteur = porteur;
    }

    public List<ResponseCommentaireProjet> getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(List<ResponseCommentaireProjet> commentaires) {
        this.commentaires = commentaires;
    }

    public int getNombreContributeurs() {
        return nombreContributeurs;
    }

    public void setNombreContributeurs(int nombreContributeurs) {
        this.nombreContributeurs = nombreContributeurs;
    }

    private boolean estFini;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private Niveau niveauDAcces;
    private boolean etat;
    private ResponseUserNames gestionnaire;
    private int piecesDAcces;
    private ResponseUserNames porteur;
    private List<ResponseCommentaireProjet> commentaires;
    private int nombreContributeurs;
}
