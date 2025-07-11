package com.groupe1.collabdev_api.repositories;

import com.groupe1.collabdev_api.entities.Contributeur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContributeurRepository extends JpaRepository<Contributeur, Integer> {
}
