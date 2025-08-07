package com.groupe1.collabdev_api.services;

import com.groupe1.collabdev_api.entities.Utilisateur;
import com.groupe1.collabdev_api.entities.enums.Role;
import com.groupe1.collabdev_api.repositories.UtilisateurRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    public List<String> modifierLesPreferences(int idUtilisateur, List<String> preferences) throws EntityNotFoundException {
        Utilisateur utilisateur = utilisateurRepository.findById(idUtilisateur)
                .orElseThrow(
                        () -> new EntityNotFoundException("Utilisateur introuvable!")
                );
        utilisateur.setPreferences(preferences);
        utilisateur = utilisateurRepository.save(utilisateur);
        return utilisateur.getPreferences();
    }
}

