package com.groupe1.collabdev_api.services;

import com.groupe1.collabdev_api.dto.ProjetDto;
import com.groupe1.collabdev_api.entities.Gestionnaire;
import com.groupe1.collabdev_api.entities.Projet;
import com.groupe1.collabdev_api.repositories.GestionnaireRepository;
import com.groupe1.collabdev_api.repositories.ProjetRepository;
import jakarta.persistence.EntityNotFoundException;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjetService {

    @Autowired
    private ProjetRepository projetRepository;
    @Autowired
    private GestionnaireRepository gestionnaireRepository;
    @Autowired
    private EnvoieDemailService emailService;

    public Projet chercherParId(int id) {
        return projetRepository.findById(id).orElse(null);
    }

    public List<Projet> chercherTous() {
        return projetRepository.findAll();
    }

    public Projet ajouter(ProjetDto projetDto) {
        Gestionnaire gestionnaire = gestionnaireRepository.findById(projetDto.getIdGestionnaire())
                .orElseThrow(() -> new RuntimeException("Gestionnaire introuvable"));
        Projet projet = new Projet(
                9,
                projetDto.getTitre(),
                projetDto.getDescription(),
                projetDto.isEstFini(),
                projetDto.getDateDebut(),
                projetDto.getDateFin(),
                projetDto.getNiveauDAcces(),
                projetDto.isEtat(),
                gestionnaire,
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>()
        );
        Projet projetToResponse = projetRepository.save(projet);
        emailService.envoyerEmail(
                gestionnaire.getUtilisateur().getEmail(),
                "Création de projet",
                String.format("""
                        Bonjour %s %s. \n
                        Votre projet %s a été créé avec succès, place à le gérer :)"""
                ,gestionnaire.getUtilisateur().getPrenom(),
                        gestionnaire.getUtilisateur().getNom(),
                        projetToResponse.getTitre())
        );
        return projetToResponse;
    }

    //Lister les projet d'un gestionnaire
    public List<Projet> listerLesProjetParGestionnaireId(int gestionnaireId) {
        // Vérifie que le gestionnaire existe
        if (!gestionnaireRepository.existsById(gestionnaireId)) {
            throw new RuntimeException("Gestionnaire introuvable avec l'id : " + gestionnaireId);
        }
        // Récupère les projets liés au gestionnaire
        return projetRepository.findByGestionnaireId(gestionnaireId);
    }

    //afficher un projet d'un gestionnaire
    public Projet chercherUnProjetDuGestionnaire(int gestionnaireId, int id) {
        return projetRepository.findByIdAndGestionnaireId(gestionnaireId, id)
                .orElseThrow(() -> new RuntimeException("Gestionnaire introuvable"));
    }

    public Projet modifier(int idProjet, int idGestionnaire, ProjetDto projetDto) throws BadRequestException, EntityNotFoundException {
        Projet projet = projetRepository.findById(idProjet).orElseThrow(
                () -> new EntityNotFoundException("Projet introuvable avec l'id " + idProjet)
        );
        if (projet.getGestionnaire().getId() == idGestionnaire) {
            projet.setTitre(projetDto.getTitre());
            projet.setDescription(projetDto.getDescription());
            projet.setDateDebut(projetDto.getDateDebut());
            projet.setDateFin(projetDto.getDateFin());
            projet.setNiveauDAcces(projetDto.getNiveauDAcces());
            projet.setEstFini(projetDto.isEstFini());
            projet.setEtat(projetDto.isEtat());
            return projetRepository.save(projet);
        } else {
            throw new BadRequestException("Ce gestionnaire n'est pas autorisé a modifié ce projet");
        }

    }

    public Boolean supprimerParId(int gestionnaireId, int id) {
        Projet projet = projetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("projet introuvable"));
        if (gestionnaireId == projet.getGestionnaire().getId()) {
            projetRepository.deleteById(id);
            return true;
        } else {
            System.out.println("Vous n'avez pas le droit de supprimer un projet");
            return false;
        }
    }
}

