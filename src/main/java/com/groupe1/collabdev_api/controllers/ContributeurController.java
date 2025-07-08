package com.groupe1.collabdev_api.controllers;

import com.groupe1.collabdev_api.dto.ContributeurDto;
import com.groupe1.collabdev_api.entities.Contributeur;
import com.groupe1.collabdev_api.services.ContributeurService;
import com.groupe1.collabdev_api.utilities.MappingContributeur;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contributeurs")
public class ContributeurController {
    ContributeurService contributeurService;
    //injection de dépendance
    public ContributeurController(ContributeurService contributeurService)
    {
        this.contributeurService = contributeurService;
    }
    @GetMapping("/{id}")
    public ContributeurDto chercherParId(@PathVariable int id)
    {
        return contributeurService.chercherContributeurParId(id);
    }

    @GetMapping
    public List<ContributeurDto> chercherTous()
    {
        return contributeurService.chercherTousLesContributeurs();
    }

    @PostMapping
    public Contributeur ajouter(@RequestBody Contributeur contributeur)
    {
        return contributeurService.ajouter(contributeur);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContributeurDto> modifier(
            @PathVariable  int id, @RequestBody ContributeurDto contributeur)

    {
        Contributeur contributeur1 = contributeurService.modifier(id,contributeur);
        ContributeurDto contributeurDto = MappingContributeur.contributeurToDto(contributeur1);
        return ResponseEntity.ok(contributeurDto);
    }

    @DeleteMapping("/{id}")
    public Boolean supprimerParId(@PathVariable int id)
    {
        return contributeurService.supprimerParId(id);
    }



}
