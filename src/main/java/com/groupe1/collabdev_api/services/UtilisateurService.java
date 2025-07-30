package com.groupe1.collabdev_api.services;

import com.groupe1.collabdev_api.entities.Utilisateur;
import com.groupe1.collabdev_api.entities.enums.Role;
import com.groupe1.collabdev_api.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public Utilisateur chercherParId(int id) {
        return utilisateurRepository.findById(id).orElse(null);
    }

    public Utilisateur chercherParEmail(String email) {
        return utilisateurRepository.findByEmail(email).orElse(null);
    }


    public List<Utilisateur> chercherTous() {
        return utilisateurRepository.findAll();
    }

    public Utilisateur ajouter(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    public Utilisateur modifier(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    public List<Utilisateur> chercherParRole(Role role) {
        return utilisateurRepository.findUtilisateursByRole(role);
    }
}

