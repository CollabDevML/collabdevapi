package com.groupe1.collabdev_api.repositories;

import com.groupe1.collabdev_api.entities.ObtentionBadge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ObtentionBadgeRepository extends JpaRepository<ObtentionBadge, Integer> {
    List<ObtentionBadge> findByContributeurId(int idContributeur);

    Optional<ObtentionBadge> findByBadgeId(int idBadge);

    Optional<ObtentionBadge> findByBadgeIdAndContributeurId(int idBadge, int idContributeur);
}
