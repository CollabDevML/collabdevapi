package com.groupe1.collabdev_api.services;

import com.groupe1.collabdev_api.entities.GestionAdminProjet;
import com.groupe1.collabdev_api.repositories.GestionAdminProjetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GestionAdminProjetService {

    @Autowired
    private GestionAdminProjetRepository gestionAdminProjetRepository;

    public GestionAdminProjet chercherParId(int id){
        return gestionAdminProjetRepository.findById(id).orElse(null);
    }

    public List<GestionAdminProjet> chercherTous(){
        return gestionAdminProjetRepository.findAll();
    }

    public GestionAdminProjet ajouter(GestionAdminProjet gestionAdminProjet){
        return gestionAdminProjetRepository.save(gestionAdminProjet);
    }

    public GestionAdminProjet modifier(GestionAdminProjet gestionAdminProjet){
        return gestionAdminProjetRepository.save(gestionAdminProjet);
    }

    public Boolean supprimerParId(int id){
        gestionAdminProjetRepository.deleteById(id);
        return true;
    }
}
