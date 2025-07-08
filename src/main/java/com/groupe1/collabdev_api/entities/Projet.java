package com.groupe1.collabdev_api.entities;

import com.groupe1.collabdev_api.entities.enums.Niveau;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Table(name = "projets")
@Entity
public class Projet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String titre;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private boolean estFini = false;

    @Column(nullable = false)
    private LocalDate dateDebut;

    @Column(nullable = false)
    private LocalDate dateFin;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Niveau niveauDAcces;

    @Column(nullable = false)
    private boolean etat=true;

    @ManyToOne
    @JoinColumn(name = "id_gestionnaire", nullable = false)
    private Gestionnaire gestionnaire;

    @OneToMany(mappedBy = "projet", cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<Tache> taches;

    @OneToMany(mappedBy = "projet")
    private List<Contribution> contributions = new ArrayList<>();

    @OneToMany(mappedBy = "projet")
    private List<DemandeContribution> demandeContributions = new ArrayList<>();

    @OneToMany(mappedBy = "projet")
    private List<CommentaireProjet> commentairesProjet = new ArrayList<>();

    @OneToMany(mappedBy = "projet")
    private List<GestionAdminProjet> gestionsAdminProjet = new ArrayList<>();

    @Override
    public boolean equals(Object object)
    {
        if(this == object ) return true;
        if(!(object instanceof Projet)) return false;
        Projet projet = (Projet) object;
        return id == projet.getId();
    }
    @Override
    public int hashCode()
    {
        return Integer.hashCode(id);
    }

}
