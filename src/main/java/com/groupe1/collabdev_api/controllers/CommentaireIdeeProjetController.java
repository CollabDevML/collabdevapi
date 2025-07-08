package com.groupe1.collabdev_api.controllers;

import com.groupe1.collabdev_api.controllers.response_entities.Commentaire;
import com.groupe1.collabdev_api.entities.CommentaireIdeeProjet;
import com.groupe1.collabdev_api.entities.CommentaireProjet;
import com.groupe1.collabdev_api.services.CommentaireIdeeProjetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/CommentaireideeProjet")


public class CommentaireIdeeProjetController {
    @Autowired
    private CommentaireIdeeProjetService commentaireIdeeProjetService;

    @PostMapping

    public CommentaireIdeeProjet ajoutercommentaire(@RequestBody CommentaireIdeeProjet commentaire){
        return commentaireIdeeProjetService.ajouter(commentaire);
    }

    @GetMapping

    public List<CommentaireIdeeProjet> listecommentaire(){
        return commentaireIdeeProjetService.chercherTous();
    }
    @GetMapping("/{id}")

    public CommentaireIdeeProjet chercherparid(@PathVariable Integer id){
        return commentaireIdeeProjetService.chercherParId(id);
    }

    @DeleteMapping("/{id}")

    public boolean supprimercommentaire(@PathVariable Integer id){
        return commentaireIdeeProjetService.supprimerParId(id);
    }

    @PatchMapping("/{id}")

    public CommentaireIdeeProjet PratModification(@PathVariable Integer id, @RequestBody Commentaire commentaireProjet) {
        return commentaireIdeeProjetService.modifier(id,commentaireProjet);
    }


}
