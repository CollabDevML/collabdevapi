package com.groupe1.collabdev_api.services;

import com.groupe1.collabdev_api.entities.Administrateur;
import com.groupe1.collabdev_api.repositories.AdministrateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministrateurService {

    @Autowired
    private AdministrateurRepository administrateurRepository;

    public Administrateur chercherParId(int id){
        return administrateurRepository.findById(id).orElse(null);
    }

    public List<Administrateur> chercherTous(){
        return administrateurRepository.findAll();
    }

    public Administrateur ajouter(Administrateur administrateur){
        return administrateurRepository.save(administrateur);
    }

    public Administrateur modifier(Administrateur administrateur){
        return administrateurRepository.save(administrateur);
    }

    public Boolean supprimerParId(int id){
        administrateurRepository.deleteById(id);
        return true;
    }
}
