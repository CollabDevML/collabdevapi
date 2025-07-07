package com.groupe1.collabdev_api.controllers;

import com.groupe1.collabdev_api.entities.Administrateur;
import com.groupe1.collabdev_api.services.AdministrateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/administrateurs")
public class AdministrateurController {

    @Autowired
    private AdministrateurService administrateurService;

    @PostMapping
    public Administrateur ajouterAdministrateur(
            @RequestBody Administrateur administrateur
    ) {
        administrateur.setMotDePasse(
                BCrypt.hashpw(
                        administrateur.getMotDePasse(),
                        BCrypt.gensalt()
                )
        );
        return administrateurService.ajouter(administrateur);
    }

    @GetMapping("/{id}")
    public Administrateur chercherParId(
            @PathVariable int id
    ) {
        return administrateurService.chercherParId(id);
    }

    @GetMapping
    public List<Administrateur> chercherTous() {
        return administrateurService.chercherTous();
    }

    @PutMapping
    public ResponseEntity<?> modifier(
            @RequestBody Administrateur administrateur
    ) {
        if (administrateur.getId() == 0) {
            return new ResponseEntity<>("Veuillez spécifier l'id", HttpStatus.BAD_REQUEST);
        }
        Administrateur oldAdministrateur = administrateurService.chercherParId(administrateur.getId());
        Administrateur preparedAdministrateur = new Administrateur();
        preparedAdministrateur.setId(oldAdministrateur.getId());
        if (administrateur.getEmail() == null) {
            preparedAdministrateur.setEmail(oldAdministrateur.getEmail());
        } else {
            preparedAdministrateur.setEmail(administrateur.getEmail());
        }
        if (administrateur.getMotDePasse() == null) {
            preparedAdministrateur.setMotDePasse(oldAdministrateur.getMotDePasse());
        } else {
            preparedAdministrateur.setMotDePasse(
                    BCrypt.hashpw(administrateur.getMotDePasse(),
                            BCrypt.gensalt())
            );
        }
        preparedAdministrateur.setRole(oldAdministrateur.getRole());
        return new ResponseEntity<>(
                administrateurService.modifier(preparedAdministrateur),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public Boolean supprimerParId(
            @PathVariable int id
    ) {
        return administrateurService.supprimerParId(id);
    }
}
