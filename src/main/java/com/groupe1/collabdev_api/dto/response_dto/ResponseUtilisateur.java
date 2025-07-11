package com.groupe1.collabdev_api.dto.response_dto;

import com.groupe1.collabdev_api.entities.enums.Genre;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public abstract class ResponseUtilisateur {
    private int idUtilisateur;
    private String prenom;
    private String nom;
    private String email;
    private String motDePasse;
    private Genre genre;
}
