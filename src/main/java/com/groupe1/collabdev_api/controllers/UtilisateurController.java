package com.groupe1.collabdev_api.controllers;

import com.groupe1.collabdev_api.dto.response_dto.ResponseUtilisateur;
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

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("admin/utilisateur/")
@Tag(name="Utilisateurs Api")
public class UtilisateurController {
    @Autowired
    private UtilisateurService utilisateurService;

    @Operation(summary = "pour lister les utilisateurs")

    @GetMapping
    public List<ResponseUtilisateur> getAllUtilisateurs() {
        List<Utilisateur> utilisateurs = utilisateurService.chercherTous();
        List<ResponseUtilisateur> responseUtilisateurs = new ArrayList<>();
        for (Utilisateur utilisateur : utilisateurs) {
            responseUtilisateurs.add(utilisateur.toResponse());
        }
        return responseUtilisateurs;
    }

    @Operation(summary = "la liste en fonction du role")
    @GetMapping("role")
    public List<ResponseUtilisateur> getAllUtilisateursByRole(@RequestParam(value = "role") Role role) {
        List<Utilisateur> utilisateurs = utilisateurService.chercherParRole(role);
        List<ResponseUtilisateur> responseUtilisateurs = new ArrayList<>();
        for (Utilisateur utilisateur : utilisateurs) {
            responseUtilisateurs.add(utilisateur.toResponse());
        }
        return responseUtilisateurs;
    }

}
