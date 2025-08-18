package com.groupe1.collabdev_api.dto.response_dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class ResponseProjetDemande {
    private int id;
    private String titre;

    public ResponseProjetDemande() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ResponseProjetDemande(int id, String titre, String description) {
        this.id = id;
        this.titre = titre;
        this.description = description;
    }

    private String description;
}
