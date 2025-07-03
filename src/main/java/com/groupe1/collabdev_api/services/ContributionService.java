package com.groupe1.collabdev_api.services;

import com.groupe1.collabdev_api.entities.Contribution;
import com.groupe1.collabdev_api.repositories.ContributionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}

