package com.groupe1.collabdev_api.controllers;

import com.groupe1.collabdev_api.entities.PorteurProjet;
import com.groupe1.collabdev_api.entities.Utilisateur;
import com.groupe1.collabdev_api.services.PorteurProjetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/utilisateurs/porteurs-projet")
@Tag(name = "ADMIN API",
        description = "Gestion du porteur de projet")

public class PorteurProjetController {
    @Autowired
    private PorteurProjetService porteurProjetService;

    @Operation(summary = "l'ajout d'un porteur de projet")
    @PostMapping
    public PorteurProjet ajouter(@RequestBody Utilisateur user) {
        return porteurProjetService.ajouter(user);
    }

    @Operation(summary = "pour avoir un porteur de projet par son id")
    @GetMapping("/{id}")
    public PorteurProjet rechercheParId(@RequestParam int id) {
        return porteurProjetService.chercherParId(id);
    }

    @Operation(summary = "pour lister tous les porteurs de projet")
    @GetMapping
    public List<PorteurProjet> listerPorteurProjet() {
        return porteurProjetService.chercherTous();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PorteurProjet> modifierParId(@PathVariable int id, @RequestBody Utilisateur user) {
        PorteurProjet porteurProjet = porteurProjetService.modifier(id, user);
        return ResponseEntity.ok(porteurProjet);
    }

    @Operation(summary = "pour supprimer un porteur de projet")
    @DeleteMapping("/{id}")
    public void supprimerParId(@PathVariable int id) {
        porteurProjetService.supprimerParId(id);
    }


}
