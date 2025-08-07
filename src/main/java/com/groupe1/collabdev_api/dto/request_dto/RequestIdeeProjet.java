package com.groupe1.collabdev_api.dto.request_dto;

import com.groupe1.collabdev_api.entities.enums.DomaineIdeeProjet;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class RequestIdeeProjet {
    private String titre;
    private String description;
    private List<DomaineIdeeProjet> domaine;
    private String uriCDC;
}
