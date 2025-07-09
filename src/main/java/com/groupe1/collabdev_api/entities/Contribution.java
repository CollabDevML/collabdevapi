package com.groupe1.collabdev_api.entities;

import com.groupe1.collabdev_api.dto.ContributionDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "contributions")
public class Contribution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private boolean estValide = false;

    @ManyToOne
    @JoinColumn(name = "id_contributeur", nullable = false)
    private Contributeur contributeur;

    @ManyToOne
    @JoinColumn(name = "id_projet", nullable = false)
    private Projet projet;

    @OneToOne
    @JoinColumn(name = "id_tache", nullable = false)
    private Tache tache;

    public static List<ContributionDto> toContributeurDtoList(
            List<Contribution> contributions
    ) {
        List<ContributionDto> contributionDtoList = new ArrayList<>();
        contributions.forEach(contribution ->
                contributionDtoList.add(
                        new ContributionDto(
                                contribution.id,
                                contribution.estValide,
                                contribution.tache.getId(),
                                contribution.tache.getDescription(),
                                contribution.getContributeur().getId()
                        )
                )
        );
        return contributionDtoList;
    }

    public ContributionDto toContributeurDto() {
        return new ContributionDto(
                this.getId(),
                this.estValide,
                this.tache.getId(),
                this.tache.getDescription(),
                this.getContributeur().getId()
        );
    }

}
