package com.groupe1.collabdev_api.controllers;

import com.groupe1.collabdev_api.entities.request_dto.RequestContributeur;
import com.groupe1.collabdev_api.entities.request_dto.RequestGestionnaire;
import com.groupe1.collabdev_api.entities.request_dto.RequestPorteurProjet;
import com.groupe1.collabdev_api.entities.response_dto.ResponseContributeur;
import com.groupe1.collabdev_api.entities.response_dto.ResponseGestionnaire;
import com.groupe1.collabdev_api.entities.response_dto.ResponsePorteurProjet;
import com.groupe1.collabdev_api.entities.Contributeur;
import com.groupe1.collabdev_api.entities.Gestionnaire;
import com.groupe1.collabdev_api.entities.PorteurProjet;
import com.groupe1.collabdev_api.entities.Utilisateur;
import com.groupe1.collabdev_api.entities.enums.Role;
import com.groupe1.collabdev_api.services.ContributeurService;
import com.groupe1.collabdev_api.services.GestionnaireService;
import com.groupe1.collabdev_api.services.PorteurProjetService;
import com.groupe1.collabdev_api.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/utilisateurs")
public class CreationCompteController {

    @Autowired
    private GestionnaireService gestionnaireService;

    @Autowired
    private PorteurProjetService porteurProjetService;

    @Autowired
    private ContributeurService contributeurService;

    @Autowired
    private UtilisateurService utilisateurService;

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
                                contributeurAjoute.getUriCv()
                        ),
                        HttpStatus.CREATED
                );
    }

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
                                gestionnaireAjoute.isEstValide()
                        ),
                        HttpStatus.CREATED
                );
    }
}
