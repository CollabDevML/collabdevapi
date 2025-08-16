package com.groupe1.collabdev_api.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.groupe1.collabdev_api.dto.response_dto.ResponseTache;
import com.groupe1.collabdev_api.entities.enums.NiveauTache;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@Entity
@Table(name = "taches")
public class Tache {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String titre;

    public Tache() {
    }

    @Column(nullable = false)
    private String description;

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

    public int getPieceAGagner() {
        return pieceAGagner;
    }

    public void setPieceAGagner(int pieceAGagner) {
        this.pieceAGagner = pieceAGagner;
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

    public boolean isEstFini() {
        return estFini;
    }

    public void setEstFini(boolean estFini) {
        this.estFini = estFini;
    }

    public NiveauTache getNiveau() {
        return niveau;
    }

    public void setNiveau(NiveauTache niveau) {
        this.niveau = niveau;
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
    private int pieceAGagner;

    public Tache(int id, String titre, String description, int pieceAGagner, LocalDate dateDebut, LocalDate dateFin, boolean estFini, NiveauTache niveau, Contributeur contributeur, Projet projet) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.pieceAGagner = pieceAGagner;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.estFini = estFini;
        this.niveau = niveau;
        this.contributeur = contributeur;
        this.projet = projet;
    }

    @Column(nullable = false)
    private LocalDate dateDebut;

    @Column(nullable = false)
    private LocalDate dateFin;

    @Column(nullable = false)
    private boolean estFini = false;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private NiveauTache niveau;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_contributeur")
    private Contributeur contributeur;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "id_projet", nullable = false)
    private Projet projet;

    public ResponseTache toResponse() {
        return new ResponseTache(
                this.id,
                this.titre,
                this.description,
                this.dateDebut,
                this.dateFin,
                this.pieceAGagner,
                this.niveau
        );
    }
}
