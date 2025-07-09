package com.groupe1.collabdev_api.utilities;

import com.groupe1.collabdev_api.dto.ContributionDto;
import com.groupe1.collabdev_api.entities.Contribution;

import java.util.ArrayList;
import java.util.List;

public class MappingContribution {
    public static ContributionDto ContributionToDto(Contribution contribution) {
        return new ContributionDto(
                contribution.getId(),
                contribution.isEstValide(),
                contribution.getTache().getId(),
                contribution.getTache().getDescription(),
                contribution.getContributeur().getId()
        );
    }

    public static List<ContributionDto> contributionDtoList(List<Contribution> contributionList) {
        List<ContributionDto> contributionDtos = new ArrayList<>();
        for (Contribution contribution : contributionList) {
            contributionDtos.add(ContributionToDto(contribution));
        }
        return contributionDtos;
    }
}
