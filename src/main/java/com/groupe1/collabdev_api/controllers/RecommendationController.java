package com.groupe1.collabdev_api.controllers;

import com.groupe1.collabdev_api.services.RecommendationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recommandations")
@Tag(name = "Utilisateurs Api",
        description = "Recommandations de projets et d'idées de projet pour les utilisateurs")
@CrossOrigin(origins = "http://localhost:4200")
public class RecommendationController {

    @Autowired
    private RecommendationService recommendationService;

    @Operation(summary = "pour obtention les récommandations d'idées de projet d'un utilisateur")
    @GetMapping("/idees-projet/{idUtilisateur}")
    public ResponseEntity<?> getRecommendedIdeas(
            @PathVariable int idUtilisateur
    ) {
        try {
            return new ResponseEntity<>(
                    recommendationService.getRecommendedIdeas(idUtilisateur),
                    HttpStatus.OK
            );
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }
}
