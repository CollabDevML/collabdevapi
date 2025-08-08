package com.groupe1.collabdev_api.repositories;

import com.groupe1.collabdev_api.entities.Gestionnaire;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GestionnaireRepository extends JpaRepository<Gestionnaire, Integer> {
    List<Gestionnaire> findAllByEstValide(boolean estValide);

    Optional<Gestionnaire> findByUtilisateurId(int idUtilisateur);
}
