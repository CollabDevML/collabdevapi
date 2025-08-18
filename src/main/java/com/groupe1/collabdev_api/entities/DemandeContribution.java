package com.groupe1.collabdev_api.entities;

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

@Table(name = "demandes_contribution")
public class DemandeContribution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public DemandeContribution() {
    }

    public DemandeContribution(int id, boolean estAcceptee, LocalDateTime dateEnvoi, Type profileContributeur, Contributeur contributeur, Projet projet) {
        this.id = id;
        this.estAcceptee = estAcceptee;
        this.dateEnvoi = dateEnvoi;
        this.profileContributeur = profileContributeur;
        this.contributeur = contributeur;
        this.projet = projet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isEstAcceptee() {
        return estAcceptee;
    }

    public void setEstAcceptee(boolean estAcceptee) {
        this.estAcceptee = estAcceptee;
    }

    public LocalDateTime getDateEnvoi() {
        return dateEnvoi;
    }

    public void setDateEnvoi(LocalDateTime dateEnvoi) {
        this.dateEnvoi = dateEnvoi;
    }

    public Type getProfileContributeur() {
        return profileContributeur;
    }

    public void setProfileContributeur(Type profileContributeur) {
        this.profileContributeur = profileContributeur;
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
                this.projet.getTitre(),
                this.contributeur.getUtilisateur().getPrenom(),
                this.contributeur.getUtilisateur().getNom(),
                this.contributeur.getId()
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
