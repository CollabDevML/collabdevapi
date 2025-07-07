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
    //lister toutes les contributions validées ou non validée d'un contributeur dans un projet
    public List<Contribution> chercherContributionValide(int idContributeur,int idProjet, Boolean valide)
    {
        if(valide)
        {
            return contributionRepository.findByContributeur_IdAndProjet_IdAndEstValideTrue(
                    idContributeur,idProjet );
        }
        else {
            return contributionRepository.findByContributeur_IdAndProjet_IdAndEstValideFalse(idContributeur, idProjet);
        }
    }

    //lister toutes les contributions d'un projet
    public List<Contribution> chercherParProjetId(int idProjet)
    {
        return contributionRepository.findByProjet_Id(idProjet);
    }

    //quitter un projet
    public List<Contribution> quitterUnProjet(int idContributeur, int idProjet)
    {
        return contributionRepository.deleteByContributeur_IdAndProjet_Id(idContributeur, idProjet);
    }
}

