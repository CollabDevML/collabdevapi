package com.groupe1.collabdev_api.controllers;

import com.groupe1.collabdev_api.controllers.response_entities.Commentaire;
import com.groupe1.collabdev_api.entities.CommentaireProjet;
import com.groupe1.collabdev_api.repositories.CommentaireProjetRepository;
import com.groupe1.collabdev_api.services.CommentaireProjetService;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/CommentaireProjet")

public class CommentaireProjetController {
    @Autowired
    private CommentaireProjetService commentaireProjetService;
    private CommentaireProjetRepository commentaireProjetRepository;

    @PostMapping

    public CommentaireProjet ajoutercommentaire(@RequestBody CommentaireProjet commentaire){
        return  commentaireProjetService.ajouter(commentaire);
    }

    @GetMapping
    public List<CommentaireProjet> Listecommentaire(){
        return commentaireProjetService.chercherTous();
    }




    @GetMapping("/{id}")

    public CommentaireProjet chercherparid(@PathVariable Integer id ){
        return commentaireProjetService.chercherParId(id);
    }

    @DeleteMapping("/{id}")

    public boolean supprimercommentaire(@PathVariable Integer id){
        return commentaireProjetService.supprimerParId(id);
    }

    @PutMapping("/{id}")

    public CommentaireProjet modifier(@PathVariable Integer id,@RequestBody CommentaireProjet commentaireProjet) {
        CommentaireProjet comexiste = commentaireProjetRepository.findById(id).orElseThrow(
                () -> new RuntimeException("commentaire introuvable"));

        comexiste.setDateCommentaire(commentaireProjet.getDateCommentaire());
        comexiste.setProjet(commentaireProjet.getProjet());
        comexiste.setContenu(commentaireProjet.getContenu());
        comexiste.setUtilisateur(commentaireProjet.getUtilisateur());
        return commentaireProjetRepository.save(comexiste);

    }

    @PatchMapping("/{id}")

    public CommentaireProjet PratModification(@PathVariable Integer id,@RequestBody Commentaire commentaireProjet) {
        return commentaireProjetService.modifier(id,commentaireProjet);
    }




}
