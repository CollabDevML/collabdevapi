package com.groupe1.collabdev_api.dto;

import com.groupe1.collabdev_api.entities.Utilisateur;
import com.groupe1.collabdev_api.entities.enums.Genre;
import com.groupe1.collabdev_api.entities.enums.Niveau;
import com.groupe1.collabdev_api.entities.enums.Type;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContributeurDto {
    private String nom;
    private String prenom;
    private String email;
    private String motDePasse;
    private Genre genre;
    private Niveau niveau;
    private String specialite;
    private Type type;
    private double pieces;
    private String uriCv;


}
