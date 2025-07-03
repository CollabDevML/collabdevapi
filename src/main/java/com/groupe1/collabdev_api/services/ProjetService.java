package com.groupe1.collabdev_api.services;

import com.groupe1.collabdev_api.entities.Projet;
import com.groupe1.collabdev_api.repositories.ProjetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjetService {

    @Autowired
    private ProjetRepository projetRepository;

    public Projet chercherParId(int id){
        return projetRepository.findById(id).orElse(null);
    }

    public List<Projet> chercherTous(){
        return projetRepository.findAll();
    }

    public Projet ajouter(Projet projet){
        return projetRepository.save(projet);
    }

    public Projet modifier(Projet projet){
        return projetRepository.save(projet);
    }

    public Boolean supprimerParId(int id){
        projetRepository.deleteById(id);
        return true;
    }
}

