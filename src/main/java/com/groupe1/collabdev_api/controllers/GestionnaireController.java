package com.groupe1.collabdev_api.controllers;

import com.groupe1.collabdev_api.entities.Gestionnaire;
import com.groupe1.collabdev_api.repositories.GestionnaireRepository;
import com.groupe1.collabdev_api.services.GestionnaireService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gestionnaire")
@RequiredArgsConstructor
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

    @DeleteMapping("/{id}")
    public boolean SupprimerUnGestionnaire(@PathVariable int id){
        gestionnaireService.supprimerParId(id);
        return true;
    }
}
