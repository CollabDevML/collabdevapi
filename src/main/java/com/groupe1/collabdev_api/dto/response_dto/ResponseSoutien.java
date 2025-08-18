package com.groupe1.collabdev_api.dto.response_dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter

public class ResponseSoutien {
    public int getIdIdeeProjet() {
        return idIdeeProjet;
    }

    public void setIdIdeeProjet(int idIdeeProjet) {
        this.idIdeeProjet = idIdeeProjet;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public LocalDateTime getDateSoutien() {
        return dateSoutien;
    }

    public void setDateSoutien(LocalDateTime dateSoutien) {
        this.dateSoutien = dateSoutien;
    }

    private int idIdeeProjet;
    private int idUtilisateur;

    public ResponseSoutien() {
    }

    private LocalDateTime dateSoutien;

    public ResponseSoutien(int idIdeeProjet, int idUtilisateur, LocalDateTime dateSoutien) {
        this.idIdeeProjet = idIdeeProjet;
        this.idUtilisateur = idUtilisateur;
        this.dateSoutien = dateSoutien;
    }
}
