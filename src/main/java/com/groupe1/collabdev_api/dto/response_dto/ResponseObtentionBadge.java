package com.groupe1.collabdev_api.dto.response_dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class ResponseObtentionBadge {
    private int id;
    private String nomContributeur;
    private String titreBadge;
    private LocalDate dateObtention;
}
