package com.groupe1.collabdev_api.dto.response_dto;

import com.groupe1.collabdev_api.entities.enums.Genre;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ResponseUtilisateur {
    private int idUtilisateur;
    private String prenom;
    private String nom;
    private String email;
    private Genre genre;
    private List<String> preferences;
}
