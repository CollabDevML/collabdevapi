package com.groupe1.collabdev_api.dto.response_dto;

import com.groupe1.collabdev_api.entities.enums.DomaineIdeeProjet;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ResponseIdeeProjet {
    private int id;
    private String titre;
    private String description;
    private List<DomaineIdeeProjet> domaine;
    private String uriCDC;
    private int nombreSoutien;
    private LocalDate datePublication;
    private int idUtilisateur;
}
