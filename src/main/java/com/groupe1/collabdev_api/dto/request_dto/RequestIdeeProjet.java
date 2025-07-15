package com.groupe1.collabdev_api.dto.request_dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class RequestIdeeProjet {
    private String titre;
    private String description;
    private String domaine;
    private String uriCDC;
    private int nombreSoutien;
    private LocalDate datePublication;
    private int idUtilisateur;
}
