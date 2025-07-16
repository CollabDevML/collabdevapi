package com.groupe1.collabdev_api.controllers;

import com.groupe1.collabdev_api.entities.Utilisateur;
import com.groupe1.collabdev_api.entities.enums.Role;
import com.groupe1.collabdev_api.services.GestionAdminUtilisateurService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/administrateurs/gestion/utilisateur/")
@Tag(name = "Admin Api",
        description = "Autorisation de l'admin")
public class GestionAdminUtilisateurController {
    @Autowired
    private GestionAdminUtilisateurService gestionAdminUtilisateurService;

    @Operation(summary = "pour lister tous les utilisateurs")

    @GetMapping
    public List<Utilisateur> getAllUtilisateurs() {
        return gestionAdminUtilisateurService.chercherTous();
    }

    @Operation(summary = "pour les lister en fonction du role")
    @GetMapping("role")
    public List<Utilisateur> getAllUtilisateursByRole(@RequestParam(value = "role") Role role) {
        return gestionAdminUtilisateurService.chercherParRole(role);
    }


    @Operation(summary = "pour bloquer un utilisateur")
    @GetMapping("bloquer/{id}")
    public Utilisateur getgestionAdminUtilisateurService(@PathVariable int id, @RequestParam("idAdmin") int idA) {
        return gestionAdminUtilisateurService.bloquerUtilisateur(id, idA);
    }

    @Operation(summary = "pour debloquer un utilisateur")
    @GetMapping("debloquer/{id}")
    public Utilisateur debloUtilisateur(@PathVariable int id, @RequestParam("idAdmin") int idA) {
        return gestionAdminUtilisateurService.debloquerUtilisateur(id, idA);
    }

    @Operation(summary = "pour lbloquer par le role")
    @GetMapping("bloquer")
    public String boquerEnFonctionDeRole(@RequestParam("Role") Role role, @RequestParam("idAdmin") int idA) {
        if (gestionAdminUtilisateurService.bloquerParRole(role, idA)) {
            return "Les " + role + " " + "Utilisateurs sont tous bloqués !!!";
        }
        return "Erreur. Quelque chose s'est mal passé !!!";
    }

    @Operation(summary = "pour debloquer en fonction du role")
    @GetMapping("debloquer")
    public String debloquerEnFonction(@RequestParam("Role") Role role, @RequestParam("idAdmin") int idA) {
        if (gestionAdminUtilisateurService.debloquerUtilisateurRole(role, idA)) {
            return "Les " + role + " " + "Utilisateurs sont tous Debloqués !!!";
        }
        return "Erreur. Quelque chose s'est mal passé !!!";
    }

    @Operation(summary = "pour debloquer tous les utilisateurs bloqués")
    @GetMapping("debloquerTous")
    public List<Utilisateur> debloquerTous(@RequestParam("idAdmin") int idA) {
        return gestionAdminUtilisateurService.debloquerTousUtilisateur(idA);
    }

    @Operation(summary = "pour la suppression d'un utilisateur")
    @DeleteMapping("{id}")
    public List<Utilisateur> deleteUtilisateur(@PathVariable int id, @RequestParam("idAdmin") int idA) {
        gestionAdminUtilisateurService.supprimerParId(id, idA);
        return gestionAdminUtilisateurService.chercherTous();
    }

    @Operation(summary = "pour la gestion de l'acceptation des gestionnaires")
    @GetMapping("gestionnaire")
    public List<Utilisateur> gestionnaireUtilisateur() {
        return gestionAdminUtilisateurService.chercherParRole(Role.GESTIONNAIRE);
    }

    @Operation(summary = "pour valider la demande d'un gestionnaire")
    @GetMapping("gestionnaire/{id}")
    public void validerDemande(@PathVariable int id, @RequestParam("idAdmin") int idA) {
        this.debloUtilisateur(id, idA);
    }

    @Operation(summary = "pour rejeter la demande d'un gestionnaire")
    @DeleteMapping("gestionnaire/{id}")
    public void rejeterDemande(@PathVariable int id, @RequestParam("idAdmin") int idA) {
        this.deleteUtilisateur(id, idA);
    }
}
