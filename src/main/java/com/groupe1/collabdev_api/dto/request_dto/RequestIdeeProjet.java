package com.groupe1.collabdev_api.dto.request_dto;

import com.groupe1.collabdev_api.entities.enums.DomaineIdeeProjet;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RequestIdeeProjet {
    private String titre;
    private String description;
    private List<DomaineIdeeProjet> domaine;

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

    public List<DomaineIdeeProjet> getDomaine() {
        return domaine;
    }

    public void setDomaine(List<DomaineIdeeProjet> domaine) {
        this.domaine = domaine;
    }

    public String getUriCDC() {
        return uriCDC;
    }

    public void setUriCDC(String uriCDC) {
        this.uriCDC = uriCDC;
    }

    private String uriCDC;
}
