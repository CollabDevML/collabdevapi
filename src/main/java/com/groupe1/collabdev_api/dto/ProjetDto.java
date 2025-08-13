package com.groupe1.collabdev_api.dto;

import com.groupe1.collabdev_api.entities.enums.Niveau;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class ProjetDto {
    private String titre;
    private String description;
    private boolean estFini;
    private Date dateDebut;
    private Date dateFin;
    private Niveau niveauDAcces;
    private boolean etat;
    private int idGestionnaire;
    private int piecesDAcces;
    private int idIdeeProjet;
}
