package com.groupe1.collabdev_api.controllers;

import com.groupe1.collabdev_api.entities.Utilisateur;
import com.groupe1.collabdev_api.entities.enums.Role;
import com.groupe1.collabdev_api.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin/utilisateur/")
public class UtilisateurController {
    @Autowired
    private UtilisateurService utilisateurService;

    //Pour lister les utilisateurs :

    @GetMapping
    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurService.chercherTous();
    }

    //Pour les lister en fonction de role :
    @GetMapping("role")
    public List<Utilisateur> getAllUtilisateursByRole(@RequestParam(value = "role") Role role) {
        return utilisateurService.chercherParRole(role);
    }

}
