package com.groupe1.collabdev_api.dto.request_dto;

import com.groupe1.collabdev_api.entities.enums.Type;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestDemandeContribution {
    private int idContributeur;
    private int idProjet;
    private Type profileContributeur;
}
