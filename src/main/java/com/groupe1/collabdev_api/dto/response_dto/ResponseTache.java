package com.groupe1.collabdev_api.dto.response_dto;

import com.groupe1.collabdev_api.entities.enums.NiveauTache;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class ResponseTache {
    private int id;
    private String titre;
    private String description;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private int piecesAGagner;
    private NiveauTache niveau;
}
