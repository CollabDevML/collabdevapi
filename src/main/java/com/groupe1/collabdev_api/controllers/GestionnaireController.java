package com.groupe1.collabdev_api.controllers;

import com.groupe1.collabdev_api.entities.Gestionnaire;
import com.groupe1.collabdev_api.dto.response_dto.ResponseGestionnaire;
import com.groupe1.collabdev_api.services.GestionnaireService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/utilisateurs/gestionnaires")
@Tag(name = "ADMIN API",
        description = "Gestion CRUD de l'administrateurs")

public class GestionnaireController {

    @Autowired
    private GestionnaireService gestionnaireService;

    @Operation(summary = "pour l'affichage des gestionnaires")
    @GetMapping
    public List<Gestionnaire> afficherLesGestionnaire(){
        return gestionnaireService.chercherTous();
    }

    @Operation(summary = "pour afficher un seul gestionnaire")
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
    @Operation(summary = "pour la suppression d'un gestionnaire")
    @DeleteMapping("/{id}")
    public boolean SupprimerUnGestionnaire(@PathVariable int id){
        gestionnaireService.supprimerParId(id);
        return true;
    }
}
