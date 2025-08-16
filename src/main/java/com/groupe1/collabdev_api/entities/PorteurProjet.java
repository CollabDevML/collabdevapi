package com.groupe1.collabdev_api.entities;

import com.groupe1.collabdev_api.dto.response_dto.ResponsePorteurProjet;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "porteurs_projet")
@Getter
@Setter

public class PorteurProjet {
    public PorteurProjet(int id, Utilisateur utilisateur) {
        this.id = id;
        this.utilisateur = utilisateur;
    }

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

    public PorteurProjet() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "id_utilisateur", nullable = false)
    private Utilisateur utilisateur;

    public ResponsePorteurProjet toResponse() {
        return new ResponsePorteurProjet(
                utilisateur.getId(),
                utilisateur.getPrenom(),
                utilisateur.getNom(),
                utilisateur.getEmail(),
                utilisateur.getGenre(),
                utilisateur.getPreferences(),
                id
        );
    }

}
