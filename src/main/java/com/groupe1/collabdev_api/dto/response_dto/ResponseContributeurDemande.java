package com.groupe1.collabdev_api.dto.response_dto;

import com.groupe1.collabdev_api.entities.enums.Niveau;
import com.groupe1.collabdev_api.entities.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class ResponseContributeurDemande {
    private int idUtilisateur;

    public ResponseContributeurDemande() {
    }

    private String prenom;

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
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

    public Niveau getNiveau() {
        return niveau;
    }

    public void setNiveau(Niveau niveau) {
        this.niveau = niveau;
    }

    public Type getTypeProfile() {
        return typeProfile;
    }

    public void setTypeProfile(Type typeProfile) {
        this.typeProfile = typeProfile;
    }

    public ResponseContributeurDemande(int idUtilisateur, String prenom, String nom, Niveau niveau, Type typeProfile) {
        this.idUtilisateur = idUtilisateur;
        this.prenom = prenom;
        this.nom = nom;
        this.niveau = niveau;
        this.typeProfile = typeProfile;
    }

    private String nom;
    private Niveau niveau;
    private Type typeProfile;
}
