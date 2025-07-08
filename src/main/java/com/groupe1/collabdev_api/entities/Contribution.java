package com.groupe1.collabdev_api.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    public ContributionDto toContributeurDto() {
        return new ContributionDto(
                this.getId(),
                this.estValide
                );
    }

    public static List<ContributionDto> toContributeurDtoList(
            List<Contribution> contributions
    ) {
        List<ContributionDto> contributionDtoList = new ArrayList<>();
        contributions.forEach( contribution ->
                contributionDtoList.add(
                        new ContributionDto(
                                contribution.id,
                                contribution.estValide
                        )
                )
        );
        return contributionDtoList;
    }

}
