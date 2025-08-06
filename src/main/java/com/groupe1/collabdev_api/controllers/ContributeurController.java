package com.groupe1.collabdev_api.controllers;

import com.groupe1.collabdev_api.dto.ContributeurDto;
import com.groupe1.collabdev_api.dto.ProjetDto;
import com.groupe1.collabdev_api.entities.Contributeur;
import com.groupe1.collabdev_api.services.ContributeurService;
import com.groupe1.collabdev_api.utilities.MappingContributeur;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/utilisateurs/contributeurs")
@Tag(name = "Utilisateurs Api",
        description = "gestion du contributeur")
@CrossOrigin(origins = "http://localhost:4200")
public class ContributeurController {
    ContributeurService contributeurService;

    //injection de dépendance
    public ContributeurController(ContributeurService contributeurService) {
        this.contributeurService = contributeurService;
    }

    @Operation(summary = "pour avoir un contributeur par son id")
    @GetMapping("/{id}")
    public ResponseEntity<?> chercherParId(@PathVariable int id) {
        try {
            ContributeurDto contribituerDto = contributeurService.chercherContributeurParId(id);
            return ResponseEntity.ok(contribituerDto);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.NOT_FOUND
            );
        } catch (RuntimeException e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }

    }

    @Operation(summary = "pour chercher tous les contributeurs")
    @GetMapping
    public List<ContributeurDto> chercherTous() {
        return contributeurService.chercherTousLesContributeurs();
    }

    @Operation(summary = "pour modifier un contributeur")
    @PutMapping("/{id}")
    public ResponseEntity<?> modifier(
            @PathVariable int id, @RequestBody ContributeurDto contributeur) {
        try {
            Contributeur contributeur1 = contributeurService.modifier(id, contributeur);
            ContributeurDto contributeurDto = MappingContributeur.contributeurToDto(contributeur1);
            return ResponseEntity.ok(contributeurDto);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.NOT_FOUND
            );
        } catch (RuntimeException e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }

    }

    @Operation(summary = "pour supprimer un contributeur")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> supprimerParId(@PathVariable int id) {
        try {
            return new ResponseEntity<>(
                    contributeurService.supprimerParId(id),
                    HttpStatus.OK
            );
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.NOT_FOUND
            );
        } catch (RuntimeException e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }

    }

    @GetMapping("/projets/{idContributeur}")
    public List<ProjetDto> chercherProjetsParContributeur(@PathVariable int idContributeur) {
        return contributeurService.chercherProjetsParContributeur(idContributeur);
    }

    @Operation(summary = "pour qu'un contributeur quitte le projet")
    @DeleteMapping("{idContributeur}/projets/{idProjet}")
    public ResponseEntity<?> quitterProjet(@PathVariable int idContributeur,
                                           @PathVariable int idProjet) {

        int i = contributeurService.quitterUnProjet(idContributeur, idProjet);
        return i >= 1 ? ResponseEntity.ok("Suppression effectuée") : new ResponseEntity<>(
                "id contributeur ou id projet invalide",
                HttpStatus.NOT_FOUND
        );

    }


}
