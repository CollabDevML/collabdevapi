package com.groupe1.collabdev_api.repositories;

import com.groupe1.collabdev_api.entities.Gestionnaire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GestionnaireRepository extends JpaRepository<Gestionnaire, Integer> {
}
