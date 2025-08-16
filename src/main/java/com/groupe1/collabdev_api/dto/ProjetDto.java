package com.groupe1.collabdev_api.dto;

import com.groupe1.collabdev_api.dto.response_dto.ResponseGestionnaire;
import com.groupe1.collabdev_api.entities.DemandeContribution;
import com.groupe1.collabdev_api.entities.Tache;
import com.groupe1.collabdev_api.entities.enums.Niveau;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ProjetDto {
    private int id;
    private String titre;
    private String description;
    private boolean estFini;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;

    private Niveau niveauDAcces;
    private boolean etat;
    private int idGestionnaire;
    private int piecesDAcces;
    private int idIdeeProjet;
    private List<DemandeContributionDto> demandeContributions;
    private List<ContributionDto> listContributions;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<ContributionDto> getListContributions() {
        return listContributions;
    }

    public void setListContributions(List<ContributionDto> listContributions) {
        this.listContributions = listContributions;
    }

    public ProjetDto() {
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

    public boolean isEstFini() {
        return estFini;
    }

    public void setEstFini(boolean estFini) {
        this.estFini = estFini;
    }

    public LocalDateTime getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDateTime dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDateTime getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDateTime dateFin) {
        this.dateFin = dateFin;
    }

    public Niveau getNiveauDAcces() {
        return niveauDAcces;
    }

    public void setNiveauDAcces(Niveau niveauDAcces) {
        this.niveauDAcces = niveauDAcces;
    }

    public boolean isEtat() {
        return etat;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
    }

    public int getIdGestionnaire() {
        return idGestionnaire;
    }

    public void setIdGestionnaire(int idGestionnaire) {
        this.idGestionnaire = idGestionnaire;
    }

    public int getPiecesDAcces() {
        return piecesDAcces;
    }

    public void setPiecesDAcces(int piecesDAcces) {
        this.piecesDAcces = piecesDAcces;
    }

    public List<DemandeContributionDto> getDemandeContributions() {
        return demandeContributions;
    }

    public void setDemandeContributions(List<DemandeContributionDto> demandeContributions) {
        this.demandeContributions = demandeContributions;
    }

    public int getIdIdeeProjet() {
        return idIdeeProjet;
    }

    public void setIdIdeeProjet(int idIdeeProjet) {
        this.idIdeeProjet = idIdeeProjet;
    }

    public ProjetDto(int id, String titre, String description, boolean estFini,
                     LocalDateTime dateDebut, LocalDateTime dateFin, Niveau niveauDAcces, boolean etat, int idGestionnaire, int piecesDAcces, int idIdeeProjet,
                     List<DemandeContributionDto> demandeContributions,
                     List<ContributionDto> listContributions) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.estFini = estFini;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.niveauDAcces = niveauDAcces;
        this.etat = etat;
        this.idGestionnaire = idGestionnaire;
        this.piecesDAcces = piecesDAcces;
        this.idIdeeProjet = idIdeeProjet;
        this.demandeContributions = demandeContributions;
        this.listContributions = listContributions;
    }

}
