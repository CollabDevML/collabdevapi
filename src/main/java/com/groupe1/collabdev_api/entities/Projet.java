package com.groupe1.collabdev_api.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.groupe1.collabdev_api.dto.ProjetDto;
import com.groupe1.collabdev_api.dto.response_dto.ResponseCommentaireProjet;
import com.groupe1.collabdev_api.dto.response_dto.ResponseProjet;
import com.groupe1.collabdev_api.dto.response_dto.ResponseUser;
import com.groupe1.collabdev_api.entities.enums.Niveau;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
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
    private LocalDateTime dateDebut;

    @Column(nullable = false)
    private LocalDateTime dateFin;

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

    public ResponseProjet toResponse() {
        int nombreContributeurs = 0;
        for (DemandeContribution demandeContribution : demandeContributions) {
            if (demandeContribution.isEstAcceptee()) {
                nombreContributeurs++;
            }
        }
        List<ResponseCommentaireProjet> commentaireProjets = new ArrayList<>();
        for (CommentaireProjet commentaireProjet : this.commentairesProjet) {
            commentaireProjets.add(commentaireProjet.toResponse());
        }
        return new ResponseProjet(
                this.id,
                titre,
                description,
                isEstFini(),
                getDateDebut(),
                getDateFin(),
                getNiveauDAcces(),
                isEtat(),
                new ResponseUser(
                        gestionnaire.getUtilisateur().getPrenom(),
                        gestionnaire.getUtilisateur().getNom(),
                        gestionnaire.getUtilisateur().getRole()
                ),
                piecesDAcces,
                new ResponseUser(
                        ideeProjet.getUtilisateur().getPrenom(),
                        ideeProjet.getUtilisateur().getNom(),
                        ideeProjet.getUtilisateur().getRole()
                ),
                commentaireProjets,
                nombreContributeurs
        );
    }

}
