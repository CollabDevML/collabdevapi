package com.groupe1.collabdev_api.controllers;

import com.groupe1.collabdev_api.entities.DemandeContribution;
import com.groupe1.collabdev_api.services.DemandeContributionService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class DemandeContributionController {
    public final DemandeContributionService demandeContributionService;
    public DemandeContributionController(DemandeContributionService demandeContributionService)
    {
        this.demandeContributionService = demandeContributionService;
    }

    @GetMapping("/id")
    public DemandeContribution chercherParId(@PathVariable int id)
    {
        return demandeContributionService.chercherParId(id);
    }

    @GetMapping
    public List<DemandeContribution> chercherTous()
    {
        return demandeContributionService.chercherTous();
    }

    @PostMapping
    public DemandeContribution ajouter(@RequestBody DemandeContribution demandeContribution)
    {
        return demandeContributionService.ajouter(demandeContribution);
    }

    @PutMapping("/id")
    public DemandeContribution modifier(
            @PathVariable int id, @RequestBody DemandeContribution demandeContribution
    )
    {
        demandeContribution.setId(id);
        return demandeContributionService.modifier(id,demandeContribution);
    }

    @PatchMapping
    public DemandeContribution modifier(@RequestBody DemandeContribution demandeContribution)
    {
        return demandeContributionService.modifier(demandeContribution);
    }

    @DeleteMapping("/id")
    public Boolean supprimerParId(@PathVariable int id)
    {
        return demandeContributionService.supprimerParId(id);
    }
}
