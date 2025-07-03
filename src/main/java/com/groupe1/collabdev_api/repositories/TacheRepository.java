package com.groupe1.collabdev_api.repositories;

import com.groupe1.collabdev_api.entities.Tache;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TacheRepository extends JpaRepository<Tache, Integer> {
}
