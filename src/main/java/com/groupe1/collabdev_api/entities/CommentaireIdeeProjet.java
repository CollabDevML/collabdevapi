package com.groupe1.collabdev_api.entities;

import com.groupe1.collabdev_api.dto.response_dto.ResponseCommentaireIdeeProjet;
import com.groupe1.collabdev_api.dto.response_dto.ResponseUserNames;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@Table(name = "commentaires_idee_projet")
@Entity
public class CommentaireIdeeProjet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public CommentaireIdeeProjet() {
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

    public LocalDate getDateCommentaire() {
        return dateCommentaire;
    }

    public void setDateCommentaire(LocalDate dateCommentaire) {
        this.dateCommentaire = dateCommentaire;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public IdeeProjet getIdeeProjet() {
        return ideeProjet;
    }

    public void setIdeeProjet(IdeeProjet ideeProjet) {
        this.ideeProjet = ideeProjet;
    }

    public CommentaireIdeeProjet(int id, String contenu, LocalDate dateCommentaire, Utilisateur utilisateur, IdeeProjet ideeProjet) {
        this.id = id;
        this.contenu = contenu;
        this.dateCommentaire = dateCommentaire;
        this.utilisateur = utilisateur;
        this.ideeProjet = ideeProjet;
    }

    @Column(nullable = false)
    private String contenu;

    @Column(nullable = false)
    private LocalDate dateCommentaire = LocalDate.now();

    @ManyToOne
    @JoinColumn(name = "id_utilisateur", nullable = false)
    private Utilisateur utilisateur;

    @ManyToOne
    @JoinColumn(name = "id_idee_projet", nullable = false)
    private IdeeProjet ideeProjet;

    public ResponseCommentaireIdeeProjet toResponse() {
        return new ResponseCommentaireIdeeProjet(
                this.id,
                this.contenu,
                this.dateCommentaire,
                new ResponseUserNames(
                        this.utilisateur.getPrenom(),
                        this.utilisateur.getNom()
                )
        );
    }
}