package com.groupe1.collabdev_api.dto.response_dto;

import com.groupe1.collabdev_api.entities.enums.Niveau;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter

public class ResponseProjet {

    public ResponseProjet() {
    }


    private int id;
    private String titre;
    private String description;
    private boolean estFini;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private Niveau niveauDAcces;
    private boolean etat;
    private ResponseUser gestionnaire;
    private int piecesDAcces;
    private ResponseUser porteur;
    private List<ResponseCommentaireProjet> commentaires;
    private int nombreContributeurs;

    public ResponseProjet(int id, String titre, String description, boolean estFini, LocalDateTime dateDebut, LocalDateTime dateFin, Niveau niveauDAcces, boolean etat, ResponseUser gestionnaire, int piecesDAcces, ResponseUser porteur, List<ResponseCommentaireProjet> commentaires, int nombreContributeurs) {
        this.id = id;
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

    public ResponseUser getGestionnaire() {
        return gestionnaire;
    }

    public void setGestionnaire(ResponseUser gestionnaire) {
        this.gestionnaire = gestionnaire;
    }

    public int getPiecesDAcces() {
        return piecesDAcces;
    }

    public void setPiecesDAcces(int piecesDAcces) {
        this.piecesDAcces = piecesDAcces;
    }

    public ResponseUser getPorteur() {
        return porteur;
    }

    public void setPorteur(ResponseUser porteur) {
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


}
