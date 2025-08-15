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
    private String description;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private int piecesAGagner;
    private int idContributeur = 0;
    private NiveauTache niveau;
}
