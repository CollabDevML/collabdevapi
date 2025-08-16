package com.groupe1.collabdev_api.dto.response_dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter

public class ResponseCommentaireProjet {
    private int id;
    private String contenu;

    public ResponseCommentaireProjet() {
    }

    public ResponseCommentaireProjet(int id, String contenu, LocalDate dateCommentaire, ResponseUserNames utilisateur) {
        this.id = id;
        this.contenu = contenu;
        this.dateCommentaire = dateCommentaire;
        this.utilisateur = utilisateur;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public LocalDate getDateCommentaire() {
        return dateCommentaire;
    }

    public void setDateCommentaire(LocalDate dateCommentaire) {
        this.dateCommentaire = dateCommentaire;
    }

    public ResponseUserNames getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(ResponseUserNames utilisateur) {
        this.utilisateur = utilisateur;
    }

    private LocalDate dateCommentaire;
    private ResponseUserNames utilisateur;
}