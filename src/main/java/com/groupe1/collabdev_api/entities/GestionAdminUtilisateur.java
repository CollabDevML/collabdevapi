package com.groupe1.collabdev_api.entities;

import com.groupe1.collabdev_api.entities.enums.TypeGestionUtilisateurs;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "gestions_admin_utilisateur")
@Getter
@Setter
public class GestionAdminUtilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public GestionAdminUtilisateur(int id, TypeGestionUtilisateurs typeGestion, LocalDateTime dateGestion, Administrateur administrateur, Utilisateur utilisateur) {
        this.id = id;
        this.typeGestion = typeGestion;
        this.dateGestion = dateGestion;
        this.administrateur = administrateur;
        this.utilisateur = utilisateur;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TypeGestionUtilisateurs getTypeGestion() {
        return typeGestion;
    }

    public void setTypeGestion(TypeGestionUtilisateurs typeGestion) {
        this.typeGestion = typeGestion;
    }

    public LocalDateTime getDateGestion() {
        return dateGestion;
    }

    public void setDateGestion(LocalDateTime dateGestion) {
        this.dateGestion = dateGestion;
    }

    public Administrateur getAdministrateur() {
        return administrateur;
    }

    public void setAdministrateur(Administrateur administrateur) {
        this.administrateur = administrateur;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public GestionAdminUtilisateur() {
    }

    @Column(nullable = false)
    private TypeGestionUtilisateurs typeGestion;

    @Column(nullable = false)
    private LocalDateTime dateGestion = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(nullable = false, name = "id_administrateur")
    private Administrateur administrateur;

    @ManyToOne
    @JoinColumn(nullable = false, name = "id_utilisateur")
    private Utilisateur utilisateur;
}
