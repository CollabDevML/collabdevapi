package com.groupe1.collabdev_api.repositories;

import com.groupe1.collabdev_api.entities.Gestionnaire;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GestionnaireRepository extends JpaRepository<Gestionnaire, Integer> {
    List<Gestionnaire> findAllByEstValide(boolean estValide);
}
