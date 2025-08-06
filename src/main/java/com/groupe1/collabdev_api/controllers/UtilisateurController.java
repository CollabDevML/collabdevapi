package com.groupe1.collabdev_api.controllers;

import com.groupe1.collabdev_api.services.UtilisateurService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/utilisateurs")
@Tag(name = "Utilisateurs Api",
        description = "Gestion des utilisateurs")
@CrossOrigin(origins = "http://localhost:4200")
public class UtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;

    @Operation(summary = "Modifier les préférences de l'utilisateur")
    @PutMapping("/{idUtilisateur}")
    public ResponseEntity<?> modifierLesPreferences(
            @PathVariable int idUtilisateur,
            @RequestBody List<String> preferences
    ) {
        try {
            return new ResponseEntity<>(
                    utilisateurService.modifierLesPreferences(idUtilisateur, preferences),
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
