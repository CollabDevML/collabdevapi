package com.groupe1.collabdev_api.dto.response_dto;

import com.groupe1.collabdev_api.dto.request_dto.RequestIdeeProjet;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class ResponseIdeeProjet extends RequestIdeeProjet {
    private int id;
    private String titre;
    private String description;
    private String domaine;
    private String uriCDC;
    private int nombreSoutien;
    private LocalDate datePublication;
    private int idUtilisateur;
}
