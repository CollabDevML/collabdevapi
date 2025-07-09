package com.groupe1.collabdev_api.controllers;

import com.groupe1.collabdev_api.entities.Utilisateur;
import com.groupe1.collabdev_api.entities.enums.Role;
import com.groupe1.collabdev_api.services.GestionAdminUtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/administrateurs/gestion/utilisateur/")
public class GestionAdminUtilisateurController {
    @Autowired
    private GestionAdminUtilisateurService gestionAdminUtilisateurService;

    //Pour lister les utilisateurs :

    @GetMapping
    public List<Utilisateur> getAllUtilisateurs() {
        return gestionAdminUtilisateurService.chercherTous();
    }

    //Pour les lister en fonction de role :
    @GetMapping("role")
    public List<Utilisateur> getAllUtilisateursByRole(@RequestParam(value = "role") Role role) {
        return gestionAdminUtilisateurService.chercherParRole(role);
    }


    //Pour bloquer un utilisateur :
    @GetMapping("bloquer/{id}")
    public Utilisateur getgestionAdminUtilisateurService(@PathVariable int id, @RequestParam("idAdmin") int idA) {
        return gestionAdminUtilisateurService.bloquerUtilisateur(id, idA);
    }

    //Pour debloquer un utilisateur :
    @GetMapping("debloquer/{id}")
    public Utilisateur debloUtilisateur(@PathVariable int id, @RequestParam("idAdmin") int idA) {
        return gestionAdminUtilisateurService.debloquerUtilisateur(id, idA);
    }

    //Pour bloquer en fonction de role :
    @GetMapping("bloquer")
    public String boquerEnFonctionDeRole(@RequestParam("Role") Role role, @RequestParam("idAdmin") int idA) {
        if (gestionAdminUtilisateurService.bloquerParRole(role, idA)) {
            return "Les " + role + " " + "Utilisateurs sont tous bloqués !!!";
        }
        return "Erreur. Quelque chose s'est mal passé !!!";
    }

    //Pour debloquer en fonction de Role:
    @GetMapping("debloquer")
    public String debloquerEnFonction(@RequestParam("Role") Role role, @RequestParam("idAdmin") int idA) {
        if (gestionAdminUtilisateurService.debloquerUtilisateurRole(role, idA)) {
            return "Les " + role + " " + "Utilisateurs sont tous Debloqués !!!";
        }
        return "Erreur. Quelque chose s'est mal passé !!!";
    }

    //Pour debloquer tout les utilisateurs bloqués :
    @GetMapping("debloquerTous")
    public List<Utilisateur> debloquerTous(@RequestParam("idAdmin") int idA) {
        return gestionAdminUtilisateurService.debloquerTousUtilisateur(idA);
    }

    //Pour la suppression d'un utilisateur :
    @DeleteMapping("{id}")
    public List<Utilisateur> deleteUtilisateur(@PathVariable int id, @RequestParam("idAdmin") int idA) {
        gestionAdminUtilisateurService.supprimerParId(id, idA);
        return gestionAdminUtilisateurService.chercherTous();
    }

    //Pour la gestion de l'acceptation des gestionnaires :
    @GetMapping("gestionnaire")
    public List<Utilisateur> gestionnaireUtilisateur() {
        return gestionAdminUtilisateurService.chercherParRole(Role.GESTIONNAIRE);
    }

    //Pour valider une demande de gestionnaire :
    @GetMapping("gestionnaire/{id}")
    public void validerDemande(@PathVariable int id, @RequestParam("idAdmin") int idA) {
        this.debloUtilisateur(id, idA);
    }

    //Pour Rejeter une demande de gestionnaire :
    @DeleteMapping("gestionnaire/{id}")
    public void rejeterDemande(@PathVariable int id, @RequestParam("idAdmin") int idA) {
        this.deleteUtilisateur(id, idA);
    }
}
