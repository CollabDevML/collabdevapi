package com.groupe1.collabdev_api.entities;

import com.groupe1.collabdev_api.dto.ContributeurDto;
import com.groupe1.collabdev_api.dto.DemandeContributionDto;
import com.groupe1.collabdev_api.dto.response_dto.ResponseContributeurDemande;
import com.groupe1.collabdev_api.dto.response_dto.ResponseDemandeContribution;
import com.groupe1.collabdev_api.dto.response_dto.ResponseProjetDemande;
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

    public ResponseDemandeContribution toResponse() {
        return new ResponseDemandeContribution(
                this.id,
                this.dateEnvoi,
                new ResponseContributeurDemande(
                        this.contributeur.getUtilisateur().getId(),
                        this.contributeur.getUtilisateur().getPrenom(),
                        this.contributeur.getUtilisateur().getNom(),
                        this.contributeur.getNiveau(),
                        this.profileContributeur
                ),
                new ResponseProjetDemande(
                        this.projet.getId(),
                        this.projet.getTitre(),
                        this.projet.getDescription()
                ),
                this.estAcceptee
        );
    }

}
