package com.groupe1.collabdev_api.dto.request_dto;

import com.groupe1.collabdev_api.entities.enums.Type;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestDemandeContribution {
    private int idContributeur;
    private int idProjet;
    private Type profileContributeur;

    public int getIdContributeur() {
        return idContributeur;
    }

    public void setIdContributeur(int idContributeur) {
        this.idContributeur = idContributeur;
    }

    public int getIdProjet() {
        return idProjet;
    }

    public void setIdProjet(int idProjet) {
        this.idProjet = idProjet;
    }

    public Type getProfileContributeur() {
        return profileContributeur;
    }

    public void setProfileContributeur(Type profileContributeur) {
        this.profileContributeur = profileContributeur;
    }
}
