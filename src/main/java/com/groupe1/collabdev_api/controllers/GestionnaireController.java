package com.groupe1.collabdev_api.controllers;

import com.groupe1.collabdev_api.dto.response_dto.ResponseGestionnaire;
import com.groupe1.collabdev_api.entities.Gestionnaire;
import com.groupe1.collabdev_api.services.GestionnaireService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/utilisateurs/gestionnaires")
@Tag(name = "Utilisateurs Api",
        description = "Gestionnaire")
@CrossOrigin(origins = "http://localhost:4200")
public class GestionnaireController {

    @Autowired
    private GestionnaireService gestionnaireService;

    @Operation(summary = "pour l'affichage de tous les gestionnaires'")
    @GetMapping
    public List<Gestionnaire> afficherLesGestionnaire() {
        return gestionnaireService.chercherTous();
    }

    @Operation(summary = "pour afficher un gestionnaire par son id")
    @GetMapping("/{id}")
    public Gestionnaire afficherUnGestionnaire(@PathVariable int id) {
        return gestionnaireService.chercherParId(id);
    }

    @Operation(summary = "pour la suppression d'un admis")
    @GetMapping("/est-valide/{estValide}")
    public List<ResponseGestionnaire> chercherTousParEstValide(
            @PathVariable boolean estValide
    ) {
        return gestionnaireService.chercherTousParEstValide(estValide);
    }

    @Operation(summary = "pour la validation ou le refus du compte gestionnaire ")
    @PostMapping("/{id}/est-valide/{estValide}")
    public ResponseEntity<?> validerCompteGestionnaire(
            @PathVariable int id,
            @PathVariable boolean estValide,
            @RequestParam(required = false) String cause
    ) {
        try {
            Optional<ResponseGestionnaire> gestionnaire = gestionnaireService.validerCompteGestionnaire(id, estValide, cause);
            if (gestionnaire.isEmpty()) {
                return
                        new ResponseEntity<>(
                                "La demande de création du compte gestionnaire a été réfusé!",
                                HttpStatus.OK
                        );
            }
            return
                    new ResponseEntity<>(
                            gestionnaire.get(),
                            HttpStatus.OK
                    );
        } catch (RuntimeException e) {
            return
                    new ResponseEntity<>(
                            e.getMessage(),
                            HttpStatus.BAD_REQUEST
                    );
        }
    }


    @GetMapping("/{idutilisateur}/gestionnaire")
    public Gestionnaire trouverUnGestionnaireParSonIdUtilisateur(@PathVariable int idutilisateur){
        return  gestionnaireService.chercherParIdUtilisateur(idutilisateur);
    }

}
