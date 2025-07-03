package com.groupe1.collabdev_api.repositories;

import com.groupe1.collabdev_api.entities.Administrateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministrateurRepository extends JpaRepository<Administrateur, Integer> {
}
