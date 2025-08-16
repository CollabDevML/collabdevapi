package com.groupe1.collabdev_api.dto;

import com.groupe1.collabdev_api.entities.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class DemandeContributionDto {
    private int id;
    private Boolean estAccepte;
    private LocalDate dateEnvoi;
    private Type profileContributeur;

    public DemandeContributionDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DemandeContributionDto(int id, Boolean estAccepte, LocalDate dateEnvoi, Type profileContributeur) {
        this.id = id;
        this.estAccepte = estAccepte;
        this.dateEnvoi = dateEnvoi;
        this.profileContributeur = profileContributeur;
    }

    public Boolean getEstAccepte() {
        return estAccepte;
    }

    public void setEstAccepte(Boolean estAccepte) {
        this.estAccepte = estAccepte;
    }

    public LocalDate getDateEnvoi() {
        return dateEnvoi;
    }

    public void setDateEnvoi(LocalDate dateEnvoi) {
        this.dateEnvoi = dateEnvoi;
    }

    public Type getProfileContributeur() {
        return profileContributeur;
    }

    public void setProfileContributeur(Type profileContributeur) {
        this.profileContributeur = profileContributeur;
    }
}
