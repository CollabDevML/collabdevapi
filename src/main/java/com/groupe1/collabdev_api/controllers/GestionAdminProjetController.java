package com.groupe1.collabdev_api.controllers;

import com.groupe1.collabdev_api.entities.Projet;
import com.groupe1.collabdev_api.services.GestionAdminProjetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/administrateurs/gestion/projets/")
@Tag(name = "ADMIN API",
        description = "Les operations de l'administrateurs sur les projets")

public class GestionAdminProjetController {
    @Autowired
    private GestionAdminProjetService gestionAdminProjetService;

    @Operation(summary = "pour la recuperation des projets")
    @GetMapping
    public List<Projet> getAllProjets() {
        return gestionAdminProjetService.afficherListeProjet();
    }

    @Operation(summary = "pour l'activation d'un projet")
    @GetMapping("{id}/activer")
    public Projet actviveProjet(@PathVariable int id,@RequestParam("idAdmin")int idA) {
        return gestionAdminProjetService.activerProjet(id,idA);
    }

    @Operation(summary = "pour désactiver un projet")
    @GetMapping("{id}/desactiver")
    public Projet desactiverProjet(@PathVariable int id,@RequestParam("idAdmin") int idA) {
        return gestionAdminProjetService.desactiverProjet(id,idA);
    }

    @Operation(summary = "pour supprimer un projet")
    @DeleteMapping("{id}")
    public void deleteProjet(@PathVariable int id) {
        gestionAdminProjetService.supprimerParId(id);
    }
}
