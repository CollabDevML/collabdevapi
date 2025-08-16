package com.groupe1.collabdev_api.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.groupe1.collabdev_api.dto.ProjetDto;
import com.groupe1.collabdev_api.dto.response_dto.ResponseCommentaireProjet;
import com.groupe1.collabdev_api.dto.response_dto.ResponseProjet;
import com.groupe1.collabdev_api.dto.response_dto.ResponseUserNames;
import com.groupe1.collabdev_api.entities.enums.Niveau;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Setter
@Table(name = "projets")
@Entity
public class Projet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public Projet() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isEstFini() {
        return estFini;
    }

    public void setEstFini(boolean estFini) {
        this.estFini = estFini;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public Niveau getNiveauDAcces() {
        return niveauDAcces;
    }

    public void setNiveauDAcces(Niveau niveauDAcces) {
        this.niveauDAcces = niveauDAcces;
    }

    public boolean isEtat() {
        return etat;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
    }

    public int getPiecesDAcces() {
        return piecesDAcces;
    }

    public void setPiecesDAcces(int piecesDAcces) {
        this.piecesDAcces = piecesDAcces;
    }

    public IdeeProjet getIdeeProjet() {
        return ideeProjet;
    }

    public void setIdeeProjet(IdeeProjet ideeProjet) {
        this.ideeProjet = ideeProjet;
    }

    public Gestionnaire getGestionnaire() {
        return gestionnaire;
    }

    public void setGestionnaire(Gestionnaire gestionnaire) {
        this.gestionnaire = gestionnaire;
    }

    public List<Tache> getTaches() {
        return taches;
    }

    public void setTaches(List<Tache> taches) {
        this.taches = taches;
    }

    public List<Contribution> getContributions() {
        return contributions;
    }

    public void setContributions(List<Contribution> contributions) {
        this.contributions = contributions;
    }

    public List<DemandeContribution> getDemandeContributions() {
        return demandeContributions;
    }

    public void setDemandeContributions(List<DemandeContribution> demandeContributions) {
        this.demandeContributions = demandeContributions;
    }

    public List<CommentaireProjet> getCommentairesProjet() {
        return commentairesProjet;
    }

    public void setCommentairesProjet(List<CommentaireProjet> commentairesProjet) {
        this.commentairesProjet = commentairesProjet;
    }

    public List<GestionAdminProjet> getGestionsAdminProjet() {
        return gestionsAdminProjet;
    }

    public void setGestionsAdminProjet(List<GestionAdminProjet> gestionsAdminProjet) {
        this.gestionsAdminProjet = gestionsAdminProjet;
    }

    public Projet(int id, String titre, String description, boolean estFini, LocalDate dateDebut, LocalDate dateFin, Niveau niveauDAcces, boolean etat, int piecesDAcces, IdeeProjet ideeProjet, Gestionnaire gestionnaire, List<Tache> taches, List<Contribution> contributions, List<DemandeContribution> demandeContributions, List<CommentaireProjet> commentairesProjet, List<GestionAdminProjet> gestionsAdminProjet) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.estFini = estFini;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.niveauDAcces = niveauDAcces;
        this.etat = etat;
        this.piecesDAcces = piecesDAcces;
        this.ideeProjet = ideeProjet;
        this.gestionnaire = gestionnaire;
        this.taches = taches;
        this.contributions = contributions;
        this.demandeContributions = demandeContributions;
        this.commentairesProjet = commentairesProjet;
        this.gestionsAdminProjet = gestionsAdminProjet;
    }

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
                titre,
                description,
                isEstFini(),
                getDateDebut(),
                getDateFin(),
                getNiveauDAcces(),
                isEtat(),
                new ResponseUserNames(
                        gestionnaire.getUtilisateur().getPrenom(),
                        gestionnaire.getUtilisateur().getNom()
                ),
                piecesDAcces,
                new ResponseUserNames(
                        ideeProjet.getUtilisateur().getPrenom(),
                        ideeProjet.getUtilisateur().getNom()
                ),
                commentaireProjets,
                nombreContributeurs
        );
    }

}
