package com.groupe1.collabdev_api.controllers;

import com.groupe1.collabdev_api.entities.Administrateur;
import com.groupe1.collabdev_api.entities.enums.Role;
import com.groupe1.collabdev_api.services.AdministrateurService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Controller pour les administrateurs",
        description = "Quelque chose")
@RestController
@RequestMapping("/administrateurs")
public class AdministrateurController {

    @Autowired
    private AdministrateurService administrateurService;

    //Methode pour la creation des autres Administrateurs :
    @PostMapping
    public Administrateur add(@RequestBody Administrateur admin) {
        admin.setMotDePasse(BCrypt.hashpw(admin.getMotDePasse(), BCrypt.gensalt()));
        admin.setRole(Role.ADMIN);
        return administrateurService.ajouter(admin);
    }

    //Methode pour la modification d'un administrateur par id :
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Administrateur admin) {
        return administrateurService.updateAdmin(id, admin);
    }

    //Methode pour la liste des Administrateurs :
    @GetMapping
    public List<Administrateur> list() {
        if (administrateurService.chercherTous().isEmpty()) {
            return null;
        }
        return administrateurService.chercherTous();
    }

    //Methode pour afficher un seul administrateur :
    @GetMapping("/{id}")
    public Administrateur get(@PathVariable Integer id) {
        return administrateurService.chercherParId(id);
    }

    //Methode pour la suppression d'un administrateur :
    @DeleteMapping("/{id}")
    public List<Administrateur> deleteAdmin(@PathVariable Integer id) {
        if (administrateurService.supprimerParId(id)) {
            return administrateurService.chercherTous();
        }
        return null;
    }

}
