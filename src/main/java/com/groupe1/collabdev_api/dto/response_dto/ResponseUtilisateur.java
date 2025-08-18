package com.groupe1.collabdev_api.dto.response_dto;

import com.groupe1.collabdev_api.entities.enums.Genre;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class ResponseUtilisateur {
    public ResponseUtilisateur() {
    }

    private int idUtilisateur;
    private String prenom;

    public ResponseUtilisateur(int idUtilisateur, String prenom, String nom, String email, Genre genre, List<String> preferences) {
        this.idUtilisateur = idUtilisateur;
        this.prenom = prenom;
        this.nom = nom;
        this.email = email;
        this.genre = genre;
        this.preferences = preferences;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public List<String> getPreferences() {
        return preferences;
    }

    public void setPreferences(List<String> preferences) {
        this.preferences = preferences;
    }

    private String nom;
    private String email;
    private Genre genre;
    private List<String> preferences;
}
