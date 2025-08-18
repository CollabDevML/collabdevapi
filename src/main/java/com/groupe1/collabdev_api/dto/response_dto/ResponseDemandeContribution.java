package com.groupe1.collabdev_api.dto.response_dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ResponseDemandeContribution {
    private int id;
    private LocalDateTime dateEnvoi;
    private ResponseContributeurDemande contributeur;
    private ResponseProjetDemande projet;
    private boolean estAcceptee;
}
