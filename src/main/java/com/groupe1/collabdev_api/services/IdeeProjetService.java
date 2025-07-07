package com.groupe1.collabdev_api.services;

import com.groupe1.collabdev_api.entities.IdeeProjet;
import com.groupe1.collabdev_api.repositories.IdeeProjetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IdeeProjetService {

    @Autowired
    private IdeeProjetRepository ideeProjetRepository;

    public IdeeProjet chercherParId(int id) {
        return ideeProjetRepository.findById(id).orElse(null);
    }

    public List<IdeeProjet> chercherTous() {
        return ideeProjetRepository.findAll();
    }

    public IdeeProjet ajouter(IdeeProjet ideeProjet) {
        return ideeProjetRepository.save(ideeProjet);
    }

    public IdeeProjet modifier(IdeeProjet ideeProjet) {
        return ideeProjetRepository.save(ideeProjet);
    }

    public Boolean supprimerParId(int id) {
        ideeProjetRepository.deleteById(id);
        return true;
    }

    public IdeeProjet soutenirIdeeProjet(int id) {
        IdeeProjet projet = ideeProjetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Projet introuvable"));

        projet.setNombreDeSoutien(projet.getNombreDeSoutien() + 1);
        return ideeProjetRepository.save(projet);
    }
}

