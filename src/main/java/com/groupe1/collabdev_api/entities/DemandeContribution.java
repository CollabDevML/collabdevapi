package com.groupe1.collabdev_api.entities;

import com.groupe1.collabdev_api.dto.DemandeContributionDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "demandes_contribution")
public class DemandeContribution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private boolean estAcceptee = false;

    @Column(nullable = false)
    private LocalDate dateEnvoi = LocalDate.now();

    @ManyToOne
    @JoinColumn(name = "id_contributeur", nullable = false)
    private Contributeur contributeur;

    @ManyToOne
    @JoinColumn(name = "id_projet", nullable = false)
    private Projet projet;

    public DemandeContributionDto toDto(){
        return new DemandeContributionDto(
                this.id,
                this.estAcceptee,
                this.dateEnvoi
        );
    }

}
