package com.groupe1.collabdev_api.controllers;

import com.groupe1.collabdev_api.entities.Projet;
import com.groupe1.collabdev_api.services.ProjetService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projets")
@RequiredArgsConstructor
public class ProjetController {

    private ProjetService projetService;

    @Autowired
    public ProjetController(ProjetService projetService) {
        this.projetService = projetService;
    }

    @PostMapping
    public Projet ajouterProjet(@RequestParam int id, @RequestBody Projet projet){
        return projetService.ajouter(id,projet);
    }

    @GetMapping
    public List<Projet> afficherToutLesProjet(){
        return  projetService.chercherTous();
    }

    @GetMapping("/{id}")
    public Projet afficherUnProjet(@PathVariable int id){

        return projetService.chercherParId(id);
    }
    @GetMapping("/GestionnaireProjets/{id}")
    public List<Projet> affichertousLesProjetDuGestionnaire(@PathVariable int id){
        return projetService.listerLesProjetParGestionnaireId(id);
    }
    @GetMapping("/GestionnaireProjet/{id}/{id_projet}")
    public Projet afficherUnProjetDuGestionnaire(@PathVariable int id, @PathVariable int id_projet){
        return projetService.chercherUnProjetDuGestionnaire(id,id_projet);
    }

    @PutMapping("/{id}")
    public Projet modifierUnProjet(@PathVariable int id, @RequestBody Projet projet){
        return projetService.modifier(id,projet);
    }

    @DeleteMapping("/{id}/{idprojet}")
    public boolean supprimerUnProjet(@PathVariable int id, @PathVariable int idprojet){
        return projetService.supprimerParId(id,idprojet);
    }

}
