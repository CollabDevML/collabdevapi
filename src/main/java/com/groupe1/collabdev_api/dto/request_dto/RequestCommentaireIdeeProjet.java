package com.groupe1.collabdev_api.dto.request_dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter

public class RequestCommentaireIdeeProjet {
    private String contenu;

    private LocalDateTime dateCommentaire;

    public RequestCommentaireIdeeProjet(String contenu, LocalDateTime dateCommentaire) {
        this.contenu = contenu;
        this.dateCommentaire = dateCommentaire;
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
}
