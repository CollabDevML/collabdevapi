package com.groupe1.collabdev_api.services;

import com.groupe1.collabdev_api.dto.ContributionDto;
import com.groupe1.collabdev_api.entities.Badge;
import com.groupe1.collabdev_api.entities.Contributeur;
import com.groupe1.collabdev_api.entities.Contribution;
import com.groupe1.collabdev_api.entities.ObtentionBadge;
import com.groupe1.collabdev_api.repositories.BadgeRepository;
import com.groupe1.collabdev_api.repositories.ContributionRepository;
import com.groupe1.collabdev_api.utilities.MappingContribution;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ContributionService {

    @Autowired
    private ContributionRepository contributionRepository;

    @Autowired
    private ContributeurService contributeurService;

    @Autowired
    private ObtentionBadgeService obtentionBadgeService;

    @Autowired
    private BadgeService badgeService;

    @Autowired
    private BadgeRepository badgeRepository;

    public ResponseEntity<?> chercherParId(int id) {
        Contribution contribtution = contributionRepository.findById(id).orElse(null);
        if (contribtution == null) {
            return ResponseEntity.ok("Contribution non trouvée");
        }
        return ResponseEntity.ok(MappingContribution.ContributionToDto(contribtution));
    }

    public List<ContributionDto> chercherTous() {

        List<Contribution> contributions = contributionRepository.findAll();
        return MappingContribution.contributionDtoList(contributions);
    }

    public List<ContributionDto> chercherTousLesContributions() {
        List<Contribution> contributions = contributionRepository.findAll();
        return MappingContribution.contributionDtoList(contributions);
    }

    public Contribution ajouter(Contribution contribution) {
        return contributionRepository.save(contribution);

    }

    public Contribution modifier(Contribution contribution) {
        return contributionRepository.save(contribution);
    }

    public Boolean supprimerParId(int id) {
        contributionRepository.deleteById(id);
        return true;
    }

    @Transactional
    public ContributionDto validerContribution(int id) {
        Contribution existante = contributionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Contribution non trouvée avec l'id : " + id));
        existante.setEstValide(true);
        Contribution contribution = contributionRepository.save(existante);
        gagnerPiece(contribution);
        attribution(contribution.getContributeur().getId());
        return contribution.toContributeurDto();
    }

    private void gagnerPiece(Contribution contribution) {
        Contributeur contributeur = contribution.getContributeur();
        contributeur.setPieces(contributeur.getPieces() + contribution.getTache().getPieceAGagner());
        contributeurService.modifier(contributeur);
    }

    //liste toutes les contributions d'un contributeur
    public List<ContributionDto> chercherParContributeurId(int idContributeur) {
        List<Contribution> contribution = contributionRepository.findByContributeur_Id(idContributeur);
        return MappingContribution.contributionDtoList(contribution);
    }

    //lister toutes les contributions validées ou non validée d'un contributeur dans un projet
    public List<ContributionDto> chercherContributionValide(int idContributeur, int idProjet, Boolean valide) {
        List<Contribution> contributions;
        if (valide) {
            contributions = contributionRepository.findByContributeur_IdAndProjet_IdAndEstValideTrue(
                    idContributeur, idProjet);
        } else {
            contributions = contributionRepository.findByContributeur_IdAndProjet_IdAndEstValideFalse(idContributeur, idProjet);
        }
        return Contribution.toContributeurDtoList(contributions);
    }

    //lister toutes les contributions d'un projet
    public List<ContributionDto> chercherParProjetId(int idProjet) {
        List<Contribution> contributions = contributionRepository.findByProjet_Id(idProjet);
        return MappingContribution.contributionDtoList(contributions);
    }

    //lister ces projets
//    public List<ProjetDto> listerProjetsDuContributeur(int idContributeur) {
//        List<Contribution> contributions = contributionRepository.findByContributeur_Id(idContributeur);
//        return contributions.stream()
//                .map(Contribution::getProjet)
//                .distinct() // Pour éviter les doublons
//                .toList();
//    }

    //quitter un projet
    public List<ContributionDto> quitterUnProjet(int idContributeur, int idProjet) {
        List<Contribution> contributions = contributionRepository
                .deleteByContributeur_IdAndProjet_Id(idContributeur, idProjet);
        return MappingContribution.contributionDtoList(contributions);
    }


    @Transactional
    public void attribution(int idContributeur) {
        List<ContributionDto> contributions = chercherParContributeurId(idContributeur);
        AtomicInteger atomicInteger = new AtomicInteger(0);
        contributions.forEach(contributionDto -> {
                    if (contributionDto.getEstValide()) {
                        atomicInteger.getAndIncrement();
                    }
                }
        );
        Contributeur contributeur = contributeurService.chercherParId(idContributeur);
        if (contributeur == null) {
            return;
        }
        int nombreValide = atomicInteger.get();
        if (nombreValide >= 2 && nombreValide < 5) {
            attribuerBadge(badgeRepository.findAll().getFirst().getTitre(), contributeur);
        } else if (nombreValide >= 5 && nombreValide < 10) {
            attribuerBadge(badgeRepository.findAll().get(1).getTitre(), contributeur);
        } else if (nombreValide >= 10 && nombreValide < 20) {
            attribuerBadge(badgeRepository.findAll().get(2).getTitre(), contributeur);
        } else if (nombreValide >= 20 && nombreValide < 50) {
            attribuerBadge(badgeRepository.findAll().get(3).getTitre(), contributeur);
        } else if (nombreValide >= 50) {
            attribuerBadge(badgeRepository.findAll().get(4).getTitre(), contributeur);
        }
    }

    private void attribuerBadge(String badgeTitle, Contributeur contributeur) {
        Badge badge = badgeRepository.findByTitre(
                badgeTitle
        );
        if (obtentionBadgeService.chercherParBadgeIdAndContributeurId(badge.getId(), contributeur.getId()).isEmpty()) {
            obtentionBadgeService.ajouter(
                    new ObtentionBadge(
                            0,
                            LocalDate.now(),
                            contributeur,
                            badge
                    )
            );
        }
    }

}

