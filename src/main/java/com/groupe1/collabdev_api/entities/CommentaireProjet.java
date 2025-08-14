package com.groupe1.collabdev_api.entities;

import com.groupe1.collabdev_api.dto.response_dto.ResponseCommentaireProjet;
import com.groupe1.collabdev_api.dto.response_dto.ResponseUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Table(name = "commentaires_projet")
@Entity
public class CommentaireProjet {

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
