package com.groupe1.collabdev_api.utilities;

import com.groupe1.collabdev_api.dto.ProjetDto;
import com.groupe1.collabdev_api.entities.Projet;

import java.util.ArrayList;
import java.util.List;

public class MappingProjet {
    public static ProjetDto toProjetDto(Projet projet) {
        return new ProjetDto(
                projet.getTitre(),
                projet.getDescription(),
                projet.isEstFini(),
                projet.getDateDebut(),
                projet.getDateFin(),
                projet.getNiveauDAcces(),
                projet.isEtat(),
                projet.getGestionnaire().getId()
        );
    }

    public static List<ProjetDto> projetDtoList(List<Projet> projets) {
        List<ProjetDto> projetDtoList = new ArrayList<>();
        for (Projet projet : projets) {
            projetDtoList.add(toProjetDto(projet));
        }
        return projetDtoList;
    }
}
