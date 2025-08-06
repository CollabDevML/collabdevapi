package com.groupe1.collabdev_api.services;

import com.groupe1.collabdev_api.dto.response_dto.SoutienResponse;
import com.groupe1.collabdev_api.entities.IdeeProjet;
import com.groupe1.collabdev_api.entities.Soutien;
import com.groupe1.collabdev_api.entities.ids.SoutienId;
import com.groupe1.collabdev_api.repositories.IdeeProjetRepository;
import com.groupe1.collabdev_api.repositories.SoutienRepository;
import com.groupe1.collabdev_api.repositories.UtilisateurRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class SoutienService {

    @Autowired
    private SoutienRepository soutienRepository;

    @Autowired
    private IdeeProjetRepository ideeProjetRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Transactional
    public SoutienResponse ajouter(int idIdeeProjet, int idUtilisateur) throws EntityExistsException, BadRequestException {
        if (!areExistUserAndIdea(idUtilisateur, idIdeeProjet)) {
            throw new BadRequestException("Vérifiez les ids fournis!");
        }
        SoutienId idSoutien = new SoutienId(idIdeeProjet, idUtilisateur);
        if (soutienRepository.existsById(idSoutien)) {
            throw new EntityExistsException("Idée de projet déjà soutenu par l'utilisateur!");
        }
        Soutien soutien = new Soutien(
                idSoutien,
                LocalDate.now()
        );
        IdeeProjet ideeProjet = ideeProjetRepository.findById(idIdeeProjet)
                .orElseThrow(
                        () -> new EntityNotFoundException("Idée de projet introuvable!")
                );
        ideeProjet.setNombreDeSoutien(ideeProjet.getNombreDeSoutien()+1);
        ideeProjetRepository.save(ideeProjet);
        return soutienRepository.save(soutien).toResponse();
    }

    @Transactional
    public String supprimer(int idIdeeProjet, int idUtilisateur) throws EntityNotFoundException, BadRequestException {
        if (!areExistUserAndIdea(idUtilisateur, idIdeeProjet)) {
            throw new BadRequestException("Vérifiez les ids fournis!");
        }
        SoutienId idSoutien = new SoutienId(idIdeeProjet, idUtilisateur);
        if (!soutienRepository.existsById(idSoutien)) {
            throw new EntityNotFoundException("L'utilisateur ne soutient pas cette idée!");
        }
        IdeeProjet ideeProjet = ideeProjetRepository.findById(idIdeeProjet)
                .orElseThrow(
                        () -> new EntityNotFoundException("Idée de projet introuvable!")
                );
        ideeProjet.setNombreDeSoutien(ideeProjet.getNombreDeSoutien()-1);
        ideeProjetRepository.save(ideeProjet);
        soutienRepository.deleteById(idSoutien);
        return "L'utilisateur a arrêté de soutenir cette idée!";
    }

    private Boolean areExistUserAndIdea(int idUtilisateur, int idIdeeProjet) {
        return utilisateurRepository.findById(idUtilisateur).isPresent() && ideeProjetRepository.findById(idIdeeProjet).isPresent();
    }
}
