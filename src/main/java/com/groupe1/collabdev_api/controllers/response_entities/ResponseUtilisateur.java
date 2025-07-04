package com.groupe1.collabdev_api.controllers.response_entities;

import com.groupe1.collabdev_api.entities.enums.Genre;
import com.groupe1.collabdev_api.entities.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public abstract class ResponseUtilisateur {
    private int id;
    private String prenom;
    private String nom;
    private String email;
    private String motDePasse;
    private Genre genre;
}
