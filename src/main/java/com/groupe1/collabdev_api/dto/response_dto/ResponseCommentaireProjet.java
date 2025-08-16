package com.groupe1.collabdev_api.dto.response_dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter

public class ResponseCommentaireProjet {
    private int id;
    private String contenu;
    private LocalDateTime dateCommentaire;
    private ResponseUser utilisateur;


    public ResponseCommentaireProjet() {
    }

    public ResponseCommentaireProjet(int id, String contenu, LocalDateTime dateCommentaire, ResponseUser utilisateur) {
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

    public LocalDateTime getDateCommentaire() {
        return dateCommentaire;
    }

    public void setDateCommentaire(LocalDateTime dateCommentaire) {
        this.dateCommentaire = dateCommentaire;
    }

    public ResponseUser getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(ResponseUser utilisateur) {
        this.utilisateur = utilisateur;
    }






}