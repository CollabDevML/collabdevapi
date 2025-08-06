package com.groupe1.collabdev_api.services;

import com.groupe1.collabdev_api.dto.response_dto.ResponseIdeeProjet;
import com.groupe1.collabdev_api.entities.Utilisateur;
import com.groupe1.collabdev_api.repositories.IdeeProjetRepository;
import com.groupe1.collabdev_api.repositories.SoutienRepository;
import com.groupe1.collabdev_api.repositories.UtilisateurRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendationService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private SoutienRepository soutienRepository;

    @Autowired
    private IdeeProjetRepository ideeProjetRepository;

    public ResponseIdeeProjet getRecommendedIdea(int idUtilisateur) {
        Utilisateur utilisateur = utilisateurRepository.findById(idUtilisateur)
                .orElseThrow(
                        () -> new EntityNotFoundException("Utilisateur non trouvable avec cet id!")
                );
        List<String> preferences = utilisateur.getPreferences();
        return null;
    }
}
