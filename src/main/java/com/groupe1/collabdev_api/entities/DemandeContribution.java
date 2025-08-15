package com.groupe1.collabdev_api.entities;

import com.groupe1.collabdev_api.dto.ContributeurDto;
import com.groupe1.collabdev_api.dto.DemandeContributionDto;
import com.groupe1.collabdev_api.entities.enums.Type;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "demandes_contribution")
public class DemandeContribution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private boolean estAcceptee = false;

    @Column(nullable = false)
    private LocalDateTime dateEnvoi = LocalDateTime.now();

    @Column(nullable = false)
    private Type profileContributeur;

    @ManyToOne
    @JoinColumn(name = "id_contributeur", nullable = false)
    private Contributeur contributeur;

    @ManyToOne
    @JoinColumn(name = "id_projet", nullable = false)
    private Projet projet;

    public DemandeContributionDto toDto() {
        return new DemandeContributionDto(
                this.id,
                this.estAcceptee,
                this.dateEnvoi,
                this.profileContributeur,
                getContributeur().getUtilisateur().getNom(),
                getContributeur().getUtilisateur().getPrenom(),
                getProjet().getTitre()
        );
    }

}
