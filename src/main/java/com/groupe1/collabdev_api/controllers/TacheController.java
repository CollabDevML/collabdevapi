package com.groupe1.collabdev_api.controllers;

import com.groupe1.collabdev_api.entities.Tache;
import com.groupe1.collabdev_api.services.TacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/taches")
public class TacheController {

    @Autowired
    private TacheService tacheService;

    @PostMapping
    public Tache ajouterUneTache (@RequestParam int projectId, @RequestParam int gestionnaireId, @RequestBody Tache tache){
        return tacheService.ajouter(projectId,gestionnaireId,tache);
    }

    @GetMapping
    public List<Tache> afficherTousLesTache(@RequestParam int projetId){
        return tacheService.chercherTous(projetId);
    }

    @GetMapping
    public Tache afficherUneTache(@RequestParam int projetId ,@RequestParam int tacheId){
        return tacheService.chercherParId(projetId, tacheId);
    }

    @PutMapping
    public Tache modifierUneTache(@RequestParam int idTache, @RequestBody Tache tache){
        return tacheService.modifier(idTache, tache);
    }

    @DeleteMapping
    public boolean supprimerUneTache(@RequestParam int idTache,@RequestParam int idGestionnaire){
        return tacheService.supprimerParId(idTache, idGestionnaire);
    }
}
