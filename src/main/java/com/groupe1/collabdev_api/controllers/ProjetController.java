package com.groupe1.collabdev_api.controllers;

import com.groupe1.collabdev_api.entities.Projet;
import com.groupe1.collabdev_api.services.ProjetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gestionnaires")
public class ProjetController {

    @Autowired
    private ProjetService projetService;

    @PostMapping("/projets")
    public Projet ajouterProjet(@RequestParam int id, @RequestBody Projet projet) {
        return projetService.ajouter(id, projet);
    }

    @GetMapping("/projets")
    public List<Projet> afficherToutLesProjet() {
        return projetService.chercherTous();
    }

    @GetMapping("/projets/{id}")
    public Projet afficherUnProjet(@PathVariable int id) {
        return projetService.chercherParId(id);
    }

    @GetMapping("/{id}/projets")
    public List<Projet> afficherTousLesProjetDuGestionnaire(@PathVariable int id) {
        return projetService.listerLesProjetParGestionnaireId(id);
    }

    @GetMapping("/{id}/{id_projet}")
    public Projet afficherUnProjetDuGestionnaire(@PathVariable int id, @PathVariable int id_projet) {
        return projetService.chercherUnProjetDuGestionnaire(id, id_projet);
    }

    @PutMapping("/projets/{id}")
    public Projet modifierUnProjet(@PathVariable int id, @RequestBody Projet projet) {
        return projetService.modifier(id, projet);
    }

    @DeleteMapping("/projets/{id}/{idprojet}")
    public boolean supprimerUnProjet(@PathVariable int id, @PathVariable int idprojet) {
        return projetService.supprimerParId(id, idprojet);
    }
}
