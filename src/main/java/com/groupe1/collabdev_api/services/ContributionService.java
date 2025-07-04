package com.groupe1.collabdev_api.services;

import com.groupe1.collabdev_api.entities.Contribution;
import com.groupe1.collabdev_api.repositories.ContributionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContributionService {

    @Autowired
    private ContributionRepository contributionRepository;

    public Contribution chercherParId(int id){
        return contributionRepository.findById(id).orElse(null);
    }

    public List<Contribution> chercherTous(){
        return contributionRepository.findAll();
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

    public Contribution modifier(int id, Contribution contribution)
    {
        contribution.setId(id);
        return contributionRepository.save(contribution);
    }
    //liste toutes les contributions d'un contributeur
    public List<Contribution> chercherParContributeurId(int idContributeur)
    {
        return contributionRepository.findByContributeur_Id(idContributeur);
    }
    //lister toutes les contributions validées ou non validée d'un contributeur
    public List<Contribution> chercherContributionValide(int idContributeur, Boolean valide)
    {
        List<Contribution> contributionList = contributionRepository.findByContributeur_Id(idContributeur);
        List<Contribution> contributionValide = new ArrayList<>();
        List<Contribution> contributionNonValide = new ArrayList<>();
        for(Contribution contribution : contributionList)
        {
            if(contribution.isEstValide())
            {
                contributionValide.add(contribution);
            }
            else {
                contributionNonValide.add(contribution);
            }
        }
        if(valide)
        {
            return contributionValide;
        }
        else
        {
            return contributionNonValide;
        }
    }

    //lister toutes les contributions d'un projet
    public List<Contribution> chercherParProjetId(int idProjet)
    {
        return contributionRepository.findByProjet_Id(idProjet);
    }
}

