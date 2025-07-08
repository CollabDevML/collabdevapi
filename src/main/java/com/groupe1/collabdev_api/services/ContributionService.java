package com.groupe1.collabdev_api.services;

import com.groupe1.collabdev_api.dto.ContributionDto;
import com.groupe1.collabdev_api.entities.Contribution;
import com.groupe1.collabdev_api.entities.Projet;
import com.groupe1.collabdev_api.repositories.ContributionRepository;
import com.groupe1.collabdev_api.utilities.MappingContribution;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContributionService {

    @Autowired
    private ContributionRepository contributionRepository;

    public ContributionDto chercherParId(int id){

        Optional<Contribution> contributionOptional =  contributionRepository.findById(id);
        return contributionOptional.map(MappingContribution::ContributionToDto).orElse(null);
    }

    public List<ContributionDto> chercherTous(){

        List<Contribution> contributions = contributionRepository.findAll();
        return MappingContribution.contributionDtoList(contributions);
    }

    public List<ContributionDto> chercherTousLesContributions(){
        List<Contribution> contributions = contributionRepository.findAll();
        return MappingContribution.contributionDtoList(contributions);
    }

    public Contribution ajouter(Contribution contribution){
        return contributionRepository.save(contribution);

    }

    public Contribution modifier(Contribution contribution){
        return contributionRepository.save(contribution);
    }

    public Boolean supprimerParId(int id){
        contributionRepository.deleteById(id);
        return true;
    }

    public ContributionDto modifier(int id, ContributionDto dto) {
        Contribution existante = contributionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Contribution non trouvée avec l'id : " + id));
        if(dto.getEstValide()!=null)
        {
            existante.setEstValide(dto.getEstValide());
        }
        return MappingContribution.ContributionToDto(contributionRepository.save(existante));
    }

    //liste toutes les contributions d'un contributeur
    public List<ContributionDto> chercherParContributeurId(int idContributeur)
    {
        List<Contribution> contribution = contributionRepository.findByContributeur_Id(idContributeur);
        return MappingContribution.contributionDtoList(contribution);
    }
    //lister toutes les contributions validées ou non validée d'un contributeur dans un projet
    public List<ContributionDto> chercherContributionValide(int idContributeur, int idProjet, Boolean valide)
    {
        List<Contribution> contributions = new ArrayList<>();
        if(valide)
        {
            contributions = contributionRepository.findByContributeur_IdAndProjet_IdAndEstValideTrue(
                    idContributeur,idProjet );
        }
        else {
            contributions = contributionRepository.findByContributeur_IdAndProjet_IdAndEstValideFalse(idContributeur, idProjet);
        }
        return MappingContribution.contributionDtoList(contributions);
    }

    //lister toutes les contributions d'un projet
    public List<ContributionDto> chercherParProjetId(int idProjet)
    {
        List<Contribution> contributions = contributionRepository.findByProjet_Id(idProjet);
        return MappingContribution.contributionDtoList(contributions);
    }
    //lister ces projets
    public List<Projet> listerProjetsDuContributeur(int idContributeur) {
        List<Contribution> contributions = contributionRepository.findByContributeur_Id(idContributeur);
        return contributions.stream()
                .map(Contribution::getProjet)
                .distinct() // Pour éviter les doublons
                .collect(Collectors.toList());
    }


    //quitter un projet
    public List<ContributionDto> quitterUnProjet(int idContributeur, int idProjet)
    {
        List<Contribution> contributions = contributionRepository
                .deleteByContributeur_IdAndProjet_Id(idContributeur, idProjet);
        return MappingContribution.contributionDtoList(contributions);
    }
}

