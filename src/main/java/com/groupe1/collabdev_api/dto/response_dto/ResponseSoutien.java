package com.groupe1.collabdev_api.dto.response_dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter

public class ResponseSoutien {
    private int idIdeeProjet;
    private int idUtilisateur;



    public ResponseSoutien(int idIdeeProjet, int idUtilisateur, LocalDate dateSoutien) {
        this.idIdeeProjet = idIdeeProjet;
        this.idUtilisateur = idUtilisateur;
        this.dateSoutien = dateSoutien;
    }

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

    public LocalDate getDateSoutien() {
        return dateSoutien;
    }

    public void setDateSoutien(LocalDate dateSoutien) {
        this.dateSoutien = dateSoutien;
    }

    private LocalDate dateSoutien;
}
