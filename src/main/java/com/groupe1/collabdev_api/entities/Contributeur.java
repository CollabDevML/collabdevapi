package com.groupe1.collabdev_api.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "contributeurs")
public class Contributeur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(cascade = CascadeType.REMOVE)
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

    @OneToMany(mappedBy = "contributeur", cascade = CascadeType.REMOVE)

    private List<Contribution> contributions = new ArrayList<>();

    @OneToMany(mappedBy = "contributeur" , cascade = CascadeType.REMOVE)

    private List<DemandeContribution> demandeContributions = new ArrayList<>();

    @OneToMany(mappedBy = "contributeur" , cascade = CascadeType.REMOVE)

    private List<ObtentionBadge> obtentionBadges = new ArrayList<>();

}
