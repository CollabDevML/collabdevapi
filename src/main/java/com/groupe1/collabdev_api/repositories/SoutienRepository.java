package com.groupe1.collabdev_api.repositories;

import com.groupe1.collabdev_api.entities.Soutien;
import com.groupe1.collabdev_api.entities.ids.SoutienId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SoutienRepository extends JpaRepository<Soutien, SoutienId> {
    List<Soutien> findByIdSoutienIdUtilisateur(int idUtilisateur);
}
