package com.groupe1.collabdev_api.dto.response_dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class UsersCount {
    private long contributeurs;
    private long gestionnaires;

    public UsersCount() {
    }

    public UsersCount(long contributeurs, long gestionnaires, long porteursProjet) {
        this.contributeurs = contributeurs;
        this.gestionnaires = gestionnaires;
        this.porteursProjet = porteursProjet;
    }

    private long porteursProjet;

    public long getContributeurs() {
        return contributeurs;
    }

    public void setContributeurs(long contributeurs) {
        this.contributeurs = contributeurs;
    }

    public long getGestionnaires() {
        return gestionnaires;
    }

    public void setGestionnaires(long gestionnaires) {
        this.gestionnaires = gestionnaires;
    }

    public long getPorteursProjet() {
        return porteursProjet;
    }

    public void setPorteursProjet(long porteursProjet) {
        this.porteursProjet = porteursProjet;
    }
}
