package com.groupe1.collabdev_api.repositories;

import com.groupe1.collabdev_api.entities.Badge;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BadgeRepository extends JpaRepository<Badge, Integer> {
}
