package com.groupe1.collabdev_api.repositories;


import com.groupe1.collabdev_api.entities.Projet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjetRepository extends JpaRepository<Projet, Integer>
{
}
