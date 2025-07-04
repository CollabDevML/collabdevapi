package com.groupe1.collabdev_api.controllers;

import com.groupe1.collabdev_api.entities.Contribution;
import com.groupe1.collabdev_api.services.ContributionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contributions")
public class ContributionController {
    ContributionService contributionService;
    public ContributionController(ContributionService contributionService)
    {
        this.contributionService = contributionService;
    }
    @GetMapping("/id")
    public Contribution chercherParId(@PathVariable int id)
    {
        return contributionService.chercherParId(id);
    }
    @GetMapping
    public List<Contribution> chercherTous()
    {
        return contributionService.chercherTous();
    }
    @PutMapping("/id")
    public Contribution modifier(@PathVariable int id, @RequestBody Contribution contribution)
    {
        return contributionService.modifier(id, contribution);
    }
    @PostMapping
    public Contribution ajouter(@RequestBody Contribution contribution)
    {
        return contributionService.ajouter(contribution);
    }
    @DeleteMapping
    public Boolean supprimerById(@PathVariable int id)
    {
        return contributionService.supprimerParId(id);
    }
    @GetMapping
    public List<Contribution> chercherParContributeurId(@RequestParam int idContributeur)
    {
        return contributionService.chercherParContributeurId(idContributeur);
    }
    @GetMapping
    public List<Contribution> chercherParProjetId(@RequestParam int idProjet)
    {
        return contributionService.chercherParProjetId(idProjet);
    }
    @GetMapping
    public List<Contribution> chercherContributionValide(
            @PathVariable int idContributeur
            ,@RequestParam boolean valide)
    {
        return contributionService.chercherContributionValide(idContributeur, valide);
    }
}
