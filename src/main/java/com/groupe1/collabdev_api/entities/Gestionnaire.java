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
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "gestionnaires")
public class Gestionnaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_utilisateur", nullable = false)
    private Utilisateur utilisateur;

    @Column(nullable = false)
    private String uriCv;

    @Column(nullable = false)
    private boolean estValide;

    @OneToMany
    private List<Projet> projets;

    public ResponseGestionnaire toResponse() {
        return new ResponseGestionnaire(
                this.utilisateur.getId(),
                this.utilisateur.getPrenom(),
                this.utilisateur.getNom(),
                this.utilisateur.getEmail(),
                this.utilisateur.getMotDePasse(),
                this.utilisateur.getGenre(),
                this.uriCv,
                this.estValide,
                this.getId()
        );
    }
}
