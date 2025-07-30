package com.groupe1.collabdev_api.controllers;

import com.groupe1.collabdev_api.dto.request_dto.RequestContributeur;
import com.groupe1.collabdev_api.dto.request_dto.RequestGestionnaire;
import com.groupe1.collabdev_api.dto.request_dto.RequestPorteurProjet;
import com.groupe1.collabdev_api.dto.response_dto.ResponseContributeur;
import com.groupe1.collabdev_api.dto.response_dto.ResponseGestionnaire;
import com.groupe1.collabdev_api.dto.response_dto.ResponsePorteurProjet;
import com.groupe1.collabdev_api.entities.Contributeur;
import com.groupe1.collabdev_api.entities.Gestionnaire;
import com.groupe1.collabdev_api.entities.PorteurProjet;
import com.groupe1.collabdev_api.entities.Utilisateur;
import com.groupe1.collabdev_api.entities.enums.Role;
import com.groupe1.collabdev_api.services.ContributeurService;
import com.groupe1.collabdev_api.services.GestionnaireService;
import com.groupe1.collabdev_api.services.PorteurProjetService;
import com.groupe1.collabdev_api.services.UtilisateurService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/utilisateurs")
@Tag(name = "Authentification Api",
        description = "pour la création du compte des utilisateurs")
public class CreationCompteController {

    @Autowired
    private GestionnaireService gestionnaireService;

    @Autowired
    private PorteurProjetService porteurProjetService;

    @Autowired
    private ContributeurService contributeurService;

    @Autowired
    private UtilisateurService utilisateurService;

    @Operation(summary = "pour la création du compte d'un contributeur ")
    @PostMapping("/contributeurs")
    public ResponseEntity<ResponseContributeur> ajouterContributeur(
            @RequestBody RequestContributeur requestContributeur
    ) {
        Utilisateur utilisateurAjoute = utilisateurService.ajouter(
                new Utilisateur(
                        0,
                        requestContributeur.getPrenom(),
                        requestContributeur.getNom(),
                        requestContributeur.getEmail(),
                        BCrypt.hashpw(requestContributeur.getMotDePasse(), BCrypt.gensalt()),
                        requestContributeur.getGenre(),
                        Role.CONTRIBUTEUR,
                        true,
                        new ArrayList<>(),
                        new ArrayList<>(),
                        new ArrayList<>()
                )
        );
        Contributeur contributeurAjoute = contributeurService.ajouter(
                new Contributeur(
                        0,
                        utilisateurAjoute,
                        requestContributeur.getNiveau(),
                        requestContributeur.getSpecialite(),
                        requestContributeur.getType(),
                        requestContributeur.getPieces(),
                        requestContributeur.getUriCv(),
                        new ArrayList<>(),
                        new ArrayList<>(),
                        new ArrayList<>(),
                        new ArrayList<>()
                )
        );
        return
                new ResponseEntity<>(
                        new ResponseContributeur(
                                utilisateurAjoute.getId(),
                                utilisateurAjoute.getPrenom(),
                                utilisateurAjoute.getNom(),
                                utilisateurAjoute.getEmail(),
                                utilisateurAjoute.getMotDePasse(),
                                utilisateurAjoute.getGenre(),
                                contributeurAjoute.getNiveau(),
                                contributeurAjoute.getSpecialite(),
                                contributeurAjoute.getType(),
                                contributeurAjoute.getPieces(),
                                contributeurAjoute.getUriCv(),
                                contributeurAjoute.getId()
                        ),
                        HttpStatus.CREATED
                );
    }

    @Operation(summary = "pour la création du compte d'un porteur de projet")
    @PostMapping("/porteurs-projet")
    public ResponseEntity<ResponsePorteurProjet> ajouterPorteurProjet(
            @RequestBody RequestPorteurProjet requestPorteurProjet
    ) {
        Utilisateur utilisateurAjoute = utilisateurService.ajouter(
                new Utilisateur(
                        0,
                        requestPorteurProjet.getPrenom(),
                        requestPorteurProjet.getNom(),
                        requestPorteurProjet.getEmail(),
                        BCrypt.hashpw(requestPorteurProjet.getMotDePasse(), BCrypt.gensalt()),
                        requestPorteurProjet.getGenre(),
                        Role.PORTEUR_PROJET,
                        true,
                        new ArrayList<>(),
                        new ArrayList<>(),
                        new ArrayList<>()
                )
        );
        porteurProjetService.ajouter(
                new PorteurProjet(
                        0,
                        utilisateurAjoute
                )
        );
        return
                new ResponseEntity<>(
                        new ResponsePorteurProjet(
                                utilisateurAjoute.getId(),
                                utilisateurAjoute.getPrenom(),
                                utilisateurAjoute.getNom(),
                                utilisateurAjoute.getEmail(),
                                utilisateurAjoute.getMotDePasse(),
                                utilisateurAjoute.getGenre()
                        ),
                        HttpStatus.CREATED
                );
    }

    @Operation(summary = "pour la création de compte d'un gestionnaire")
    @PostMapping("/gestionnaires")
    public ResponseEntity<ResponseGestionnaire> ajouterGestionnaire(
            @RequestBody RequestGestionnaire requestGestionnaire
    ) {
        Utilisateur utilisateurAjoute = utilisateurService.ajouter(
                new Utilisateur(
                        0,
                        requestGestionnaire.getPrenom(),
                        requestGestionnaire.getNom(),
                        requestGestionnaire.getEmail(),
                        BCrypt.hashpw(requestGestionnaire.getMotDePasse(), BCrypt.gensalt()),
                        requestGestionnaire.getGenre(),
                        Role.GESTIONNAIRE,
                        true,
                        new ArrayList<>(),
                        new ArrayList<>(),
                        new ArrayList<>()
                )
        );
        Gestionnaire gestionnaireAjoute = gestionnaireService.ajouter(
                new Gestionnaire(
                        0,
                        utilisateurAjoute,
                        requestGestionnaire.getUriCv(),
                        false,
                        new ArrayList<>()
                )
        );
        return
                new ResponseEntity<>(
                        new ResponseGestionnaire(
                                utilisateurAjoute.getId(),
                                utilisateurAjoute.getPrenom(),
                                utilisateurAjoute.getNom(),
                                utilisateurAjoute.getEmail(),
                                utilisateurAjoute.getMotDePasse(),
                                utilisateurAjoute.getGenre(),
                                gestionnaireAjoute.getUriCv(),
                                gestionnaireAjoute.isEstValide(),
                                gestionnaireAjoute.getId()
                        ),
                        HttpStatus.CREATED
                );
    }
}
