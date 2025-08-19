package com.groupe1.collabdev_api.repositories;

import com.groupe1.collabdev_api.entities.Messagerie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MessagerieRepository extends JpaRepository<Messagerie, Integer> {
    List <Messagerie> findTop20ByProjetIdOrderByTimestampDesc(int idProjet);
}
