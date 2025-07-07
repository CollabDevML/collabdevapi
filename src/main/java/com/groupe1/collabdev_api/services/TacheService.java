package com.groupe1.collabdev_api.services;

import com.groupe1.collabdev_api.entities.Tache;
import com.groupe1.collabdev_api.repositories.TacheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TacheService {

    @Autowired
    private TacheRepository tacheRepository;

    public Tache chercherParId(int id){
        return tacheRepository.findById(id).orElse(null);
    }

    public List<Tache> chercherTous(){
        return tacheRepository.findAll();
    }

    public Tache ajouter(Tache tache){
        return tacheRepository.save(tache);
    }

    public Tache modifier(Tache tache){
        return tacheRepository.save(tache);
    }

    public Boolean supprimerParId(int id){
        tacheRepository.deleteById(id);
        return true;
    }
}
