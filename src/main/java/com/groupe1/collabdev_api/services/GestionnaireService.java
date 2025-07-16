package com.groupe1.collabdev_api.services;

import com.groupe1.collabdev_api.dto.response_dto.ResponseGestionnaire;
import com.groupe1.collabdev_api.entities.Administrateur;
import com.groupe1.collabdev_api.entities.Gestionnaire;
import com.groupe1.collabdev_api.entities.enums.Role;
import com.groupe1.collabdev_api.repositories.GestionnaireRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GestionnaireService {

    @Autowired
    private GestionnaireRepository gestionnaireRepository;

    @Autowired
    private EnvoieDemailService emailService;

    @Autowired
    private AdministrateurService administrateurService;

    @Autowired
    private UtilisateurService utilisateurService;

    public Gestionnaire chercherParId(int id) {
        return gestionnaireRepository.findById(id).orElse(null);
    }

    public List<Gestionnaire> chercherTous() {
        return gestionnaireRepository.findAll();
    }

    public List<ResponseGestionnaire> chercherTousParEstValide(boolean estValide) {
        List<Gestionnaire> gestionnaires = gestionnaireRepository.findAllByEstValide(estValide);
        List<ResponseGestionnaire> responseGestionnaires = new ArrayList<>();
        for (Gestionnaire gestionnaire : gestionnaires) {
            responseGestionnaires.add(
                    new ResponseGestionnaire(
                            gestionnaire.getUtilisateur().getId(),
                            gestionnaire.getUtilisateur().getPrenom(),
                            gestionnaire.getUtilisateur().getNom(),
                            gestionnaire.getUtilisateur().getEmail(),
                            gestionnaire.getUtilisateur().getMotDePasse(),
                            gestionnaire.getUtilisateur().getGenre(),
                            gestionnaire.getUriCv(),
                            gestionnaire.isEstValide(),
                            gestionnaire.getId()
                    )
            );
        }
        return responseGestionnaires;
    }

    public Optional<ResponseGestionnaire> validerCompteGestionnaire(int idGestionnaire, boolean estValide, String cause) throws EntityNotFoundException {
        Gestionnaire gestionnaire = gestionnaireRepository.findById(idGestionnaire)
                .orElseThrow(
                        () -> new EntityNotFoundException("Gestionnaire introuvable avec l'id : " + idGestionnaire)
                );
        if (gestionnaire.isEstValide()) {
            throw new RuntimeException("Ce gestionnaire est déjà validé!");
        }
        if (estValide) {
            gestionnaire.setEstValide(true);
            gestionnaireRepository.save(gestionnaire);
            emailService.envoyerEmail(
                    gestionnaire.getUtilisateur().getEmail(),
                    "Validation de compte",
                    String.format("""
                                    Cher %s %s, nous vous informons que votre compte a été validé avec succès! \n
                                    Veuillez vous rendre sur l'application pour vous connecter.
                                    """,
                            gestionnaire.getUtilisateur().getPrenom(),
                            gestionnaire.getUtilisateur().getNom())
            );
            return Optional.of(gestionnaire.toResponse());
        }
        supprimerParId(idGestionnaire);
        emailService.envoyerEmail(
                gestionnaire.getUtilisateur().getEmail(),
                "Validation de compte",
                String.format("""
                                Cher %s %s, nous vous informons que la validation de votre compte a été réfusé! \n
                                Pour cause : %s
                                """,
                        gestionnaire.getUtilisateur().getPrenom(),
                        gestionnaire.getUtilisateur().getNom(),
                        cause)
        );
        return Optional.empty();
    }

    public Gestionnaire ajouter(Gestionnaire gestionnaire) {
        Gestionnaire gestionnaireAjoute = gestionnaireRepository.save(gestionnaire);
        emailService.envoyerEmail(
                gestionnaire.getUtilisateur().getEmail(),
                "Demande de validation de compte",
                """
                        Votre demande est en cours de validation.\
                        Merci de bien vouloir patienter pendant le processus de validation :D
                        """
        );
        List<Administrateur> administrateurs = administrateurService.chercherTous();
        for (Administrateur administrateur : administrateurs) {
            if (administrateur.getRole() == Role.ADMIN) {
                emailService.envoyerEmail(
                        administrateur.getEmail(),
                        "Demande de validation de compte Gestionnaire",
                        String.format(
                                """
                                        Vous avez une demande de validation de compte en attente de Mr/Mme %s %s.\
                                        Veuillez vous connecter pour étudier sa demande.\
                                        Prévisualiser le CV à cet url %s.
                                        """,
                                gestionnaire.getUtilisateur().getPrenom(),
                                gestionnaire.getUtilisateur().getNom(),
                                gestionnaire.getUriCv()
                        )
                );
            }
        }
        return gestionnaireAjoute;
    }

    public Gestionnaire modifier(Gestionnaire gestionnaire) {
        return gestionnaireRepository.save(gestionnaire);
    }

    public Boolean supprimerParId(int id) {
        gestionnaireRepository.deleteById(id);
        return true;
    }
}

