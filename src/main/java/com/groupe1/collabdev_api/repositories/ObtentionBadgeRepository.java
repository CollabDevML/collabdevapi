package com.groupe1.collabdev_api.repositories;

import com.groupe1.collabdev_api.entities.ObtentionBadge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ObtentionBadgeRepository extends JpaRepository<ObtentionBadge, Integer> {
    List<ObtentionBadge> findByContributeurId(int idContributeur);

    ObtentionBadge findByBadgeId(int idBadge);
}
