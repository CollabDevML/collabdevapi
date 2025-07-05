package com.groupe1.collabdev_api.controllers;

import com.groupe1.collabdev_api.entities.Projet;
import com.groupe1.collabdev_api.services.GestionAdminProjetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin/gestion/projet/")
public class GestionAdminProjetController {
    @Autowired
    private GestionAdminProjetService gestionAdminProjetService;

    //Pour la recuperation des Projets
    @GetMapping
    public List<Projet> getAllProjets() {
        return gestionAdminProjetService.afficherListeProjet();
    }

    //Pour activer un Projet :
    @GetMapping("activer/{id}")
    public Projet actviveProjet(@PathVariable int id,@RequestParam("idAdmin")int idA) {
        return gestionAdminProjetService.activerProjet(id,idA);
    }

    //Pour desactiver un Projet :
    @GetMapping("desactiver/{id}")
    public Projet desactiverProjet(@PathVariable int id,@RequestParam("idAdmin") int idA) {
        return gestionAdminProjetService.desactiverProjet(id,idA);
    }

    //Pour supprimer un projet :
    @DeleteMapping("{id}")
    public void deleteProjet(@PathVariable int id) {
        gestionAdminProjetService.supprimerParId(id);
    }
}
