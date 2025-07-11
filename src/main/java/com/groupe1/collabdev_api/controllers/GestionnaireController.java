package com.groupe1.collabdev_api.controllers;

import com.groupe1.collabdev_api.entities.Gestionnaire;
import com.groupe1.collabdev_api.dto.response_dto.ResponseGestionnaire;
import com.groupe1.collabdev_api.services.GestionnaireService;
import jakarta.persistence.EntityNotFoundException;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/utilisateurs/gestionnaires")
public class GestionnaireController {

    @Autowired
    private GestionnaireService gestionnaireService;

    @GetMapping
    public List<Gestionnaire> afficherLesGestionnaire(){
        return gestionnaireService.chercherTous();
    }

    @GetMapping("/{id}")
    public Gestionnaire afficherUnGestionnaire(@PathVariable int id){
         return gestionnaireService.chercherParId(id);
    }

    @GetMapping("/est-valide/{estValide}")
    public List<ResponseGestionnaire> chercherTousParEstValide(
            @PathVariable boolean estValide
    ) {
        return gestionnaireService.chercherTousParEstValide(estValide);
    }

    @PostMapping("/{id}/est-valide/{estValide}")
    public ResponseEntity<?> validerCompteGestionnaire(
            @PathVariable int id,
            @PathVariable boolean estValide,
            @RequestParam(required = false) String cause
    ) {
        try {
            Optional<ResponseGestionnaire> gestionnaire = gestionnaireService.validerCompteGestionnaire(id, estValide, cause);
            if(gestionnaire.isEmpty()) {
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
        } catch (RuntimeException e){
            return
                    new ResponseEntity<>(
                            e.getMessage(),
                            HttpStatus.BAD_REQUEST
                    );
        }
    }

}
