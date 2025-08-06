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
@NoArgsConstructor
@AllArgsConstructor
public class PorteurProjet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "id_utilisateur", nullable = false)
    private Utilisateur utilisateur;

    public ResponsePorteurProjet toResponse() {
        return new ResponsePorteurProjet(
                id,
                utilisateur.getPrenom(),
                utilisateur.getNom(),
                utilisateur.getEmail(),
                utilisateur.getMotDePasse(),
                utilisateur.getGenre(),
                utilisateur.getPreferences()
        );
    }

}
