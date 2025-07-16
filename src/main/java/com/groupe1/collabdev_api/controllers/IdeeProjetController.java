package com.groupe1.collabdev_api.controllers;

import com.groupe1.collabdev_api.dto.request_dto.RequestIdeeProjet;
import com.groupe1.collabdev_api.dto.response_dto.ResponseIdeeProjet;
import com.groupe1.collabdev_api.entities.IdeeProjet;
import com.groupe1.collabdev_api.services.IdeeProjetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/utilisateurs/idees-projet")
@Tag(name = "Projet Api",
        description = "CRUD pour l'idée de projet")
public class IdeeProjetController {
    @Autowired
    private IdeeProjetService ideeProjetService;

    @Operation(summary = "pour ajouter une idée de projet")
    @PostMapping
    public ResponseEntity<?> ajouterIdeeProjet(
            @RequestParam int idPorteur,
            @RequestBody RequestIdeeProjet ideeProjet
    ) {
        ideeProjet.setNombreSoutien(0);
        try {
            return new ResponseEntity<>(
                    ideeProjetService.ajouter(ideeProjet).toResponse(),
                    HttpStatus.CREATED
            );
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @Operation(summary = "pour lister les idées de projet")
    @GetMapping
    public List<ResponseIdeeProjet> listeIdeeProjet() {
        List<IdeeProjet> ideeProjets = ideeProjetService.chercherTous();
        List<ResponseIdeeProjet> responseIdeeProjets = new ArrayList<>();
        for (IdeeProjet ideeProjet : ideeProjets) {
            responseIdeeProjets.add(ideeProjet.toResponse());
        }
        return responseIdeeProjets;
    }

    @Operation(summary = "pour modifier une idée de projet")
    @PutMapping("/{id}")
    public ResponseEntity<?> modifierIdeeProjet(
            @PathVariable int id,
            @RequestBody RequestIdeeProjet requestIdeeProjet
    ) {
        try {
            return new ResponseEntity<>(
                    ideeProjetService.modifier(id, requestIdeeProjet).toResponse(),
                    HttpStatus.OK
            );
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @Operation(summary = "pour la suppression d'une idée de projet")
    @DeleteMapping("/{id}")
    public Boolean supprimerIdeeProjet(@PathVariable int id) {
        return ideeProjetService.supprimerParId(id);
    }

    @Operation(summary = "pour avoir les idées de projet par id")
    @GetMapping("/{id}")
    public ResponseIdeeProjet chercherParId(@RequestParam int id) {
        return ideeProjetService.chercherParId(id).toResponse();
    }

    @Operation(summary = "pour soutenir une idée de projet")
    @PutMapping("/nombre-soutien/{id}")
    public ResponseIdeeProjet soutenir(@PathVariable int id) {
        return ideeProjetService.soutenirIdeeProjet(id).toResponse();
    }
}
