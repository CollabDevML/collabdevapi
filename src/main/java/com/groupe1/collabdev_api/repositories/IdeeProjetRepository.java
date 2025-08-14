package com.groupe1.collabdev_api.repositories;

import com.groupe1.collabdev_api.entities.IdeeProjet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IdeeProjetRepository extends JpaRepository<IdeeProjet, Integer> {
    List<IdeeProjet> findByUtilisateurId(int utilisateurId);
}
