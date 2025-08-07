package com.groupe1.collabdev_api.entities;

import com.groupe1.collabdev_api.dto.response_dto.SoutienResponse;
import com.groupe1.collabdev_api.entities.ids.SoutienId;
import com.groupe1.collabdev_api.repositories.SoutienRepository;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name = "soutiens")
public class Soutien {
    @EmbeddedId
    private SoutienId idSoutien;

    @Column(nullable = false)
    private LocalDate dateSoutien;

    public SoutienResponse toResponse() {
        return new SoutienResponse(
                idSoutien.getIdIdeeProjet(),
                idSoutien.getIdUtilisateur(),
                dateSoutien
        );
    }
}
