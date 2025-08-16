package com.groupe1.collabdev_api.entities;

import com.groupe1.collabdev_api.entities.enums.Niveau;
import com.groupe1.collabdev_api.entities.enums.Type;
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
@Table(name = "contributeurs")
public class Contributeur {
    public Contributeur() {
    }

    public Contributeur(int id, Utilisateur utilisateur, Niveau niveau, String specialite, Type type, double pieces, String uriCv, List<Tache> taches, List<Contribution> contributions, List<DemandeContribution> demandeContributions, List<ObtentionBadge> obtentionBadges) {
        this.id = id;
        this.utilisateur = utilisateur;
        this.niveau = niveau;
        this.specialite = specialite;
        this.type = type;
        this.pieces = pieces;
        this.uriCv = uriCv;
        this.taches = taches;
        this.contributions = contributions;
        this.demandeContributions = demandeContributions;
        this.obtentionBadges = obtentionBadges;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Niveau getNiveau() {
        return niveau;
    }

    public void setNiveau(Niveau niveau) {
        this.niveau = niveau;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public double getPieces() {
        return pieces;
    }

    public void setPieces(double pieces) {
        this.pieces = pieces;
    }

    public String getUriCv() {
        return uriCv;
    }

    public void setUriCv(String uriCv) {
        this.uriCv = uriCv;
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

    public List<ObtentionBadge> getObtentionBadges() {
        return obtentionBadges;
    }

    public void setObtentionBadges(List<ObtentionBadge> obtentionBadges) {
        this.obtentionBadges = obtentionBadges;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "id_utilisateur", nullable = false)
    private Utilisateur utilisateur;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Niveau niveau;

    @Column(nullable = false)
    private String specialite;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Type type;

    @Column(nullable = false)
    private double pieces;

    @Column(nullable = false)
    private String uriCv;

    @OneToMany(cascade = CascadeType.REMOVE)
    private List<Tache> taches;

    @OneToMany(mappedBy = "contributeur")
    private List<Contribution> contributions = new ArrayList<>();

    @OneToMany(mappedBy = "contributeur")
    private List<DemandeContribution> demandeContributions = new ArrayList<>();

    @OneToMany(mappedBy = "contributeur")
    private List<ObtentionBadge> obtentionBadges = new ArrayList<>();

}
