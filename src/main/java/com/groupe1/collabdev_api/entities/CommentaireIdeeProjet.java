package com.groupe1.collabdev_api.entities;

import com.groupe1.collabdev_api.dto.response_dto.ResponseCommentaireIdeeProjet;
import com.groupe1.collabdev_api.dto.response_dto.ResponseUserNames;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Table(name = "commentaires_idee_projet")
@Entity
public class CommentaireIdeeProjet {

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