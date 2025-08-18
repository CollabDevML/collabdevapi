package com.groupe1.collabdev_api.dto.response_dto;

import com.groupe1.collabdev_api.entities.enums.Niveau;
import com.groupe1.collabdev_api.entities.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseContributeurDemande {
    private int idUtilisateur;
    private String prenom;
    private String nom;
    private Niveau niveau;
    private Type typeProfile;
}
