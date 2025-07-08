package com.groupe1.collabdev_api.controllers;

import com.groupe1.collabdev_api.entities.IdeeProjet;
import com.groupe1.collabdev_api.services.IdeeProjetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/utilisateurs/idees-projet")
@Tag(name = "Project API",
        description = "Gestion CRUD du projet")

public class IdeeProjetController {
    @Autowired
    private IdeeProjetService ideeProjetService;

    @Operation(summary = "pour l'ajout d'idée de projet")
    @PostMapping
    public IdeeProjet ajouterIdeeProjet(@RequestBody IdeeProjet ideeProjet) {
        ideeProjet.setNombreDeSoutien(0);
        return ideeProjetService.ajouter(ideeProjet);
    }

    @Operation(summary = "pour lister tous les idées projets")
    @GetMapping
    public List<IdeeProjet> listeIdeeProjet() {
        return ideeProjetService.chercherTous();
    }

    @Operation(summary = "pour modifier une idée projet")
    @PutMapping
    public IdeeProjet modifierIdeeProjet(@RequestBody IdeeProjet ideeProjet) {
        return ideeProjetService.modifier(ideeProjet);
    }

    @Operation(summary = "pour supprimer une idée de projet")
    @DeleteMapping("/{id}")
    public Boolean supprimerIdeeProjet(@PathVariable int id) {
        return ideeProjetService.supprimerParId(id);
    }

    @GetMapping("/{id}")
    public IdeeProjet chercherParId(@RequestParam int id) {
        return ideeProjetService.chercherParId(id);
    }

    @Operation(summary = "pour soutenir une idée de projet")
    @PutMapping("/nombre-soutien/{id}")
    public IdeeProjet soutenir(@PathVariable int id) {
        return ideeProjetService.soutenirIdeeProjet(id);
    }
}
