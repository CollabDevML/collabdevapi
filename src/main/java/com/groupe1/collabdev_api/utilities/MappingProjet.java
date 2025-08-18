package com.groupe1.collabdev_api.utilities;

import com.groupe1.collabdev_api.dto.ContributionDto;
import com.groupe1.collabdev_api.dto.DemandeContributionDto;
import com.groupe1.collabdev_api.dto.ProjetDto;
import com.groupe1.collabdev_api.entities.Projet;

import java.util.ArrayList;
import java.util.List;

public class MappingProjet {
    public static ProjetDto toProjetDto(Projet projet) {
        List<DemandeContributionDto> demandeContributionDtos =
                MappingDemandeContribution.ToDemandeDtoToList(projet.getDemandeContributions());

        List<ContributionDto> listContributions =
               MappingContribution.contributionDtoList(projet.getContributions());
        return new ProjetDto(
                projet.getId(),
                projet.getTitre(),
                projet.getDescription(),
                projet.isEstFini(),
                projet.getDateDebut(),
                projet.getDateFin(),
                projet.getNiveauDAcces(),
                projet.isEtat(),
                projet.getGestionnaire().getId(),
                projet.getPiecesDAcces(),
                projet.getIdeeProjet().getId(),
                demandeContributionDtos,
                listContributions
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
