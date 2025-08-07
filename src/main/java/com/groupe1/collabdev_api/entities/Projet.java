package com.groupe1.collabdev_api.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.groupe1.collabdev_api.dto.ProjetDto;
import com.groupe1.collabdev_api.entities.enums.Niveau;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "projets")
@Entity
public class Projet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String titre;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private boolean estFini = false;

    @Column(nullable = false)
    private LocalDate dateDebut;

    @Column(nullable = false)
    private LocalDate dateFin;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Niveau niveauDAcces;

    @Column(nullable = false)
    private boolean etat = true;

    @Column(nullable = false)
    private int piecesDAcces;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_idee_projet", nullable = false)
    private IdeeProjet ideeProjet;

    @ManyToOne
    @JoinColumn(name = "id_gestionnaire", nullable = false)
    private Gestionnaire gestionnaire;

    @OneToMany(mappedBy = "projet", cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<Tache> taches;

    @OneToMany(mappedBy = "projet")
    private List<Contribution> contributions = new ArrayList<>();

    @OneToMany(mappedBy = "projet")
    private List<DemandeContribution> demandeContributions = new ArrayList<>();

    @OneToMany(mappedBy = "projet")
    private List<CommentaireProjet> commentairesProjet = new ArrayList<>();

    @OneToMany(mappedBy = "projet")
    private List<GestionAdminProjet> gestionsAdminProjet = new ArrayList<>();

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Projet projet)) return false;
        return id == projet.getId();
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }

    public ProjetDto toDto() {
        return new ProjetDto(
                this.titre,
                this.description,
                this.isEstFini(),
                this.getDateDebut(),
                this.getDateFin(),
                this.getNiveauDAcces(),
                this.isEtat(),
                this.getGestionnaire().getId(),
                this.piecesDAcces,
                this.ideeProjet.getId()
        );
    }
}
