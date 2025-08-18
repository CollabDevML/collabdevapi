package com.groupe1.collabdev_api.entities;

import com.groupe1.collabdev_api.dto.response_dto.ResponseCommentaireProjet;
import com.groupe1.collabdev_api.dto.response_dto.ResponseUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Getter
@Setter
@Table(name = "commentaires_projet")
@Entity
public class CommentaireProjet {
    public CommentaireProjet() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public LocalDateTime getDateCommentaire() {
        return dateCommentaire;
    }

    public void setDateCommentaire(LocalDateTime dateCommentaire) {
        this.dateCommentaire = dateCommentaire;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Projet getProjet() {
        return projet;
    }

    public void setProjet(Projet projet) {
        this.projet = projet;
    }

    public CommentaireProjet(int id, String contenu, LocalDateTime dateCommentaire, Utilisateur utilisateur, Projet projet) {
        this.id = id;
        this.contenu = contenu;
        this.dateCommentaire = dateCommentaire;
        this.utilisateur = utilisateur;
        this.projet = projet;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String contenu;

    @Column(nullable = false)
    private LocalDateTime dateCommentaire = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "id_utilisateur", nullable = false)
    private Utilisateur utilisateur;

    @ManyToOne
    @JoinColumn(name = "id_projet", nullable = false)
    private Projet projet;

    public ResponseCommentaireProjet toResponse() {
        return new ResponseCommentaireProjet(
                this.id,
                this.contenu,
                this.dateCommentaire,
                new ResponseUser(
                        utilisateur.getPrenom(),
                        utilisateur.getNom(),
                        utilisateur.getRole()
                )
        );
    }
}
