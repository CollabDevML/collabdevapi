package com.groupe1.collabdev_api.controllers;

import com.groupe1.collabdev_api.entities.DemandeContribution;
import com.groupe1.collabdev_api.services.DemandeContributionService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/demandes-contribution")
public class DemandeContributionController {
    public final DemandeContributionService demandeContributionService;
    public DemandeContributionController(DemandeContributionService demandeContributionService)
    {
        this.demandeContributionService = demandeContributionService;
    }

    @GetMapping("/{id}")
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

    @PutMapping("/{id}")
    public DemandeContribution modifier(
            @PathVariable int id, @RequestBody DemandeContribution demandeContribution
    )
    {
        demandeContribution.setId(id);
        return demandeContributionService.modifier(id,demandeContribution);
    }
    @DeleteMapping("/{id}")
    public Boolean supprimerParId(@PathVariable int id)
    {
        return demandeContributionService.supprimerParId(id);
    }

    @PatchMapping("/{id}/accepter")
    public DemandeContribution accepteDemande(
            @PathVariable int id
            )
    {
        return demandeContributionService.accepterDemandeContribution(id);
    }
    @PatchMapping("/{id}/refuser")
    public DemandeContribution refuserDemande(@PathVariable int id)
    {
        return demandeContributionService.refuserDemandeContribution(id);
    }
    @GetMapping("/contributeurs/{idContributeur}")
    public List<DemandeContribution> chercherParContributeur(@PathVariable int idContributeur)
    {
        return demandeContributionService.chercherParContributeur(idContributeur);
    }
    @GetMapping("/projets/{idProjet}")
    public List<DemandeContribution> chercherParProjet(@PathVariable int idProjet)
    {
        return demandeContributionService.chercherParProjet(idProjet);
    }
    //Lister les demandes acceptées ou refusées pour un contributeur dans un projet donné.
    @GetMapping("/contributeurs/{idContributeur}/projets/{idProjet}")
    public List<DemandeContribution> chercherParEstAccepte(
            @PathVariable int idContributeur,
            @PathVariable int idProjet,
            @RequestParam boolean accepte)
    {
        return demandeContributionService.chercherParEstAccepte(
                idContributeur, idProjet, accepte
        );
    }
}
