package com.groupe1.collabdev_api.controllers;

import com.groupe1.collabdev_api.entities.Contributeur;
import com.groupe1.collabdev_api.services.ContributeurService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/utilisateurs/contributeurs")
public class ContributeurController {
    ContributeurService contributeurService;
    //injection de dépendance
    public ContributeurController(ContributeurService contributeurService)
    {
        this.contributeurService = contributeurService;
    }
    @GetMapping("/{id}")
    public Contributeur chercherParId(@PathVariable int id)
    {
        return contributeurService.chercherParId(id);
    }
    @GetMapping
    public List<Contributeur> chercherTous()
    {
        return contributeurService.chercherTous();
    }
    @PostMapping
    public Contributeur ajouter(@RequestBody Contributeur contributeur)
    {
        return contributeurService.ajouter(contributeur);
    }
    @PutMapping("/{id}")
    public Contributeur modifier(
            @PathVariable  int id, @RequestBody Contributeur contributeur)
    {
        return contributeurService.modifier(id, contributeur);
    }
    @DeleteMapping("/{id}")
    public Boolean supprimerParId(@PathVariable int id)
    {
        return contributeurService.supprimerParId(id);
    }
    //modifier un attribut
    @PatchMapping("/{id}")
    public Contributeur modifierAttribut(
            @PathVariable int id, @RequestBody Contributeur contributeur
    )
    {
        return contributeurService.modifier(id, contributeur);
    }

}
