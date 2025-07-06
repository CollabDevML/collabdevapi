package com.groupe1.collabdev_api.repositories;

import com.groupe1.collabdev_api.entities.Contribution;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContributionRepository extends JpaRepository<Contribution, Integer> {
    List<Contribution> findByContributeur_Id(int idContributeur);
    List<Contribution> findByProjet_Id(int idProjet);
    List<Contribution> findByContributeur_IdAndProjet_Id(int idContributeur, int idProjet);
    List<Contribution> findByContributeur_IdAndProjet_IdAndEstValideFalse(
            int idContributeur, int idProjet
    );
    List<Contribution> findByContributeur_IdAndProjet_IdAndEstValideTrue(
            int idContributeur, int idProjet
    );
}
