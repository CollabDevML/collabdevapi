package com.groupe1.collabdev_api.services;

import com.groupe1.collabdev_api.entities.Gestionnaire;
import com.groupe1.collabdev_api.repositories.GestionnaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GestionnaireService {

    @Autowired
    private GestionnaireRepository gestionnaireRepository;

    public Gestionnaire chercherParId(int id){
        return gestionnaireRepository.findById(id).orElse(null);
    }

    public List<Gestionnaire> chercherTous(){
        return gestionnaireRepository.findAll();
    }

    public Gestionnaire ajouter(Gestionnaire gestionnaire){
        return gestionnaireRepository.save(gestionnaire);
    }

    public Gestionnaire modifier(Gestionnaire gestionnaire){
        return gestionnaireRepository.save(gestionnaire);
    }

    public Boolean supprimerParId(int id){
        gestionnaireRepository.deleteById(id);
        return true;
    }
}

