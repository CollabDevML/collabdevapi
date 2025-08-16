package com.groupe1.collabdev_api.controllers;

import com.groupe1.collabdev_api.dto.ProjetDto;
import com.groupe1.collabdev_api.dto.response_dto.ResponseProjet;
import com.groupe1.collabdev_api.entities.Projet;
import com.groupe1.collabdev_api.services.ProjetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/gestionnaires")
@Tag(name = "Projet Api",
        description = "Gestion du projet")
@CrossOrigin(origins = "http://localhost:4200")
public class ProjetController {

    @Autowired
    private ProjetService projetService;

    @Operation(summary = "pour l'ajout d'un projet'")
    @PostMapping("/projets")
    public ResponseEntity<?> ajouterProjet(@RequestBody ProjetDto projetDto) {
        try {
            return ResponseEntity.ok(projetService.ajouter(projetDto).toDto());
        } catch (RuntimeException e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @Operation(summary = "pour afficher tous les projets")
    @GetMapping("/projets")
    public List<ProjetDto> afficherToutLesProjet() {
        List<Projet> projets = projetService.chercherTous();
        List<ProjetDto> projetDtos = new ArrayList<>();
        for (Projet projet : projets) {
            projetDtos.add(projet.toDto());
        }
        return projetDtos;
    }

    @Operation(summary = "pour afficher tous les projets (version 2)")
    @GetMapping("/projets/v2")
    public List<ResponseProjet> afficherToutLesProjetVersion2() {
        List<Projet> projets = projetService.chercherTous();
        List<ResponseProjet> projetDtos = new ArrayList<>();
        for (Projet projet : projets) {
            projetDtos.add(projet.toResponse());
        }
        return projetDtos;
    }

    @Operation(summary = "pour l'affichage d'un seul projet")
    @GetMapping("/projets/{id}")
    public ProjetDto afficherUnProjet(@PathVariable int id) {
        return projetService.chercherParId(id).toDto();
    }

    @Operation(summary = "pour l'affichage d'un seul projet (visiteur)")
    @GetMapping("/projets/v2/{id}")
    public ResponseProjet afficherUnProjetVisiteur(@PathVariable int id) {
        return projetService.chercherParId(id).toResponse();
    }

    @Operation(summary = "pour l'affichage de tous les projets d'un gestionnaire'")
    @GetMapping("/{id}/projets")
    public List<ProjetDto> afficherTousLesProjetDuGestionnaire(@PathVariable int id) {
        List<ProjetDto> projetDtos = new ArrayList<>();
        List<Projet> projets = projetService.listerLesProjetParGestionnaireId(id);
        for (Projet projet : projets) {
            projetDtos.add(projet.toDto());
        }
        return projetDtos;
    }

    @Operation(summary = "pour l'affichage d'un projets des gestionnaire'")
    @GetMapping("/{id}/{id_projet}")
    public ProjetDto afficherUnProjetDuGestionnaire(@PathVariable int id, @PathVariable int id_projet) {
        return projetService.chercherUnProjetDuGestionnaire(id, id_projet).toDto();
    }

    @Operation(summary = "pour la modification du projet'")
    @PutMapping("/projets/{id}")
    public ResponseEntity<?> modifierUnProjet(
            @PathVariable int idProjet,
            @RequestBody ProjetDto projetDto,
            @RequestParam int idGestionnaire
    ) {
        try {
            return new ResponseEntity<>(
                    projetService.modifier(idProjet, idGestionnaire, projetDto).toDto(),
                    HttpStatus.OK
            );
        } catch (BadRequestException e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.FORBIDDEN
            );
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @Operation(summary = "pour la suppression d'un projet'")
    @DeleteMapping("/projets/{id}/{idprojet}")
    public boolean supprimerUnProjet(@PathVariable int id, @PathVariable int idprojet) {
        return projetService.supprimerParId(id, idprojet);
    }
}
