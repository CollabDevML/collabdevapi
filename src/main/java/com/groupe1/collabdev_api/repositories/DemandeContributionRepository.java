package com.groupe1.collabdev_api.repositories;

import com.groupe1.collabdev_api.entities.DemandeContribution;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DemandeContributionRepository extends JpaRepository<DemandeContribution, Integer> {
    List<DemandeContribution> findByContributeur_Id(int idContributeur);
    List<DemandeContribution> findByProjet_Id(int idProjet);
}
