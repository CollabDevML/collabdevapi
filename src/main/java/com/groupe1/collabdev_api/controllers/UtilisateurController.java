package com.groupe1.collabdev_api.controllers;

import com.groupe1.collabdev_api.entities.Utilisateur;
import com.groupe1.collabdev_api.entities.enums.Role;
import com.groupe1.collabdev_api.services.UtilisateurService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("admin/utilisateur/")
@Tag(name="Utilisateurs Api")
public class UtilisateurController {
    @Autowired
    private UtilisateurService utilisateurService;

    @Operation(summary = "pour lister les utilisateurs")

    @GetMapping
    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurService.chercherTous();
    }

    @Operation(summary = "la liste en fonction du role")
    @GetMapping("role")
    public List<Utilisateur> getAllUtilisateursByRole(@RequestParam(value = "role") Role role) {
        return utilisateurService.chercherParRole(role);
    }

}
