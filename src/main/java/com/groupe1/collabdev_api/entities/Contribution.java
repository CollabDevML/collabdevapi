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
@Table(name = "contributions")
public class Contribution {
    public Contribution() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isEstValide() {
        return estValide;
    }

    public void setEstValide(boolean estValide) {
        this.estValide = estValide;
    }

    public Contributeur getContributeur() {
        return contributeur;
    }

    public void setContributeur(Contributeur contributeur) {
        this.contributeur = contributeur;
    }

    public Projet getProjet() {
        return projet;
    }

    public void setProjet(Projet projet) {
        this.projet = projet;
    }

    public Tache getTache() {
        return tache;
    }

    public void setTache(Tache tache) {
        this.tache = tache;
    }

    public Contribution(int id, boolean estValide, Contributeur contributeur, Projet projet, Tache tache) {
        this.id = id;
        this.estValide = estValide;
        this.contributeur = contributeur;
        this.projet = projet;
        this.tache = tache;
    }

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
