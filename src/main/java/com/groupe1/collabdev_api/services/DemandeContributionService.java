package com.groupe1.collabdev_api.services;

import com.groupe1.collabdev_api.entities.DemandeContribution;
import com.groupe1.collabdev_api.repositories.DemandeContributionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemandeContributionService {

    @Autowired
    private DemandeContributionRepository demandeContributionRepository;

    public DemandeContribution chercherParId(int id){
        return demandeContributionRepository.findById(id).orElse(null);
    }

    public List<DemandeContribution> chercherTous(){
        return demandeContributionRepository.findAll();
    }

    public DemandeContribution ajouter(DemandeContribution demandeContribution){
        return demandeContributionRepository.save(demandeContribution);
    }

    public DemandeContribution modifier(DemandeContribution demandeContribution){
        return demandeContributionRepository.save(demandeContribution);
    }

    public Boolean supprimerParId(int id){
        demandeContributionRepository.deleteById(id);
        return true;
    }
}

