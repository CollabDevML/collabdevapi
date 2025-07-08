package com.groupe1.collabdev_api.services;

import com.groupe1.collabdev_api.dto.request_dto.RequestAuthentification;
import com.groupe1.collabdev_api.entities.Administrateur;
import com.groupe1.collabdev_api.entities.Utilisateur;
import com.groupe1.collabdev_api.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private AdministrateurService administrateurService;

    public Boolean authenticate(RequestAuthentification user) throws UserNotFoundException {
        switch (user.getRole()) {
            case CONTRIBUTEUR, GESTIONNAIRE, PORTEUR_PROJET -> {
                return authenticateUser(user.getEmail(), user.getMotDePasse());
            }
            case ADMIN, SUPER_ADMIN -> {
                return authenticateAdmin(user.getEmail(), user.getMotDePasse());
            }
            default -> {
                return false;
            }
        }
    }

    private Boolean authenticateUser(String email, String motDePasse) throws UserNotFoundException {
        Utilisateur utilisateur = utilisateurService.chercherParEmail(email);
        if (utilisateur == null) {
            throw new UserNotFoundException("Utilisateur introuvable!");
        }
        return BCrypt.checkpw(
                motDePasse,
                utilisateur.getMotDePasse()
        );
    }

    private Boolean authenticateAdmin(String email, String motDePasse) throws UserNotFoundException {
        Administrateur administrateur = administrateurService.chercherParEmail(email);
        if (administrateur == null) {
            throw new UserNotFoundException("Administrateur introuvable!");
        }
        return BCrypt.checkpw(
                motDePasse,
                administrateur.getMotDePasse()
        );
    }
}
