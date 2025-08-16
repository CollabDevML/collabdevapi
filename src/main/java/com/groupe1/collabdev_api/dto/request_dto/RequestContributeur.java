package com.groupe1.collabdev_api.dto.request_dto;

import com.groupe1.collabdev_api.entities.enums.Genre;
import com.groupe1.collabdev_api.entities.enums.Niveau;
import com.groupe1.collabdev_api.entities.enums.Type;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RequestContributeur extends RequestUtilisateur {

    private Niveau niveau;
    private String specialite;
    private Type type;

    public Niveau getNiveau() {
        return niveau;
    }

    public void setNiveau(Niveau niveau) {
        this.niveau = niveau;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public double getPieces() {
        return pieces;
    }

    public void setPieces(double pieces) {
        this.pieces = pieces;
    }

    public String getUriCv() {
        return uriCv;
    }

    public void setUriCv(String uriCv) {
        this.uriCv = uriCv;
    }

    private double pieces;
    private String uriCv;


    public RequestContributeur(String prenom, String nom, String email, String motDePasse, Genre genre, List<String> preferences, Niveau niveau, String specialite, Type type, double pieces, String uriCv) {
        super(prenom, nom, email, motDePasse, genre, preferences);
        this.niveau = niveau;
        this.specialite = specialite;
        this.type = type;
        this.pieces = pieces;
        this.uriCv = uriCv;
    }
}
