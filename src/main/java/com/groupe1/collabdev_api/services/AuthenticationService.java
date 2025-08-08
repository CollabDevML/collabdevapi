package com.groupe1.collabdev_api.services;

import com.groupe1.collabdev_api.dto.request_dto.RequestAuthentification;
import com.groupe1.collabdev_api.dto.response_dto.ResponseAuthentification;
import com.groupe1.collabdev_api.entities.Administrateur;
import com.groupe1.collabdev_api.entities.Gestionnaire;
import com.groupe1.collabdev_api.entities.Utilisateur;
import com.groupe1.collabdev_api.entities.enums.Role;
import com.groupe1.collabdev_api.exceptions.UserNotFoundException;
import com.groupe1.collabdev_api.repositories.AdministrateurRepository;
import com.groupe1.collabdev_api.repositories.GestionnaireRepository;
import com.groupe1.collabdev_api.repositories.UtilisateurRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private AdministrateurService administrateurService;

    @Autowired
    private AdministrateurRepository administrateurRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private GestionnaireRepository gestionnaireRepository;

    public ResponseAuthentification authenticate(RequestAuthentification user) throws UserNotFoundException {
        Boolean userExist = isExistUser(user.getEmail());
        Boolean adminExist = isExistAdmin(user.getEmail());
        if (!(userExist || adminExist)) {
            return null;
        }
        if (userExist && adminExist) {
            return null;
        }
        try {
            if (userExist) {
                return authenticateUser(user.getEmail(), user.getMotDePasse());
            }
            return authenticateAdmin(user.getEmail(), user.getMotDePasse());
        } catch (EntityNotFoundException e) {
            return null;
        }
    }

    private Boolean isExistAdmin(String email) {
        return administrateurRepository.findByEmail(email).isPresent();
    }

    private Boolean isExistUser(String email) {
        return utilisateurRepository.findByEmail(email).isPresent();
    }

    private ResponseAuthentification authenticateUser(String email, String motDePasse) throws EntityNotFoundException {
        Utilisateur utilisateur = utilisateurRepository.findByEmail(email).
                orElseThrow(
                        () -> new EntityNotFoundException("Utilisateur introuvable!")
                );
        if (!utilisateur.isEtat()) {
            throw new RuntimeException("Votre compte est bloqué, impossible de se connecter!");
        }
        if(utilisateur.getRole() == Role.GESTIONNAIRE) {
            Gestionnaire gestionnaire = gestionnaireRepository.findByUtilisateurId(utilisateur.getId())
                    .orElseThrow(
                            () -> new EntityNotFoundException("Erreur lors du chargement des données de l'utilisateur!")
                    );
            if (!gestionnaire.isEstValide()){
                throw new RuntimeException("Authentification impossible! Compte gestionnaire non validé!");
            }
        }
        if (BCrypt.checkpw(
                motDePasse,
                utilisateur.getMotDePasse()
        )) {
            return new ResponseAuthentification(
                    utilisateur.getId(),
                    utilisateur.getRole()
            );
        }
        return null;
    }

    private ResponseAuthentification authenticateAdmin(String email, String motDePasse) throws EntityNotFoundException {
        Administrateur administrateur = administrateurRepository.findByEmail(email)
                .orElseThrow(
                        () -> new EntityNotFoundException("Utilisateur introuvable!")
                );
        if (BCrypt.checkpw(
                motDePasse,
                administrateur.getMotDePasse()
        )) {
            return new ResponseAuthentification(
                    administrateur.getId(),
                    administrateur.getRole()
            );
        }
        return null;
    }
}
