package com.groupe1.collabdev_api.controllers;

import com.groupe1.collabdev_api.dto.ContributionDto;
import com.groupe1.collabdev_api.entities.Contribution;
import com.groupe1.collabdev_api.entities.Projet;
import com.groupe1.collabdev_api.services.ContributionService;
import com.groupe1.collabdev_api.utilities.MappingContribution;
import org.springframework.http.ResponseEntity;
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
    @GetMapping("/{id}")
    public ContributionDto chercherParId(@PathVariable int id)
    {
        return contributionService.chercherParId(id);
    }
    @GetMapping
    public List<ContributionDto> chercherTous()
    {
        return contributionService.chercherTous();
    }
    @PostMapping
    public Contribution ajouter(@RequestBody Contribution contribution)
    {
        return contributionService.ajouter(contribution);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<ContributionDto> modifierContribution(
            @PathVariable int id,
            @RequestBody ContributionDto dto) {
        ContributionDto modifieDto = contributionService.modifier(id, dto);

        return ResponseEntity.ok(modifieDto);
    }


    @DeleteMapping("/{id}")
    public Boolean supprimerById(@PathVariable int id)
    {
        return contributionService.supprimerParId(id);
    }
    @GetMapping("/contributeur/{idContributeur}")
    public List<ContributionDto> chercherParContributeurId(@PathVariable int idContributeur)
    {
        return contributionService.chercherParContributeurId(idContributeur);
    }
    @GetMapping("/projet/{idProjet}")
    public List<ContributionDto> chercherParProjetId(@PathVariable int idProjet)
    {
        return contributionService.chercherParProjetId(idProjet);
    }
    //contributions/contributeur/{idContributeur}/projet/{idProjet}/valides?valide=true or false
    @GetMapping("/contributeur/{idContributeur}/projet/{idProjet}/valides")
    public List<ContributionDto> chercherContributionValide(
            @PathVariable int idContributeur,
            @PathVariable int idProjet,
            @RequestParam boolean valide)
    {
        return contributionService.chercherContributionValide(idContributeur,idProjet,valide);
    }
    @GetMapping("/contributeur/{id}/projets")
    public ResponseEntity<List<Projet>> projetList(
            @PathVariable int id
    )
    {
        List<Projet> projets = contributionService.listerProjetsDuContributeur(id);
        return ResponseEntity.ok(projets);
    }

}
