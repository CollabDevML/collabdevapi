package com.groupe1.collabdev_api.entities;

import com.groupe1.collabdev_api.entities.enums.TypeGestionUtilisateurs;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "gestions_admin_utilisateur")
@Getter
@Setter
@NoArgsConstructor
public class GestionAdminUtilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private TypeGestionUtilisateurs typeGestion;

    @Column(nullable = false)
    private LocalDate dateGestion = LocalDate.now();

    @ManyToOne
    @JoinColumn(nullable = false, name = "id_administrateur")
    private Administrateur administrateur;

    @ManyToOne
    @JoinColumn(nullable = false, name = "id_utilisateur")
    private Utilisateur utilisateur;
}
