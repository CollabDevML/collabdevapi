package com.groupe1.collabdev_api.dto.request_dto;

import com.groupe1.collabdev_api.entities.enums.Genre;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class RequestPorteurProjet extends RequestUtilisateur {
    public RequestPorteurProjet(String prenom, String nom, String email, String motDePasse, Genre genre) {
        super(prenom, nom, email, motDePasse, genre);
    }
}
