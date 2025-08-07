package com.groupe1.collabdev_api.repositories;

import com.groupe1.collabdev_api.entities.PorteurProjet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PorteurProjetRepository extends JpaRepository<PorteurProjet, Integer> {
    Optional<PorteurProjet> findByUtilisateurId(int idUtilisateur);
}
