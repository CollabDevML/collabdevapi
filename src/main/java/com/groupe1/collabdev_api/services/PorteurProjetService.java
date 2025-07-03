package com.groupe1.collabdev_api.services;

import com.groupe1.collabdev_api.entities.PorteurProjet;
import com.groupe1.collabdev_api.repositories.PorteurProjetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PorteurProjetService {

    @Autowired
    private PorteurProjetRepository porteurProjetRepository;

    public PorteurProjet chercherParId(int id){
        return porteurProjetRepository.findById(id).orElse(null);
    }

    public List<PorteurProjet> chercherTous(){
        return porteurProjetRepository.findAll();
    }

    public PorteurProjet ajouter(PorteurProjet porteurProjet){
        return porteurProjetRepository.save(porteurProjet);
    }

    public PorteurProjet modifier(PorteurProjet porteurProjet){
        return porteurProjetRepository.save(porteurProjet);
    }

    public Boolean supprimerParId(int id){
        porteurProjetRepository.deleteById(id);
        return true;
    }
}

