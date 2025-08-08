package com.groupe1.collabdev_api.dto.response_dto;

import com.groupe1.collabdev_api.entities.enums.Niveau;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ResponseProjet {
    private String titre;
    private String description;
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
