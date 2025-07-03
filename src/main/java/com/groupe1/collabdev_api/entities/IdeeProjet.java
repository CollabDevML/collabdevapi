package com.groupe1.collabdev_api.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
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
    private String domaine;

    private String uriCDC;

    @Column(nullable = false)
    private int nombreDeSoutien = 0;

    @Column(nullable = false)
    private LocalDate datePublication = LocalDate.now();

    @ManyToOne
    @JoinColumn(name = "id_utilisateur")
    private Utilisateur utilisateur;

    @OneToMany(mappedBy = "ideeProjet")
    private List<CommentaireIdeeProjet> commentairesIdeeProjet = new ArrayList<>();
}
