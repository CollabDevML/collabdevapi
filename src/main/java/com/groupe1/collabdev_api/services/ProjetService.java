package com.groupe1.collabdev_api.services;

import com.groupe1.collabdev_api.entities.Gestionnaire;
import com.groupe1.collabdev_api.entities.Projet;
import com.groupe1.collabdev_api.repositories.GestionnaireRepository;
import com.groupe1.collabdev_api.repositories.ProjetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjetService {

    @Autowired
    private ProjetRepository projetRepository;
    @Autowired
    private GestionnaireRepository gestionnaireRepository;

    public Projet chercherParId(int id) {
        return projetRepository.findById(id).orElse(null);
    }

    public List<Projet> chercherTous() {
        return projetRepository.findAll();
    }

    public Projet ajouter(int id, Projet projet) {
        Gestionnaire gestionnaire = gestionnaireRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Gestionnaire introuvable"));
        projet.setGestionnaire(gestionnaire);
        return projetRepository.save(projet);
    }

    //Lister les projet d'un gestionnaire
    public List<Projet> listerLesProjetParGestionnaireId(int gestionnaireId) {
        // Vérifie que le gestionnaire existe
        if (!gestionnaireRepository.existsById(gestionnaireId)) {
            throw new RuntimeException("Gestionnaire introuvable avec l'id : " + gestionnaireId);
        }
        // Récupère les projets liés au gestionnaire
        return projetRepository.findByGestionnaireId(gestionnaireId);
    }

    //afficher un projet d'un gestionnaire
    public Projet chercherUnProjetDuGestionnaire(int gestionnaireId, int id) {
        return projetRepository.findByIdAndGestionnaireId(gestionnaireId, id)
                .orElseThrow(() -> new RuntimeException("Gestionnaire introuvable"));
    }

    public Projet modifier(int id, Projet projet) {
        if (projet.getGestionnaire().getId() == id) {
            return projetRepository.save(projet);
        } else {
            return null;
        }

    }

    public Boolean supprimerParId(int gestionnaireId, int id) {
        Projet projet = projetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("projet introuvable"));
        if (gestionnaireId == projet.getGestionnaire().getId()) {
            projetRepository.deleteById(id);
            return true;
        } else {
            System.out.println("Vous n'avez pas le droit de supprimer un projet");
            return false;
        }
    }
}

