package com.groupe1.collabdev_api.entities;

import com.groupe1.collabdev_api.dto.DemandeContributionDto;
import com.groupe1.collabdev_api.entities.enums.Type;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

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

    public DemandeContribution(int id, boolean estAcceptee, LocalDate dateEnvoi, Type profileContributeur, Contributeur contributeur, Projet projet) {
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

    public LocalDate getDateEnvoi() {
        return dateEnvoi;
    }

    public void setDateEnvoi(LocalDate dateEnvoi) {
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
    private LocalDate dateEnvoi = LocalDate.now();

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
                this.profileContributeur
        );
    }

}
