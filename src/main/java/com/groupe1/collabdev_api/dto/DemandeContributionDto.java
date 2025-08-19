package com.groupe1.collabdev_api.dto;

import com.groupe1.collabdev_api.entities.Contributeur;
import com.groupe1.collabdev_api.entities.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DemandeContributionDto {
    private int id;
    private Boolean estAccepte;
    private LocalDateTime dateEnvoi;
    private Type profileContributeur;
    private String contributeurNom;
    private String contributeurPrenom;
    private String projetTitre;
}
