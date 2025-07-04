package com.groupe1.collabdev_api.services;

import com.groupe1.collabdev_api.entities.Contributeur;
import com.groupe1.collabdev_api.repositories.ContributeurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContributeurService {

    @Autowired
    private ContributeurRepository contributeurRepository;

    public Contributeur chercherParId(int id){
        return contributeurRepository.findById(id).orElse(null);
    }

    public List<Contributeur> chercherTous(){
        return contributeurRepository.findAll();
    }

    public Contributeur ajouter(Contributeur contributeur){
        return contributeurRepository.save(contributeur);
    }

    public Contributeur modifier(Contributeur contributeur){
        return contributeurRepository.save(contributeur);
    }

    public Contributeur modifier(int id, Contributeur contributeur)
    {
        contributeur.setId(id);
        return contributeurRepository.save(contributeur);
    }

    public Boolean supprimerParId(int id){
        contributeurRepository.deleteById(id);
        return true;
    }
}
