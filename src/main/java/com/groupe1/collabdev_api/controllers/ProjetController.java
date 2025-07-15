package com.groupe1.collabdev_api.controllers;

import com.groupe1.collabdev_api.entities.Projet;
import com.groupe1.collabdev_api.services.ProjetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gestionnaires")
@Tag(name="Projet Api",
        description="Gestion du projet")
public class ProjetController {

    @Autowired
    private ProjetService projetService;

    @Operation(summary = "pour l'ajout d'un projet'")
    @PostMapping("/projets")
    public Projet ajouterProjet(@RequestParam int id, @RequestBody Projet projet) {
        return projetService.ajouter(id, projet);
    }

    @Operation(summary = "pour afficher tous les projets")
    @GetMapping("/projets")
    public List<Projet> afficherToutLesProjet() {
        return projetService.chercherTous();
    }

    @Operation(summary = "pour l'affichage d'un seul projet'")
    @GetMapping("/projets/{id}")
    public Projet afficherUnProjet(@PathVariable int id) {
        return projetService.chercherParId(id);
    }

    @Operation(summary = "pour l'affichage de tous les projets d'un gestionnaire'")
    @GetMapping("/{id}/projets")
    public List<Projet> afficherTousLesProjetDuGestionnaire(@PathVariable int id) {
        return projetService.listerLesProjetParGestionnaireId(id);
    }

    @Operation(summary = "pour l'affichage d'un projets des gestionnaire'")
    @GetMapping("/{id}/{id_projet}")
    public Projet afficherUnProjetDuGestionnaire(@PathVariable int id, @PathVariable int id_projet) {
        return projetService.chercherUnProjetDuGestionnaire(id, id_projet);
    }

    @Operation(summary = "pour la modification du projet'")
    @PutMapping("/projets/{id}")
    public Projet modifierUnProjet(@PathVariable int id, @RequestBody Projet projet) {
        return projetService.modifier(id, projet);
    }

    @Operation(summary = "pour la suppression d'un projet'")
    @DeleteMapping("/projets/{id}/{idprojet}")
    public boolean supprimerUnProjet(@PathVariable int id, @PathVariable int idprojet) {
        return projetService.supprimerParId(id, idprojet);
    }
}
