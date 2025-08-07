package com.groupe1.collabdev_api.services;

import com.groupe1.collabdev_api.dto.ProjetDto;
import com.groupe1.collabdev_api.dto.response_dto.ResponseIdeeProjet;
import com.groupe1.collabdev_api.entities.IdeeProjet;
import com.groupe1.collabdev_api.entities.Projet;
import com.groupe1.collabdev_api.entities.Soutien;
import com.groupe1.collabdev_api.entities.Utilisateur;
import com.groupe1.collabdev_api.repositories.IdeeProjetRepository;
import com.groupe1.collabdev_api.repositories.ProjetRepository;
import com.groupe1.collabdev_api.repositories.SoutienRepository;
import com.groupe1.collabdev_api.repositories.UtilisateurRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RecommendationService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private SoutienRepository soutienRepository;

    @Autowired
    private IdeeProjetRepository ideeProjetRepository;

    @Autowired
    private ProjetRepository projetRepository;

    public List<ResponseIdeeProjet> getRecommendedIdeas(int idUtilisateur) {
        Utilisateur utilisateur = utilisateurRepository.findById(idUtilisateur)
                .orElseThrow(
                        () -> new EntityNotFoundException("Utilisateur non trouvable avec cet id!")
                );
        List<Integer> idIdeesProjet = new ArrayList<>();
        List<Soutien> soutiens = soutienRepository.findByIdSoutienIdUtilisateur(idUtilisateur);
        for (Soutien soutien : soutiens) {
            idIdeesProjet.add(soutien.getIdSoutien().getIdIdeeProjet());
        }
        List<IdeeProjet> ideesProjetSoutenus = new ArrayList<>(ideeProjetRepository.findAllById(idIdeesProjet));
        List<String> preferences = utilisateur.getPreferences();
        List<IdeeProjet> ideeProjets = ideeProjetRepository.findAll();
        Set<IdeeProjet> recommendedIdeas = new HashSet<>();
        for (IdeeProjet ideesProjetSoutenu : ideesProjetSoutenus) {
            for (IdeeProjet ideeProjet : ideeProjets) {
                if (ideesProjetSoutenu.getDomaine().stream().anyMatch(ideeProjet.getDomaine()::contains)) {
                    recommendedIdeas.add(ideeProjet);
                }
            }
        }
        for (String preference : preferences) {
            for (IdeeProjet ideeProjet : ideeProjets) {
                if (ideeProjet.getTitre().toLowerCase().contains(preference.toLowerCase()) || ideeProjet.getDescription().toLowerCase().contains(preference.toLowerCase())) {
                    recommendedIdeas.add(ideeProjet);
                }
            }
        }
        recommendedIdeas.removeIf(ideeProjet -> ideeProjet.getUtilisateur().getId() == idUtilisateur);
        List<ResponseIdeeProjet> recommendedIdeasResponse = new ArrayList<>();
        for (IdeeProjet recommendedIdea : recommendedIdeas) {
            recommendedIdeasResponse.add(recommendedIdea.toResponse());
        }
        if(recommendedIdeasResponse.isEmpty()) {
            for (IdeeProjet ideeProjet : ideeProjets) {
                recommendedIdeasResponse.add(ideeProjet.toResponse());
            }
        }
        return recommendedIdeasResponse;
    }

    public List<ProjetDto> getRecommendedProjects(int idUtilisateur) {
        List<ResponseIdeeProjet> recommendedIdeas = getRecommendedIdeas(idUtilisateur);
        List<ProjetDto> recommendedProjects = new ArrayList<>();
        for (ResponseIdeeProjet recommendedIdea : recommendedIdeas) {
        }
        return new ArrayList<>();
    }
}
