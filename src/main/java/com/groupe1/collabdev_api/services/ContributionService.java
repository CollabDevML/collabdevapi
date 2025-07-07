package com.groupe1.collabdev_api.services;

import com.groupe1.collabdev_api.dto.ContributionDto;
import com.groupe1.collabdev_api.entities.Contribution;
import com.groupe1.collabdev_api.repositories.ContributionRepository;
import com.groupe1.collabdev_api.utilities.MappingContribution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    //modifier une contribution ne marche pas
    public Contribution modifier(int id, Contribution contribution)
    {
        contribution.setId(id);
        return contributionRepository.save(contribution);
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

    //quitter un projet
    public List<ContributionDto> quitterUnProjet(int idContributeur, int idProjet)
    {
        List<Contribution> contributions = contributionRepository
                .deleteByContributeur_IdAndProjet_Id(idContributeur, idProjet);
        return MappingContribution.contributionDtoList(contributions);
    }
}

