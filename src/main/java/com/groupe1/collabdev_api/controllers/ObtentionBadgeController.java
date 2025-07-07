package com.groupe1.collabdev_api.controllers;

import com.groupe1.collabdev_api.entities.ObtentionBadge;
import com.groupe1.collabdev_api.services.ObtentionBadgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/obtention-badges")
public class ObtentionBadgeController {

    @Autowired
    private ObtentionBadgeService obtentionBadgeService;

    // Ajouter une obtention de badge
    @PostMapping
    public ResponseEntity<ObtentionBadge> ajouter(@RequestBody ObtentionBadge obtentionBadge) {
        ObtentionBadge saved = obtentionBadgeService.ajouter(obtentionBadge);
        return ResponseEntity.ok(saved);
    }

    //  Lister toutes les obtentions
    @GetMapping
    public ResponseEntity<List<ObtentionBadge>> getAll() {
        return ResponseEntity.ok(obtentionBadgeService.chercherTous());
    }

    // Rechercher une obtention par ID
    @GetMapping("/{id}")
    public ResponseEntity<ObtentionBadge> getById(@PathVariable int id) {
        ObtentionBadge existant = obtentionBadgeService.chercherParId(id);
        if (existant == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(existant);
    }

    // Rechercher toutes les obtentions d’un contributeur
    @GetMapping("/contributeur/{id}")
    public ResponseEntity<List<ObtentionBadge>> getByContributeur(@PathVariable int id) {
        return ResponseEntity.ok(obtentionBadgeService.chercherParIdContri(id));
    }

    // Rechercher une obtention par badge
    @GetMapping("/badge/{id}")
    public ResponseEntity<ObtentionBadge> getByBadge(@PathVariable int id) {
        ObtentionBadge existant = obtentionBadgeService.chercherParBadge(id);
        if (existant == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(existant);
    }

    // Modifier une obtention
    @PutMapping("/{id}")
    public ResponseEntity<ObtentionBadge> modifier(@PathVariable int id, @RequestBody ObtentionBadge updated) {
        ObtentionBadge existant = obtentionBadgeService.chercherParId(id);
        if (existant == null) {
            return ResponseEntity.notFound().build();
        }

        updated.setId(id);
        return ResponseEntity.ok(obtentionBadgeService.modifier(updated));
    }

    // Supprimer une obtention
    @DeleteMapping("/{id}")
    public ResponseEntity<String> supprimer(@PathVariable int id) {
        ObtentionBadge existant = obtentionBadgeService.chercherParId(id);
        if (existant == null) {
            return ResponseEntity.notFound().build();
        }

        obtentionBadgeService.supprimerParId(id);
        return ResponseEntity.ok("Obtention supprimée avec succès.");
    }
}
