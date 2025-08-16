package com.groupe1.collabdev_api.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.groupe1.collabdev_api.dto.response_dto.ResponsePorteurProjet;
import com.groupe1.collabdev_api.dto.response_dto.ResponseUtilisateur;
import com.groupe1.collabdev_api.entities.enums.Genre;
import com.groupe1.collabdev_api.entities.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "utilisateurs")
public class Utilisateur {
    public Utilisateur() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isEtat() {
        return etat;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
    }

    public List<String> getPreferences() {
        return preferences;
    }

    public void setPreferences(List<String> preferences) {
        this.preferences = preferences;
    }

    public List<CommentaireProjet> getCommentairesProjet() {
        return commentairesProjet;
    }

    public void setCommentairesProjet(List<CommentaireProjet> commentairesProjet) {
        this.commentairesProjet = commentairesProjet;
    }

    public List<CommentaireIdeeProjet> getCommentairesIdeeProjet() {
        return commentairesIdeeProjet;
    }

    public void setCommentairesIdeeProjet(List<CommentaireIdeeProjet> commentairesIdeeProjet) {
        this.commentairesIdeeProjet = commentairesIdeeProjet;
    }

    public List<GestionAdminUtilisateur> getGestionsAdminUtilisateur() {
        return gestionsAdminUtilisateur;
    }

    public void setGestionsAdminUtilisateur(List<GestionAdminUtilisateur> gestionsAdminUtilisateur) {
        this.gestionsAdminUtilisateur = gestionsAdminUtilisateur;
    }

    public Utilisateur(int id, String prenom, String nom, String email, String motDePasse, Genre genre, Role role, boolean etat, List<String> preferences, List<CommentaireProjet> commentairesProjet, List<CommentaireIdeeProjet> commentairesIdeeProjet, List<GestionAdminUtilisateur> gestionsAdminUtilisateur) {
        this.id = id;
        this.prenom = prenom;
        this.nom = nom;
        this.email = email;
        this.motDePasse = motDePasse;
        this.genre = genre;
        this.role = role;
        this.etat = etat;
        this.preferences = preferences;
        this.commentairesProjet = commentairesProjet;
        this.commentairesIdeeProjet = commentairesIdeeProjet;
        this.gestionsAdminUtilisateur = gestionsAdminUtilisateur;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String prenom;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String motDePasse;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Genre genre;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(nullable = false)
    private boolean etat = true;

    @ElementCollection
    @CollectionTable(name = "preferences_utilisateurs")
    private List<String> preferences;


    @JsonIgnore
    @OneToMany(mappedBy = "utilisateur")
    private List<CommentaireProjet> commentairesProjet = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "utilisateur")
    private List<CommentaireIdeeProjet> commentairesIdeeProjet = new ArrayList<>();

    @OneToMany(mappedBy = "utilisateur")
    private List<GestionAdminUtilisateur> gestionsAdminUtilisateur = new ArrayList<>();

    public ResponseUtilisateur toResponse() {
        return new ResponsePorteurProjet(
                id,
                prenom,
                nom,
                email,
                genre,
                preferences,
                0
        );
    }
}
