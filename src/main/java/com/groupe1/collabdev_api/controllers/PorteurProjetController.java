package com.groupe1.collabdev_api.controllers;

import com.groupe1.collabdev_api.dto.response_dto.ResponsePorteurProjet;
import com.groupe1.collabdev_api.entities.PorteurProjet;
import com.groupe1.collabdev_api.entities.Utilisateur;
import com.groupe1.collabdev_api.services.PorteurProjetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/utilisateurs/porteurs-projet")
public class PorteurProjetController {
    @Autowired
    private PorteurProjetService porteurProjetService;

    @GetMapping("/{id}")
    public ResponsePorteurProjet rechercheParId(@RequestParam int id) {
        return porteurProjetService.chercherParId(id).toResponse();
    }

    @GetMapping
    public List<ResponsePorteurProjet> listerPorteurProjet() {
        List<PorteurProjet> porteurProjets = porteurProjetService.chercherTous();
        List<ResponsePorteurProjet> responsePorteurProjets = new ArrayList<>();
        for (PorteurProjet porteurProjet : porteurProjets) {
            responsePorteurProjets.add(porteurProjet.toResponse());
        }
        return responsePorteurProjets;
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponsePorteurProjet> modifierParId(@PathVariable int id, @RequestBody Utilisateur user) {
        PorteurProjet porteurProjet = porteurProjetService.modifier(id, user);
        return ResponseEntity.ok(porteurProjet.toResponse());
    }

    @DeleteMapping("/{id}")
    public Boolean supprimerParId(@PathVariable int id) {
        return porteurProjetService.supprimerParId(id);
    }
}
