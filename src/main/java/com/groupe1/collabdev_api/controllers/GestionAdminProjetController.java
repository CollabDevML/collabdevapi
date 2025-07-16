package com.groupe1.collabdev_api.controllers;

import com.groupe1.collabdev_api.dto.ProjetDto;
import com.groupe1.collabdev_api.entities.Projet;
import com.groupe1.collabdev_api.services.GestionAdminProjetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/administrateurs/gestion/projets/")
@Tag(name="Admin Api",
        description="Autorisation du gestionnaire")
public class GestionAdminProjetController {
    @Autowired
    private GestionAdminProjetService gestionAdminProjetService;

    @Operation(summary = "pour la recuperation des projets")
    @GetMapping
    public List<ProjetDto> getAllProjets() {
        List<Projet> projets = gestionAdminProjetService.afficherListeProjet();
        List<ProjetDto> projetList = new ArrayList<>();
        for (Projet projet : projets) {
            projetList.add(projet.toDto());
        }
        return projetList;
    }

    @Operation(summary = "pour activer un projet")
    @GetMapping("{id}/activer")
    public ProjetDto actviveProjet(@PathVariable int id, @RequestParam("idAdmin") int idA) {
        return gestionAdminProjetService.activerProjet(id, idA).toDto();
    }

    @Operation(summary = "pour désactiver un projet")
    @GetMapping("{id}/desactiver")
    public ProjetDto desactiverProjet(@PathVariable int id, @RequestParam("idAdmin") int idA) {
        return gestionAdminProjetService.desactiverProjet(id, idA).toDto();
    }

    @Operation(summary = "pour supprimer un projet")//Pour supprimer un projet :
    @DeleteMapping("{id}")
    public boolean deleteProjet(@PathVariable int id,
                                @RequestParam("idAdmin") int idAdmin) {
        return gestionAdminProjetService.supprimerParId(id, idAdmin);
    }
}
