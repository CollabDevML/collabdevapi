package com.groupe1.collabdev_api.dto.response_dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseCommentaireIdeeProjet {
    private int id;
    private String contenu;
    private LocalDateTime dateCommentaire;
    private ResponseUser utilisateur;
}
