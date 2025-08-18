package com.groupe1.collabdev_api.dto.response_dto;

import com.groupe1.collabdev_api.entities.enums.NiveauTache;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
public class ResponseTache {
    private int id;
    private String titre;
    private String description;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;

    public ResponseTache() {
    }

    private int piecesAGagner;

    public ResponseTache(int id, String titre, String description, LocalDateTime dateDebut, LocalDateTime dateFin, int piecesAGagner, NiveauTache niveau) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.piecesAGagner = piecesAGagner;
        this.niveau = niveau;
    }

    private NiveauTache niveau;

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

    public int getPiecesAGagner() {
        return piecesAGagner;
    }

    public void setPiecesAGagner(int piecesAGagner) {
        this.piecesAGagner = piecesAGagner;
    }

    public NiveauTache getNiveau() {
        return niveau;
    }

    public void setNiveau(NiveauTache niveau) {
        this.niveau = niveau;
    }
}
