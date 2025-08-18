package com.groupe1.collabdev_api.controllers;

import com.groupe1.collabdev_api.dto.DemandeContributionDto;
import com.groupe1.collabdev_api.dto.request_dto.RequestDemandeContribution;
import com.groupe1.collabdev_api.dto.response_dto.ResponseDemandeContribution;
import com.groupe1.collabdev_api.entities.DemandeContribution;
import com.groupe1.collabdev_api.exceptions.NotHaveEnoughLevelException;
import com.groupe1.collabdev_api.services.DemandeContributionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.apache.coyote.BadRequestException;
import org.hibernate.mapping.Any;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/gestionnaires/projets")
@Tag(name = "Contribution Api")
@CrossOrigin(origins = "http://localhost:4200")
public class DemandeContributionController {
    public final DemandeContributionService demandeContributionService;

    public DemandeContributionController(DemandeContributionService demandeContributionService) {
        this.demandeContributionService = demandeContributionService;
    }

    @GetMapping("/demandes-contribution/{id}")
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
    @GetMapping("/demandes-contribution")
    public List<DemandeContributionDto> chercherTous() {
        return demandeContributionService.chercherTous();
    }

    @PostMapping("/demandes-contribution")
    public ResponseEntity<?> ajouter(@RequestBody RequestDemandeContribution requestDemandeContribution) {
        try {
            return
                    new ResponseEntity<>(
                            demandeContributionService.ajouter(requestDemandeContribution).toDto(),
                            HttpStatus.CREATED
                    );
        } catch (EntityExistsException e) {
            return
                    new ResponseEntity<>(
                            e.getMessage(),
                            HttpStatus.CONFLICT
                    );
        } catch (NotHaveEnoughLevelException e) {
            return
                    new ResponseEntity<>(
                            e.getMessage(),
                            HttpStatus.UNAUTHORIZED
                    );
        } catch (EntityNotFoundException e) {
            return
                    new ResponseEntity<>(
                            e.getMessage(),
                            HttpStatus.BAD_REQUEST
                    );
        }
    }

    @Operation(summary = "pour chercher les demandes de contributions d'un projet")
    @GetMapping("/{idProjet}/demandes-contribution/v2")
    public ResponseEntity<List<ResponseDemandeContribution>> chercherParIdProjet(
            @PathVariable int idProjet
    ) {
        return ResponseEntity.ok(demandeContributionService.chercherParProjet2(idProjet));
    }

    @Operation(summary = "modification d'une demande de contribution")
    @PutMapping("/demandes-contribution/{id}")
    public DemandeContribution modifier(
            @PathVariable int id, @RequestBody DemandeContribution demandeContribution
    ) {
        demandeContribution.setId(id);
        return demandeContributionService.modifier(id, demandeContribution);
    }

    @Operation(summary = "pour supprimer une demande de contribution par id")
    @DeleteMapping("/demandes-contribution/{id}")
    public Boolean supprimerParId(@PathVariable int id) {
        return demandeContributionService.supprimerParId(id);
    }

    @Operation(summary = "pour accepter une demande de contribution")
    @PatchMapping("/demandes-contribution/{id}/estAcceptee/{value}")
    public ResponseEntity<?> accepteDemande(
            @PathVariable int id,
            @PathVariable boolean value
    ) {
        try {
            Optional<DemandeContribution> demandeContribution = demandeContributionService.modifierEstAcceptee(id, value);
            if (demandeContribution.isEmpty()) {
                HashMap<String, Object> response = new HashMap<>();
                response.put("code", HttpStatus.BAD_REQUEST.value());
                response.put("message", "La demande a été rejetée");
                response.put("data", null);
                return new ResponseEntity<>(
                        response,
                        HttpStatus.OK
                );
            }
            HashMap<String, Object> response = new HashMap<>();
            response.put("code", HttpStatus.OK.value());
            response.put("message", "La demande a été acceptée");
            response.put("data", demandeContribution.get().toResponse());
            return new ResponseEntity<>(
                    response,
                    HttpStatus.OK
            );
        } catch (EntityNotFoundException | BadRequestException e) {
            HashMap<String, Object> response = new HashMap<>();
            response.put("code", HttpStatus.BAD_REQUEST.value());
            response.put("message", e.getMessage());
            response.put("data", null);
            return new ResponseEntity<>(
                    response,
                    HttpStatus.OK
            );
        }
    }

    @Operation(summary = "pour chercher la demande par contributeur")
    @GetMapping("/demandes-contribution/contributeurs/{idContributeur}")
    public List<DemandeContributionDto> chercherParContributeur(@PathVariable int idContributeur) {
        return demandeContributionService.chercherParContributeur(idContributeur);
    }

    @Operation(summary = "pour chercher la demande par projet")
    @GetMapping("/{idProjet}/demandes-contribution")
    public List<DemandeContributionDto> chercherParProjet(@PathVariable int idProjet) {
        return demandeContributionService.chercherParProjet(idProjet);
    }

    @Operation(summary = "pour la Liste des demandes acceptés ou refusées pour un contributeur dans un projet donné")
    @GetMapping("/{idProjet}/demandes-contribution/contributeurs/{idContributeur}")
    public List<DemandeContributionDto> chercherParEstAccepte(
            @PathVariable int idContributeur,
            @PathVariable int idProjet,
            @RequestParam boolean accepte) {
        return demandeContributionService.chercherParEstAccepte(
                idContributeur, idProjet, accepte
        );
    }
}
