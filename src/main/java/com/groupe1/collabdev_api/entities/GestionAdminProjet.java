package com.groupe1.collabdev_api.entities;

import com.groupe1.collabdev_api.entities.enums.TypeGestionProjet;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter

@Table(name = "gestions_admin_projet")
public class GestionAdminProjet {
    public GestionAdminProjet() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TypeGestionProjet getType() {
        return type;
    }

    public void setType(TypeGestionProjet type) {
        this.type = type;
    }

    public LocalDate getDateGestion() {
        return dateGestion;
    }

    public void setDateGestion(LocalDate dateGestion) {
        this.dateGestion = dateGestion;
    }

    public Administrateur getAdministrateur() {
        return administrateur;
    }

    public void setAdministrateur(Administrateur administrateur) {
        this.administrateur = administrateur;
    }

    public Projet getProjet() {
        return projet;
    }

    public void setProjet(Projet projet) {
        this.projet = projet;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public GestionAdminProjet(int id, TypeGestionProjet type, LocalDate dateGestion, Administrateur administrateur, Projet projet) {
        this.id = id;
        this.type = type;
        this.dateGestion = dateGestion;
        this.administrateur = administrateur;
        this.projet = projet;
    }

    @Column(nullable = false)
    private TypeGestionProjet type;

    @Column(nullable = false)
    private LocalDate dateGestion = LocalDate.now();

    @ManyToOne
    @JoinColumn(nullable = false, name = "id_administrateur")
    private Administrateur administrateur;

    @ManyToOne
    @JoinColumn(nullable = false, name = "id_projet")
    private Projet projet;
}
