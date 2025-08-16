package com.groupe1.collabdev_api.entities.ids;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter

public class SoutienId {
    public SoutienId(int idUtilisateur, int idIdeeProjet) {
        this.idUtilisateur = idUtilisateur;
        this.idIdeeProjet = idIdeeProjet;
    }

    public SoutienId() {
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public int getIdIdeeProjet() {
        return idIdeeProjet;
    }

    public void setIdIdeeProjet(int idIdeeProjet) {
        this.idIdeeProjet = idIdeeProjet;
    }

    private int idUtilisateur;
    private int idIdeeProjet;
}
