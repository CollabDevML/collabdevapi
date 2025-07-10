package com.groupe1.collabdev_api.controllers;

import com.groupe1.collabdev_api.dto.ContributeurDto;
import com.groupe1.collabdev_api.entities.Contributeur;
import com.groupe1.collabdev_api.services.ContributeurService;
import com.groupe1.collabdev_api.utilities.MappingContributeur;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/utilisateurs/contributeurs")
public class ContributeurController {
    ContributeurService contributeurService;

    //injection de dépendance
    public ContributeurController(ContributeurService contributeurService) {
        this.contributeurService = contributeurService;
    }

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

    @GetMapping
    public List<ContributeurDto> chercherTous() {
        return contributeurService.chercherTousLesContributeurs();
    }

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

    @DeleteMapping("{idContributeur}/projets/{idProjet}")
    public Boolean quitterProjet(@PathVariable int idContributeur,
                                 @PathVariable int idProjet){

        return contributeurService.quitterUnProjet(idContributeur, idProjet);

    }


}
