package com.groupe1.collabdev_api.entities;

import com.groupe1.collabdev_api.dto.response_dto.ResponseSoutien;
import com.groupe1.collabdev_api.entities.ids.SoutienId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity

@Setter
@Getter
@Table(name = "soutiens")
public class Soutien {
    @EmbeddedId
    private SoutienId idSoutien;

    @Column(nullable = false)
    private LocalDate dateSoutien;

    public SoutienId getIdSoutien() {
        return idSoutien;
    }

    public Soutien(SoutienId idSoutien, LocalDate dateSoutien) {
        this.idSoutien = idSoutien;
        this.dateSoutien = dateSoutien;
    }

    public void setIdSoutien(SoutienId idSoutien) {
        this.idSoutien = idSoutien;
    }

    public LocalDate getDateSoutien() {
        return dateSoutien;
    }

    public void setDateSoutien(LocalDate dateSoutien) {
        this.dateSoutien = dateSoutien;
    }

    public Soutien() {
    }

    public ResponseSoutien toResponse() {
        return new ResponseSoutien(
                idSoutien.getIdIdeeProjet(),
                idSoutien.getIdUtilisateur(),
                dateSoutien
        );
    }
}
