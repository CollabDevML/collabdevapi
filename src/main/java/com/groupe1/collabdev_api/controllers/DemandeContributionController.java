package com.groupe1.collabdev_api.controllers;

import com.groupe1.collabdev_api.dto.DemandeContributionDto;
import com.groupe1.collabdev_api.entities.DemandeContribution;
import com.groupe1.collabdev_api.services.DemandeContributionService;
import com.groupe1.collabdev_api.utilities.MappingDemandeContribution;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/gestionnaires/projets/demandes-contribution")
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

    @GetMapping
    public List<DemandeContributionDto> chercherTous() {
        return demandeContributionService.chercherTous();
    }

    @PostMapping
    public ResponseEntity<?> ajouter(@RequestBody DemandeContribution demandeContribution) {
        try{
            return
                    new ResponseEntity<>(
                            demandeContributionService.ajouter(demandeContribution),
                            HttpStatus.CREATED
                    );
        } catch (RuntimeException e){
            return
                    new ResponseEntity<>(
                            e.getMessage(),
                            HttpStatus.CONFLICT
                    );
        }
    }

    @PutMapping("/{id}")
    public DemandeContribution modifier(
            @PathVariable int id, @RequestBody DemandeContribution demandeContribution
    ) {
        demandeContribution.setId(id);
        return demandeContributionService.modifier(id, demandeContribution);
    }

    @DeleteMapping("/{id}")
    public Boolean supprimerParId(@PathVariable int id) {
        return demandeContributionService.supprimerParId(id);
    }

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

    @GetMapping("/contributeurs/{idContributeur}")
    public List<DemandeContributionDto> chercherParContributeur(@PathVariable int idContributeur) {
        return demandeContributionService.chercherParContributeur(idContributeur);
    }

    @GetMapping("/projets/{idProjet}")
    public List<DemandeContributionDto> chercherParProjet(@PathVariable int idProjet) {
        return demandeContributionService.chercherParProjet(idProjet);
    }

    //Lister les demandes acceptées ou refusées pour un contributeur dans un projet donné.
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
