package com.groupe1.collabdev_api.controllers;

import com.groupe1.collabdev_api.dto.response_dto.ResponseContributeur;
import com.groupe1.collabdev_api.dto.response_dto.ResponseGestionnaire;
import com.groupe1.collabdev_api.dto.response_dto.ResponsePorteurProjet;
import com.groupe1.collabdev_api.entities.Contributeur;
import com.groupe1.collabdev_api.entities.Gestionnaire;
import com.groupe1.collabdev_api.entities.PorteurProjet;
import com.groupe1.collabdev_api.entities.Utilisateur;
import com.groupe1.collabdev_api.entities.enums.Role;
import com.groupe1.collabdev_api.services.*;
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

    @Autowired
    private ContributeurService contributeurService;

    @Autowired
    private GestionnaireService gestionnaireService;

    @Autowired
    private PorteurProjetService porteurProjetService;

    @Autowired
    private SoutienService soutienService;

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

    @Operation(summary = "Avoir les données de l'utilisateur")
    @GetMapping("/{idUtilisateur}")
    public ResponseEntity<?> chercherParId(
            @PathVariable int idUtilisateur,
            @RequestParam Role role
    ) {
        Utilisateur utilisateur = utilisateurService.chercherParId(idUtilisateur);
        if (utilisateur == null) {
            return ResponseEntity.badRequest().body("Utilisateur introuvable avec cet id!");
        }
        switch (role) {
            case Role.CONTRIBUTEUR -> {
                try {
                    Contributeur contributeur = contributeurService.chercherIdUtilisateur(idUtilisateur);
                    return ResponseEntity.ok(
                            new ResponseContributeur(
                                    utilisateur.getId(),
                                    utilisateur.getPrenom(),
                                    utilisateur.getNom(),
                                    utilisateur.getEmail(),
                                    utilisateur.getGenre(),
                                    utilisateur.getPreferences(),
                                    contributeur.getNiveau(),
                                    contributeur.getSpecialite(),
                                    contributeur.getType(),
                                    contributeur.getPieces(),
                                    contributeur.getUriCv(),
                                    contributeur.getId()
                            )
                    );
                } catch (EntityNotFoundException e) {
                    return ResponseEntity.internalServerError().body(e.getMessage());
                }
            }
            case GESTIONNAIRE -> {
                try {
                    Gestionnaire gestionnaire = gestionnaireService.chercherParIdUtilisateur(idUtilisateur);
                    return ResponseEntity.ok(
                            new ResponseGestionnaire(
                                    utilisateur.getId(),
                                    utilisateur.getPrenom(),
                                    utilisateur.getNom(),
                                    utilisateur.getEmail(),
                                    utilisateur.getGenre(),
                                    utilisateur.getPreferences(),
                                    gestionnaire.getUriCv(),
                                    gestionnaire.isEstValide(),
                                    gestionnaire.getId()
                            )
                    );
                } catch (EntityNotFoundException e) {
                    return ResponseEntity.internalServerError().body(e);
                }
            }
            case PORTEUR_PROJET -> {
                try {
                    PorteurProjet porteurProjet = porteurProjetService.chercherIdUtilisateur(idUtilisateur);
                    return ResponseEntity.ok(
                            new ResponsePorteurProjet(
                                    utilisateur.getId(),
                                    utilisateur.getPrenom(),
                                    utilisateur.getNom(),
                                    utilisateur.getEmail(),
                                    utilisateur.getGenre(),
                                    utilisateur.getPreferences(),
                                    porteurProjet.getId()
                            )
                    );
                } catch (EntityNotFoundException e) {
                    return ResponseEntity.internalServerError().body(e);
                }
            }
            default -> {
                return ResponseEntity.badRequest().body("Non prise en charge");
            }
        }
    }

    @GetMapping("/{idUtilisateur}/idees-projet/{idIdeeProjet}/est-soutenu")
    public ResponseEntity<?> isHelped (
            @PathVariable int idUtilisateur,
            @PathVariable int idIdeeProjet
    ) {
        return new ResponseEntity<>(soutienService.isHelped(idUtilisateur, idIdeeProjet), HttpStatus.OK);
    }

}
