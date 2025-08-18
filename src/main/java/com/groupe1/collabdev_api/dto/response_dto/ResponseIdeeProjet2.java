package com.groupe1.collabdev_api.dto.response_dto;

import com.groupe1.collabdev_api.entities.enums.DomaineIdeeProjet;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter

public class ResponseIdeeProjet2 {
    private int id;
    private String titre;

    public ResponseIdeeProjet2() {
    }

    public ResponseIdeeProjet2(int id, String titre, String description, List<DomaineIdeeProjet> domaine, String uriCDC, int nombreSoutien, LocalDateTime datePublication, ResponseUser utilisateur, List<ResponseCommentaireIdeeProjet> commentaireIdeeProjets) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.domaine = domaine;
        this.uriCDC = uriCDC;
        this.nombreSoutien = nombreSoutien;
        this.datePublication = datePublication;
        this.utilisateur = utilisateur;
        this.commentaireIdeeProjets = commentaireIdeeProjets;
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

    public int getNombreSoutien() {
        return nombreSoutien;
    }

    public void setNombreSoutien(int nombreSoutien) {
        this.nombreSoutien = nombreSoutien;
    }

    public LocalDateTime getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(LocalDateTime datePublication) {
        this.datePublication = datePublication;
    }

    public ResponseUser getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(ResponseUser utilisateur) {
        this.utilisateur = utilisateur;
    }

    public List<ResponseCommentaireIdeeProjet> getCommentaireIdeeProjets() {
        return commentaireIdeeProjets;
    }

    public void setCommentaireIdeeProjets(List<ResponseCommentaireIdeeProjet> commentaireIdeeProjets) {
        this.commentaireIdeeProjets = commentaireIdeeProjets;
    }

    private String description;
    private List<DomaineIdeeProjet> domaine;
    private String uriCDC;
    private int nombreSoutien;
    private LocalDateTime datePublication;
    private ResponseUser utilisateur;
    private List<ResponseCommentaireIdeeProjet> commentaireIdeeProjets;
}
