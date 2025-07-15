package com.groupe1.collabdev_api.controllers;

import com.groupe1.collabdev_api.dto.ContributionDto;
import com.groupe1.collabdev_api.dto.ProjetDto;
import com.groupe1.collabdev_api.services.ContributeurService;
import com.groupe1.collabdev_api.services.ContributionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/utilisateurs/contributeurs")
@Tag(name="contribution Api",
        description="pour la gestion des contributions")
public class ContributionController {

    @Autowired
    private ContributionService contributionService;
    @Autowired
    private ContributeurService contributeurService;

    @Operation(summary = "pour avoir une contribution")
    @GetMapping("/contributions/{id}")
    public ResponseEntity<?> chercherParId(@PathVariable int id) {
        return contributionService.chercherParId(id);
    }

    @Operation(summary = "pour avoir toutes les contributions")
    @GetMapping("/contributions")
    public List<ContributionDto> chercherTous() {
        return contributionService.chercherTous();
    }

    @Operation(summary = "pour la validation d'une contribution")
    @PatchMapping("/contributions/{id}/valide")
    public ResponseEntity<ContributionDto> validerContribution(
            @PathVariable int id) {
        ContributionDto modifieDto = contributionService.validerContribution(id);
        return ResponseEntity.ok(modifieDto);
    }

    @Operation(summary = "pour supprimer une contribution")
    @DeleteMapping("/contributions/{id}")
    public Boolean supprimerById(@PathVariable int id) {
        return contributionService.supprimerParId(id);
    }

    @Operation(summary = "pour chercher la contribution des contributeurs")
    @GetMapping("/{idContributeur}/contributions")
    public List<ContributionDto> chercherParContributeurId(@PathVariable int idContributeur) {
        return contributionService.chercherParContributeurId(idContributeur);
    }

    @GetMapping("/contributions/projets/{idProjet}")
    public List<ContributionDto> chercherParProjetId(@PathVariable int idProjet) {
        return contributionService.chercherParProjetId(idProjet);
    }

    @Operation(summary = "pour chercher les contributions valides")
    //contributions/contributeur/{idContributeur}/projet/{idProjet}/valides?valide=true or false
    @GetMapping("/{idContributeur}/projets/{idProjet}/valide/{estValide}")
    public List<ContributionDto> chercherContributionValide(
            @PathVariable int idContributeur,
            @PathVariable int idProjet,
            @PathVariable boolean estValide) {
        return contributionService.chercherContributionValide(idContributeur, idProjet, estValide);
    }

    @Operation(summary = "pour chercher les projets d'un contributeur")
    @GetMapping("/{id}/projets")
    public ResponseEntity<List<ProjetDto>> projetList(
            @PathVariable int id
    ) {
        List<ProjetDto> projets = contributeurService.chercherProjetsParContributeur(id);
        return ResponseEntity.ok(projets);
    }

}
