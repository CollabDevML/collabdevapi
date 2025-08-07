package com.groupe1.collabdev_api.entities.ids;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SoutienId {
    private int idUtilisateur;
    private int idIdeeProjet;
}
