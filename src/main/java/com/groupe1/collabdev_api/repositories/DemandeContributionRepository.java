package com.groupe1.collabdev_api.repositories;

import com.groupe1.collabdev_api.entities.DemandeContribution;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DemandeContributionRepository extends JpaRepository<DemandeContribution, Integer> {
    List<DemandeContribution> findByContributeur_Id(int idContributeur);
    List<DemandeContribution> findByProjet_Id(int idProjet);
    Optional<DemandeContribution> findByContributeur_IdAndProjet_Id(int idContributeur, int idProjet);
    List<DemandeContribution> findByContributeur_IdAndProjet_IdAndEstAccepteeTrue(int idContributeur, int idProjet);
    List<DemandeContribution> findByContributeurIdAndEstAcceptee(int contributeur, boolean estAcceptee);
    List<DemandeContribution> findByContributeur_IdAndProjet_IdAndEstAccepteeFalse(int idContributeur, int idProjet);
    List<DemandeContribution> findByContributeur_IdAndEstAccepteeTrue(int idContributeur);
    DemandeContribution deleteByContributeur_IdAndProjet_Id(int idContributeur, int idProjet);
}
