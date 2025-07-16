package com.groupe1.collabdev_api.controllers;

import com.groupe1.collabdev_api.dto.DemandeContributionDto;
import com.groupe1.collabdev_api.entities.DemandeContribution;
import com.groupe1.collabdev_api.services.DemandeContributionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/gestionnaires/projets/demandes-contribution")
@Tag(name = "Contribution Api")
public class DemandeContributionController {
    public final DemandeContributionService demandeContributionService;

    public DemandeContributionController(DemandeContributionService demandeContributionService) {
        this.demandeContributionService = demandeContributionService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> chercherParId(@PathVariable int id) {
        try {
            DemandeContributionDto demandeContributionDto = demandeContributionService.chercherParId(id);
            return ResponseEntity.ok(demandeContributionDto);
        } catch (EntityNotFoundException exception) {
            return new ResponseEntity<>(
                    exception.getMessage(),
                    HttpStatus.NOT_FOUND
            );
        } catch (RuntimeException e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }

    }

    @Operation(summary = "pour chercher les demandes de contribution")
    @GetMapping
    public List<DemandeContributionDto> chercherTous() {
        return demandeContributionService.chercherTous();
    }

    @PostMapping
    public ResponseEntity<?> ajouter(@RequestBody DemandeContribution demandeContribution) {
        try {
            return
                    new ResponseEntity<>(
                            demandeContributionService.ajouter(demandeContribution),
                            HttpStatus.CREATED
                    );
        } catch (RuntimeException e) {
            return
                    new ResponseEntity<>(
                            e.getMessage(),
                            HttpStatus.CONFLICT
                    );
        }
    }

    @Operation(summary = "modification d'un e demande de contribution")
    @PutMapping("/{id}")
    public DemandeContribution modifier(
            @PathVariable int id, @RequestBody DemandeContribution demandeContribution
    ) {
        demandeContribution.setId(id);
        return demandeContributionService.modifier(id, demandeContribution);
    }

    @Operation(summary = "pour supprimer une demande de contribution par id")
    @DeleteMapping("/{id}")
    public Boolean supprimerParId(@PathVariable int id) {
        return demandeContributionService.supprimerParId(id);
    }

    @Operation(summary = "pour accepter une demande de contribution")
    @PatchMapping("/{id}/estAcceptee/{value}")
    public ResponseEntity<?> accepteDemande(
            @PathVariable int id,
            @PathVariable boolean value
    ) {
        try {
            Optional<DemandeContribution> demandeContribution = demandeContributionService.modifierEstAcceptee(id, value);
            if (demandeContribution.isEmpty()) {
                return new ResponseEntity<>(
                        "La demande a été rejetée!",
                        HttpStatus.OK
                );
            }
            return new ResponseEntity<>(
                    demandeContribution.get().toDto(),
                    HttpStatus.OK
            );
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        } catch (RuntimeException e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.FORBIDDEN
            );
        }
    }

    @Operation(summary = "pour chercher la demande par contributeur")
    @GetMapping("/contributeurs/{idContributeur}")
    public List<DemandeContributionDto> chercherParContributeur(@PathVariable int idContributeur) {
        return demandeContributionService.chercherParContributeur(idContributeur);
    }

    @Operation(summary = "pour chercher la demande par projet")
    @GetMapping("/projets/{idProjet}")
    public List<DemandeContributionDto> chercherParProjet(@PathVariable int idProjet) {
        return demandeContributionService.chercherParProjet(idProjet);
    }

    @Operation(summary = "pour la Liste des demandes acceptés ou refusées pour un contributeur dans un projet donné")
    @GetMapping("/contributeurs/{idContributeur}/projets/{idProjet}")
    public List<DemandeContributionDto> chercherParEstAccepte(
            @PathVariable int idContributeur,
            @PathVariable int idProjet,
            @RequestParam boolean accepte) {
        return demandeContributionService.chercherParEstAccepte(
                idContributeur, idProjet, accepte
        );
    }
}
