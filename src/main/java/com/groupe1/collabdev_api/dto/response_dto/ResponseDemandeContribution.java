package com.groupe1.collabdev_api.dto.response_dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Setter
@Getter
public class ResponseDemandeContribution {
    public ResponseDemandeContribution() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDateEnvoi() {
        return dateEnvoi;
    }

    public void setDateEnvoi(LocalDateTime dateEnvoi) {
        this.dateEnvoi = dateEnvoi;
    }

    public ResponseContributeurDemande getContributeur() {
        return contributeur;
    }

    public void setContributeur(ResponseContributeurDemande contributeur) {
        this.contributeur = contributeur;
    }

    public ResponseProjetDemande getProjet() {
        return projet;
    }

    public void setProjet(ResponseProjetDemande projet) {
        this.projet = projet;
    }

    public boolean isEstAcceptee() {
        return estAcceptee;
    }

    public void setEstAcceptee(boolean estAcceptee) {
        this.estAcceptee = estAcceptee;
    }

    public ResponseDemandeContribution(int id, LocalDateTime dateEnvoi, ResponseContributeurDemande contributeur, ResponseProjetDemande projet, boolean estAcceptee) {
        this.id = id;
        this.dateEnvoi = dateEnvoi;
        this.contributeur = contributeur;
        this.projet = projet;
        this.estAcceptee = estAcceptee;
    }

    private int id;
    private LocalDateTime dateEnvoi;
    private ResponseContributeurDemande contributeur;
    private ResponseProjetDemande projet;
    private boolean estAcceptee;
}
