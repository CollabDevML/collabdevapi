package com.groupe1.collabdev_api.controllers;

import com.groupe1.collabdev_api.dto.request_dto.RequestIdeeProjet;
import com.groupe1.collabdev_api.dto.response_dto.ResponseIdeeProjet;
import com.groupe1.collabdev_api.dto.response_dto.ResponseIdeeProjet2;
import com.groupe1.collabdev_api.entities.IdeeProjet;
import com.groupe1.collabdev_api.services.IdeeProjetService;
import com.groupe1.collabdev_api.services.SoutienService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/utilisateurs")
@Tag(name = "Projet Api",
        description = "CRUD pour l'idée de projet")
@CrossOrigin(origins = "http://localhost:4200")
public class IdeeProjetController {
    @Autowired
    private IdeeProjetService ideeProjetService;

    @Autowired
    private SoutienService soutienService;

    @Operation(summary = "pour ajouter une idée de projet")
    @PostMapping("/{idPorteur}/idees-projet")
    public ResponseEntity<?> ajouterIdeeProjet(
            @PathVariable int idPorteur,
            @RequestBody RequestIdeeProjet ideeProjet
    ) {
        try {
            return new ResponseEntity<>(
                    ideeProjetService.ajouter(ideeProjet, idPorteur).toResponse(),
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
    @GetMapping("/idees-projet")
    public List<ResponseIdeeProjet> listeIdeeProjet() {
        List<IdeeProjet> ideeProjets = ideeProjetService.chercherTous();
        List<ResponseIdeeProjet> responseIdeeProjets = new ArrayList<>();
        for (IdeeProjet ideeProjet : ideeProjets) {
            responseIdeeProjets.add(ideeProjet.toResponse());
        }
        return responseIdeeProjets;
    }

    @Operation(summary = "pour lister les idées de projet (version 2)")
    @GetMapping("/idees-projet")
    public List<ResponseIdeeProjet2> listeIdeeProjetV2() {
        List<IdeeProjet> ideeProjets = ideeProjetService.chercherTous();
        List<ResponseIdeeProjet2> responseIdeeProjets = new ArrayList<>();
        for (IdeeProjet ideeProjet : ideeProjets) {
            responseIdeeProjets.add(ideeProjet.toResponse2());
        }
        return responseIdeeProjets;
    }

    @Operation(summary = "pour modifier une idée de projet")
    @PutMapping("/idees-projet/{id}")
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
    @DeleteMapping("/idees-projet/{id}")
    public Boolean supprimerIdeeProjet(@PathVariable int id) {
        return ideeProjetService.supprimerParId(id);
    }

    @Operation(summary = "pour avoir les idées de projet par id")
    @GetMapping("/idees-projet/{id}")
    public ResponseIdeeProjet chercherParId(@RequestParam int id) {
        return ideeProjetService.chercherParId(id).toResponse();
    }

    @Operation(summary = "pour avoir les idées de projet par id")
    @GetMapping("/idees-projet/v2/{id}")
    public ResponseIdeeProjet2 chercherParIdV2(@PathVariable int id) {
        return ideeProjetService.chercherParId(id).toResponse2();
    }

    @Operation(summary = "pour soutenir une idée de projet")
    @PostMapping("/idees-projet/{idIdeeProjet}/nombre-soutien")
    public ResponseEntity<?> soutenir(
            @RequestParam int idUtilisateur,
            @PathVariable int idIdeeProjet
    ) {
        try {
            return
                    new ResponseEntity<>(
                            soutienService.ajouter(idIdeeProjet, idUtilisateur),
                            HttpStatus.CREATED
                    );
        } catch (BadRequestException | EntityExistsException e) {
            return
                    new ResponseEntity<>(
                            e.getMessage(),
                            HttpStatus.BAD_REQUEST
                    );
        }
    }

    @Operation(summary = "pour soutenir une idée de projet")
    @DeleteMapping("/idees-projet/{idIdeeProjet}/nombre-soutien")
    public ResponseEntity<?> enleverSoutien(
            @RequestParam int idUtilisateur,
            @PathVariable int idIdeeProjet
    ) {
        try {
            return
                    new ResponseEntity<>(
                            soutienService.supprimer(idIdeeProjet, idUtilisateur),
                            HttpStatus.OK
                    );
        } catch (BadRequestException | EntityNotFoundException e) {
            return
                    new ResponseEntity<>(
                            e.getMessage(),
                            HttpStatus.BAD_REQUEST
                    );
        }
    }
}
