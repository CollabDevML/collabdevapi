package com.groupe1.collabdev_api.controllers;

import com.groupe1.collabdev_api.dto.request_dto.RequestTache;
import com.groupe1.collabdev_api.entities.Tache;
import com.groupe1.collabdev_api.exceptions.ProjectNotFoundException;
import com.groupe1.collabdev_api.exceptions.TacheNotFoundException;
import com.groupe1.collabdev_api.exceptions.UserNotFoundException;
import com.groupe1.collabdev_api.services.TacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/utilisateurs/gestionnaires/projets/taches")
public class TacheController {

    @Autowired
    private TacheService tacheService;

    @PostMapping
    public ResponseEntity<?> ajouterUneTache(
            @RequestBody RequestTache requestTache
    ) {
        try {
            return
                    new ResponseEntity<>(
                            tacheService.ajouter(requestTache),
                            HttpStatus.CREATED
                    );
        } catch (UserNotFoundException | ProjectNotFoundException e) {
            return
                    new ResponseEntity<>(
                            e.getMessage(),
                            HttpStatus.NOT_FOUND
                    );
        } catch (RuntimeException e) {
            return
                    new ResponseEntity<>(
                            e.getMessage(),
                            HttpStatus.INTERNAL_SERVER_ERROR
                    );
        }
    }

    @GetMapping
    public List<Tache> afficherTousLesTache(@RequestParam int projetId) {
        return tacheService.chercherTous(projetId);
    }

    @GetMapping("{tacheId}")
    public Tache afficherUneTache(@RequestParam int projetId, @PathVariable int tacheId) {
        return tacheService.chercherParId(projetId, tacheId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> finirUneTache(
            @PathVariable int id
    ) {
        try {
            return
                    new ResponseEntity<>(
                            tacheService.finirUneTache(id),
                            HttpStatus.CREATED
                    );
        } catch (RuntimeException e) {
            return
                    new ResponseEntity<>(
                            e.getMessage(),
                            HttpStatus.INTERNAL_SERVER_ERROR
                    );
        } catch (TacheNotFoundException e) {
            return
                    new ResponseEntity<>(
                            e.getMessage(),
                            HttpStatus.NOT_FOUND
                    );
        }
    }

    @DeleteMapping
    public boolean supprimerUneTache(@RequestParam int idTache, @RequestParam int idGestionnaire) {
        return tacheService.supprimerParId(idTache, idGestionnaire);
    }
}
