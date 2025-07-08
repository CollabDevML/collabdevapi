package com.groupe1.collabdev_api.controllers;

import com.groupe1.collabdev_api.entities.ObtentionBadge;
import com.groupe1.collabdev_api.services.ObtentionBadgeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/utilisateurs/contributeurs")
@Tag(name = "ADMIN API",
        description = "Gestion CRUD des badges")

public class ObtentionBadgeController {

    @Autowired
    private ObtentionBadgeService obtentionBadgeService;

    @Operation(summary = "pour l'ajout d'obtention de badge")
    @PostMapping("/obtentions-badge")
    public ResponseEntity<ObtentionBadge> ajouter(@RequestBody ObtentionBadge obtentionBadge) {
        ObtentionBadge saved = obtentionBadgeService.ajouter(obtentionBadge);
        return ResponseEntity.ok(saved);
    }

    @Operation(summary = "pour lister tous les badge obténu")
    @GetMapping("/obtentions-badge")
    public ResponseEntity<List<ObtentionBadge>> getAll() {
        return ResponseEntity.ok(obtentionBadgeService.chercherTous());
    }

    @Operation(summary = "pour rechercher une obtention par id")
    @GetMapping("/obtentions-badge/{id}")
    public ResponseEntity<ObtentionBadge> getById(@PathVariable int id) {
        ObtentionBadge existant = obtentionBadgeService.chercherParId(id);
        if (existant == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(existant);
    }

    @Operation(summary = "pour rechercher toutes les obtentions d'un contributeur")
    @GetMapping("/{id}/obtentions-badge")
    public ResponseEntity<List<ObtentionBadge>> getByContributeur(@PathVariable int id) {
        return ResponseEntity.ok(obtentionBadgeService.chercherParIdContri(id));
    }

    // Rechercher une obtention par badge
    @GetMapping("/obtentions-badge/badge/{id}")
    public ResponseEntity<ObtentionBadge> getByBadge(@PathVariable int id) {
        ObtentionBadge existant = obtentionBadgeService.chercherParBadge(id);
        if (existant == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(existant);
    }

    // Modifier une obtention
    @PutMapping("/obtentions-badge/{id}")
    public ResponseEntity<ObtentionBadge> modifier(@PathVariable int id, @RequestBody ObtentionBadge updated) {
        ObtentionBadge existant = obtentionBadgeService.chercherParId(id);
        if (existant == null) {
            return ResponseEntity.notFound().build();
        }

        updated.setId(id);
        return ResponseEntity.ok(obtentionBadgeService.modifier(updated));
    }

    // Supprimer une obtention
    @DeleteMapping("/obtentions-badge/{id}")
    public ResponseEntity<String> supprimer(@PathVariable int id) {
        ObtentionBadge existant = obtentionBadgeService.chercherParId(id);
        if (existant == null) {
            return ResponseEntity.notFound().build();
        }

        obtentionBadgeService.supprimerParId(id);
        return ResponseEntity.ok("Obtention supprimée avec succès.");
    }
}
