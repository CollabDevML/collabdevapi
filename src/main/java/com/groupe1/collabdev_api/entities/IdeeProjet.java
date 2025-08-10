package com.groupe1.collabdev_api.entities;

import com.groupe1.collabdev_api.dto.response_dto.ResponseCommentaireIdeeProjet;
import com.groupe1.collabdev_api.dto.response_dto.ResponseIdeeProjet;
import com.groupe1.collabdev_api.dto.response_dto.ResponseIdeeProjet2;
import com.groupe1.collabdev_api.dto.response_dto.ResponseUserNames;
import com.groupe1.collabdev_api.entities.enums.DomaineIdeeProjet;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "idees_projet")
public class IdeeProjet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String titre;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @ElementCollection
    @CollectionTable(name = "domaines_idees_projet")
    private List<DomaineIdeeProjet> domaine;

    private String uriCDC;

    @Column(nullable = false)
    private int nombreDeSoutien = 0;

    @Column(nullable = false)
    private LocalDateTime datePublication = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "id_utilisateur")
    private Utilisateur utilisateur;

    @Transient
    @OneToOne(mappedBy = "ideeProjet")
    private Projet projet;

    @OneToMany(mappedBy = "ideeProjet")
    private List<CommentaireIdeeProjet> commentairesIdeeProjet = new ArrayList<>();

    public ResponseIdeeProjet toResponse() {
        return new ResponseIdeeProjet(
                id,
                titre,
                description,
                domaine,
                uriCDC,
                nombreDeSoutien,
                datePublication,
                utilisateur.getId()
        );
    }

    public ResponseIdeeProjet2 toResponse2() {
        List<ResponseCommentaireIdeeProjet> commentaireIdeeProjets = new ArrayList<>();
        for (CommentaireIdeeProjet commentaireIdeeProjet : commentairesIdeeProjet) {
            commentaireIdeeProjets.add(commentaireIdeeProjet.toResponse());
        }
        return new ResponseIdeeProjet2(
                id,
                titre,
                description,
                domaine,
                uriCDC,
                nombreDeSoutien,
                datePublication,
                new ResponseUserNames(
                        utilisateur.getPrenom(),
                        utilisateur.getNom()
                ),
                commentaireIdeeProjets
        );
    }
}
