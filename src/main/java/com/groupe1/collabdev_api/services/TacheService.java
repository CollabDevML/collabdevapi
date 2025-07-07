package com.groupe1.collabdev_api.services;

import com.groupe1.collabdev_api.entities.Gestionnaire;
import com.groupe1.collabdev_api.entities.Projet;
import com.groupe1.collabdev_api.entities.Tache;
import com.groupe1.collabdev_api.repositories.GestionnaireRepository;
import com.groupe1.collabdev_api.repositories.ProjetRepository;
import com.groupe1.collabdev_api.repositories.TacheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TacheService {

    @Autowired
    private TacheRepository tacheRepository;
    @Autowired
    private ProjetRepository projetRepository;
    @Autowired
    private GestionnaireRepository gestionnaireRepository;

    //Rechercher une tache dans un projet
    public Tache chercherParId(int idProjet, int tacheId){
        Projet projet = projetRepository.findById(idProjet).orElseThrow(() -> new RuntimeException("Projet introuvable"));
        Tache tache = tacheRepository.findById(tacheId).orElseThrow(() -> new RuntimeException("Projet introuvable"));
        if (tache.getProjet() != null && tache.getProjet().getId() == projet.getId()){
            return tache;
        }
        throw new RuntimeException("Cette tache n'existe pas dans ce projet");
//        if (projet.getTaches().get().getId() == tache.getId() ){
//            return tacheRepository.findById(idProjet).orElse(null);
//        }
    }

    //Chercher tous les taches d'un projet
    public List<Tache> chercherTous(int projetId){
        Projet projet = projetRepository.findById(projetId).orElseThrow(() -> new RuntimeException("Projet introuvable"));

        if (!projet.getTaches().isEmpty()){
            return tacheRepository.findAll();
        }
        throw new RuntimeException("Ce ptojet n'as pas de taches");
    }

    public Tache ajouter(int idProjet, int gestionnaireId ,Tache tache){
        Projet projet = projetRepository.findById(idProjet)
                .orElseThrow(() -> new RuntimeException("Projet introuvable"));
        Gestionnaire gestionnaire = gestionnaireRepository.findById(gestionnaireId)
                .orElseThrow(() -> new RuntimeException("Gestionnaire introuvable"));

        // Vérification d'autorisation
        if (projet.getGestionnaire().getId() == gestionnaire.getId()) {
        // Liaison de la tâche avec le projet
        tache.setProjet(projet);
        return tacheRepository.save(tache);
        }
        throw new RuntimeException("Vous n'avez pas le droit de créer une tâche pour ce projet");

    }

    public Tache modifier(int idTache,Tache tache){
        if (tache.getId() == idTache){
         return tacheRepository.save(tache);
        }
        throw new RuntimeException("Vous n'avez pas le droit de modifier une tâche");
    }

    public Boolean supprimerParId(int idTache, int gestionnaireId){
        Tache tache = tacheRepository.findById(idTache).orElseThrow(() -> new RuntimeException("Projet introuvable"));
        if (tache.getProjet().getGestionnaire().getId() == gestionnaireId){
            tacheRepository.deleteById(idTache);
            return true;
        }
        return false;
    }
}
