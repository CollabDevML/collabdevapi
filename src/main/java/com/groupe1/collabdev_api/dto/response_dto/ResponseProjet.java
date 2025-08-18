package com.groupe1.collabdev_api.dto.response_dto;

import com.groupe1.collabdev_api.entities.enums.Niveau;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ResponseProjet {
    private int id;
    private String titre;
    private String description;
    private boolean estFini;
    private Date dateDebut;
    private Date dateFin;
    private Niveau niveauDAcces;
    private boolean etat;
    private ResponseUser gestionnaire;
    private int piecesDAcces;
    private ResponseUser porteur;
    private List<ResponseCommentaireProjet> commentaires;
    private int nombreContributeurs;
}
