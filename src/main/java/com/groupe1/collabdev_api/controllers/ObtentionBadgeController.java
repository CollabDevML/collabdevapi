package com.groupe1.collabdev_api.controllers;

import com.groupe1.collabdev_api.entities.ObtentionBadge;
import com.groupe1.collabdev_api.services.ObtentionBadgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/obtention-badges")
public class ObtentionBadgeController {

    @Autowired
    private ObtentionBadgeService obtentionBadgeService;

    //  Ajouter une obtention (réservé au contributeur)
    @PostMapping
    public ResponseEntity<?> ajouter(@RequestHeader("Role") String role,
                                     @RequestBody ObtentionBadge obtentionBadge) {
        if (!"CONTRIBUTEUR".equalsIgnoreCase(role)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Accès refusé : seuls les contributeurs peuvent obtenir des badges.");
        }

        ObtentionBadge saved = obtentionBadgeService.ajouter(obtentionBadge);
        return ResponseEntity.ok(saved);
    }

    //  Voir ses propres obtentions (réservé au contributeur)
    @GetMapping("/contributeur/{id}")
    public ResponseEntity<?> getByContributeur(@RequestHeader("Role") String role,
                                               @RequestHeader("ContributeurId") int headerId,
                                               @PathVariable int id) {
        if (!"CONTRIBUTEUR".equalsIgnoreCase(role)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Accès refusé : seuls les contributeurs peuvent accéder à cette ressource.");
        }

        if (id != headerId) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Accès refusé : vous ne pouvez consulter que vos propres obtentions de badges.");
        }

        List<ObtentionBadge> obtentions = obtentionBadgeService.chercherParIdContri(id);
        return ResponseEntity.ok(obtentions);
    }

    //  Voir une obtention par ID (admin uniquement ou test interne)
    @GetMapping("/{id}")
    public ResponseEntity<ObtentionBadge> getById(@PathVariable int id) {
        ObtentionBadge existant = obtentionBadgeService.chercherParId(id);
        if (existant == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(existant);
    }

    //  Voir une obtention par badge (admin ou à usage spécifique)
    @GetMapping("/badge/{id}")
    public ResponseEntity<ObtentionBadge> getByBadge(@PathVariable int id) {
        ObtentionBadge existant = obtentionBadgeService.chercherParBadge(id);
        if (existant == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(existant);
    }

    //  Modifier une obtention (admin uniquement)
    @PutMapping("/{id}")
    public ResponseEntity<ObtentionBadge> modifier(@PathVariable int id,
                                                   @RequestBody ObtentionBadge updated) {
        ObtentionBadge existant = obtentionBadgeService.chercherParId(id);
        if (existant == null) {
            return ResponseEntity.notFound().build();
        }

        updated.setId(id);
        return ResponseEntity.ok(obtentionBadgeService.modifier(updated));
    }

    //  Supprimer une obtention (admin uniquement)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> supprimer(@PathVariable int id) {
        ObtentionBadge existant = obtentionBadgeService.chercherParId(id);
        if (existant == null) {
            return ResponseEntity.notFound().build();
        }

        obtentionBadgeService.supprimerParId(id);
        return ResponseEntity.ok("Obtention supprimée avec succès.");
    }

    // Voir toutes les obtentions (admin uniquement)
    @GetMapping
    public ResponseEntity<?> getAll(@RequestHeader("Role") String role) {
        if (!"ADMIN".equalsIgnoreCase(role)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Accès refusé : seuls les administrateurs peuvent voir toutes les obtentions.");
        }

        return ResponseEntity.ok(obtentionBadgeService.chercherTous());
    }

}
