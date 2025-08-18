package com.groupe1.collabdev_api.dto;

import com.groupe1.collabdev_api.entities.IdeeProjet;
import com.groupe1.collabdev_api.entities.Projet;
import com.groupe1.collabdev_api.entities.Utilisateur;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Commentaire {
    private String contenu;
    private Projet projet;
    private Utilisateur user;

    public Commentaire() {
    }

    public Commentaire(String contenu, Projet projet, Utilisateur user, IdeeProjet ideeProjet) {
        this.contenu = contenu;
        this.projet = projet;
        this.user = user;
        this.ideeProjet = ideeProjet;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Projet getProjet() {
        return projet;
    }

    public void setProjet(Projet projet) {
        this.projet = projet;
    }

    public Utilisateur getUser() {
        return user;
    }

    public void setUser(Utilisateur user) {
        this.user = user;
    }

    public IdeeProjet getIdeeProjet() {
        return ideeProjet;
    }

    public void setIdeeProjet(IdeeProjet ideeProjet) {
        this.ideeProjet = ideeProjet;
    }

    private IdeeProjet ideeProjet;
}
