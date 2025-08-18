package com.groupe1.collabdev_api.dto.response_dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ResponseBadge {
    private int id;

    public ResponseBadge() {
    }

    public ResponseBadge(int id, String titre, String uriImage) {
        this.id = id;
        this.titre = titre;
        this.uriImage = uriImage;
    }

    private String titre;

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

    public String getUriImage() {
        return uriImage;
    }

    public void setUriImage(String uriImage) {
        this.uriImage = uriImage;
    }

    private String uriImage;
}
