package com.groupe1.collabdev_api.controllers;

import com.groupe1.collabdev_api.entities.Gestionnaire;
import com.groupe1.collabdev_api.dto.response_dto.ResponseGestionnaire;
import com.groupe1.collabdev_api.services.GestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @DeleteMapping("/{id}")
    public boolean SupprimerUnGestionnaire(@PathVariable int id){
        gestionnaireService.supprimerParId(id);
        return true;
    }
}
