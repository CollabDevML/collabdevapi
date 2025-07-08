package com.groupe1.collabdev_api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DemandeContributionDto {
    private int id;
    private Boolean estAccepte;
    private LocalDate dateEnvoi;
}
