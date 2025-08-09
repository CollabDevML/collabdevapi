package com.groupe1.collabdev_api.dto.request_dto;

import com.groupe1.collabdev_api.entities.enums.Genre;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public abstract class RequestUtilisateur {
    private String prenom;
    private String nom;
    private String email;
    private String motDePasse;
    private Genre genre;
    private List<String> preferences;
}
