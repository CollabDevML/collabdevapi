package com.groupe1.collabdev_api.utilities;

import com.groupe1.collabdev_api.dto.DemandeContributionDto;
import com.groupe1.collabdev_api.entities.DemandeContribution;

import java.util.ArrayList;
import java.util.List;

public class MappingDemandeContribution {
    public static DemandeContributionDto ToDemandeDto(DemandeContribution demandeContribution) {
        return new DemandeContributionDto(
                demandeContribution.getId(),
                demandeContribution.isEstAcceptee(),
                demandeContribution.getDateEnvoi()
        );
    }

    public static List<DemandeContributionDto> ToDemandeDtoToList(List<DemandeContribution> demandeContributions) {
        List<DemandeContributionDto> demandeContributionDtos = new ArrayList<>();
        for (DemandeContribution demandeContribution : demandeContributions) {
            demandeContributionDtos.add(MappingDemandeContribution.ToDemandeDto(demandeContribution));
        }
        return demandeContributionDtos;
    }
}
