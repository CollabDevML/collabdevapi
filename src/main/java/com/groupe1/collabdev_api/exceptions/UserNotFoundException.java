package com.groupe1.collabdev_api.exceptions;

import com.groupe1.collabdev_api.entities.enums.Role;

public class UserNotFoundException extends Exception {

    public UserNotFoundException(
            Role role
    ) {
        super(getMessage(role));
    }

    private static String getMessage(Role role) {
        String message = "";
        switch (role) {
            case CONTRIBUTEUR -> {
                return "Contributeur introuvable!";
            }
            case GESTIONNAIRE -> {
                return "Gestionnaire introuvable!";
            }
            case PORTEUR_PROJET -> {
                return "Porteur de projet introuvable!";
            }
            default -> {
                return "Utilisateur introuvable!";
            }
        }
    }
}
