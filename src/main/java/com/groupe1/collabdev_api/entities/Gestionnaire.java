package com.groupe1.collabdev_api.entities;

import com.groupe1.collabdev_api.dto.response_dto.ResponseGestionnaire;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter

@Table(name = "gestionnaires")
public class Gestionnaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

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

    public String getUriCv() {
        return uriCv;
    }

    public void setUriCv(String uriCv) {
        this.uriCv = uriCv;
    }

    public boolean isEstValide() {
        return estValide;
    }

    public void setEstValide(boolean estValide) {
        this.estValide = estValide;
    }

    public List<Projet> getProjets() {
        return projets;
    }

    public void setProjets(List<Projet> projets) {
        this.projets = projets;
    }

    public Gestionnaire() {
    }

    public Gestionnaire(int id, Utilisateur utilisateur, String uriCv, boolean estValide, List<Projet> projets) {
        this.id = id;
        this.utilisateur = utilisateur;
        this.uriCv = uriCv;
        this.estValide = estValide;
        this.projets = projets;
    }

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_utilisateur", nullable = false)
    private Utilisateur utilisateur;

    @Column(nullable = false)
    private String uriCv;

    @Column(nullable = false)
    private boolean estValide;

    @OneToMany(mappedBy = "gestionnaire", fetch = FetchType.EAGER)
    private List<Projet> projets;

    public ResponseGestionnaire toResponse() {
        return new ResponseGestionnaire(
                this.utilisateur.getId(),
                this.utilisateur.getPrenom(),
                this.utilisateur.getNom(),
                this.utilisateur.getEmail(),
                this.utilisateur.getGenre(),
                this.utilisateur.getPreferences(),
                this.uriCv,
                this.estValide,
                this.getId()
        );
    }
}
