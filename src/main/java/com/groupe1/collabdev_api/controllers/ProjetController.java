package com.groupe1.collabdev_api.controllers;

import com.groupe1.collabdev_api.dto.ProjetDto;
import com.groupe1.collabdev_api.entities.Projet;
import com.groupe1.collabdev_api.services.ProjetService;
import jakarta.persistence.EntityNotFoundException;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/gestionnaires")
public class ProjetController {

    @Autowired
    private ProjetService projetService;

    @PostMapping("/projets")
    public ProjetDto ajouterProjet(@RequestBody ProjetDto projetDto) {
        return projetService.ajouter(projetDto).toDto();
    }

    @GetMapping("/projets")
    public List<ProjetDto> afficherToutLesProjet() {
        List<Projet> projets = projetService.chercherTous();
        List<ProjetDto> projetDtos = new ArrayList<>();
        for (Projet projet : projets) {
            projetDtos.add(projet.toDto());
        }
        return projetDtos;
    }

    @GetMapping("/projets/{id}")
    public ProjetDto afficherUnProjet(@PathVariable int id) {
        return projetService.chercherParId(id).toDto();
    }

    @GetMapping("/{id}/projets")
    public List<ProjetDto> afficherTousLesProjetDuGestionnaire(@PathVariable int id) {
        List<ProjetDto> projetDtos = new ArrayList<>();
        List<Projet> projets = projetService.listerLesProjetParGestionnaireId(id);
        for (Projet projet : projets) {
            projetDtos.add(projet.toDto());
        }
        return projetDtos;
    }

    @GetMapping("/{id}/{id_projet}")
    public ProjetDto afficherUnProjetDuGestionnaire(@PathVariable int id, @PathVariable int id_projet) {
        return projetService.chercherUnProjetDuGestionnaire(id, id_projet).toDto();
    }

    @PutMapping("/projets/{id}")
    public ResponseEntity<?> modifierUnProjet(
            @PathVariable int idProjet,
            @RequestBody ProjetDto projetDto,
            @RequestParam int idGestionnaire
            ) {
        try{
            return new ResponseEntity<>(
                    projetService.modifier(idProjet, idGestionnaire, projetDto).toDto(),
                    HttpStatus.OK
            );
        } catch (BadRequestException e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.FORBIDDEN
            );
        } catch (EntityNotFoundException e){
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @DeleteMapping("/projets/{id}/{idprojet}")
    public boolean supprimerUnProjet(@PathVariable int id, @PathVariable int idprojet) {
        return projetService.supprimerParId(id, idprojet);
    }
}
