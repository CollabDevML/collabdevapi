package com.groupe1.collabdev_api.repositories;


import com.groupe1.collabdev_api.entities.Projet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProjetRepository extends JpaRepository<Projet, Integer>
{
    List<Projet> findByGestionnaireId(int gestionnaireId);
    Optional<Projet> findByIdAndGestionnaireId(Integer id, Integer gestionnaireId);
}
