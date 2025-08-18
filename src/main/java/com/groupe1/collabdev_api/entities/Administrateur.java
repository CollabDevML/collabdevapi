package com.groupe1.collabdev_api.entities;

import com.groupe1.collabdev_api.dto.response_dto.ResponseAdministrateur;
import com.groupe1.collabdev_api.entities.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity

@Getter
@Setter
@Table(name = "administrateurs")

public class Administrateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public Administrateur() {
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Administrateur(int id, String prenom, String nom, String email, String motDePasse, Role role, List<GestionAdminUtilisateur> gestionsAdminUtilisateur, List<GestionAdminProjet> gestionsAdminProjet) {
        this.id = id;
        this.prenom = prenom;
        this.nom = nom;
        this.email = email;
        this.motDePasse = motDePasse;
        this.role = role;
        this.gestionsAdminUtilisateur = gestionsAdminUtilisateur;
        this.gestionsAdminProjet = gestionsAdminProjet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<GestionAdminUtilisateur> getGestionsAdminUtilisateur() {
        return gestionsAdminUtilisateur;
    }

    public void setGestionsAdminUtilisateur(List<GestionAdminUtilisateur> gestionsAdminUtilisateur) {
        this.gestionsAdminUtilisateur = gestionsAdminUtilisateur;
    }

    public List<GestionAdminProjet> getGestionsAdminProjet() {
        return gestionsAdminProjet;
    }

    public void setGestionsAdminProjet(List<GestionAdminProjet> gestionsAdminProjet) {
        this.gestionsAdminProjet = gestionsAdminProjet;
    }

    @Column(nullable = false)
    private String prenom;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String motDePasse;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @OneToMany(mappedBy = "administrateur")
    private List<GestionAdminUtilisateur> gestionsAdminUtilisateur = new ArrayList<>();

    @OneToMany(mappedBy = "administrateur")
    private List<GestionAdminProjet> gestionsAdminProjet = new ArrayList<>();

    public ResponseAdministrateur toResponse() {
        return
                new ResponseAdministrateur(
                        this.id,
                        this.prenom,
                        this.nom,
                        this.email,
                        this.role
                );
    }
}
