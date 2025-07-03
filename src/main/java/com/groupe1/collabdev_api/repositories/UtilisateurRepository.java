package com.groupe1.collabdev_api.repositories;

import com.groupe1.collabdev_api.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
}
