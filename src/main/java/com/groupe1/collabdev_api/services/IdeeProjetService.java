package com.groupe1.collabdev_api.services;

import com.groupe1.collabdev_api.dto.request_dto.RequestIdeeProjet;
import com.groupe1.collabdev_api.entities.IdeeProjet;
import com.groupe1.collabdev_api.entities.Utilisateur;
import com.groupe1.collabdev_api.repositories.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IdeeProjetService {

    @Autowired
    private IdeeProjetRepository ideeProjetRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private ContributeurRepository contributeurRepository;

    @Autowired
    private GestionnaireRepository gestionnaireRepository;

    @Autowired
    private PorteurProjetRepository porteurProjetRepository;

    public IdeeProjet chercherParId(int id) {
        return ideeProjetRepository.findById(id).orElse(null);
    }

    public List<IdeeProjet> chercherTous() {
        return ideeProjetRepository.findAll();
    }

    public IdeeProjet ajouter(RequestIdeeProjet ideeProjet) throws EntityNotFoundException {
        Utilisateur utilisateur = utilisateurRepository.findById(ideeProjet.getIdUtilisateur())
                .orElseThrow(
                        () -> new EntityNotFoundException("Utilisateur introuvable avec l'id : " + ideeProjet.getIdUtilisateur())
                );
        return ideeProjetRepository.save(new IdeeProjet(
                0,
                ideeProjet.getTitre(),
                ideeProjet.getDescription(),
                ideeProjet.getDomaine(),
                ideeProjet.getUriCDC(),
                ideeProjet.getNombreSoutien(),
                ideeProjet.getDatePublication(),
                utilisateur,
                new ArrayList<>()
        ));
    }

    public IdeeProjet modifier(int id, RequestIdeeProjet requestIdeeProjet) throws EntityNotFoundException {
        IdeeProjet ideeProjet = ideeProjetRepository.findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException("Idée de Projet introuvable!")
                );
        ideeProjet.setTitre(requestIdeeProjet.getTitre());
        ideeProjet.setDescription(requestIdeeProjet.getDescription());
        ideeProjet.setUriCDC(requestIdeeProjet.getUriCDC());
        ideeProjet.setDomaine(requestIdeeProjet.getDomaine());
        return ideeProjetRepository.save(ideeProjet);
    }

    public Boolean supprimerParId(int id) {
        ideeProjetRepository.deleteById(id);
        return true;
    }

    public IdeeProjet soutenirIdeeProjet(int id) {
        IdeeProjet projet = ideeProjetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Projet introuvable"));

        projet.setNombreDeSoutien(projet.getNombreDeSoutien() + 1);
        return ideeProjetRepository.save(projet);
    }
}

