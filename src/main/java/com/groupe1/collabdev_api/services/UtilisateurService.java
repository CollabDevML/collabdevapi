package com.groupe1.collabdev_api.services;

import com.groupe1.collabdev_api.entities.Utilisateur;
import com.groupe1.collabdev_api.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public Utilisateur chercherParId(int id){
        return utilisateurRepository.findById(id).orElse(null);
    }

    public List<Utilisateur> chercherTous(){
        return utilisateurRepository.findAll();
    }

    public Utilisateur ajouter(Utilisateur utilisateur){
        return utilisateurRepository.save(utilisateur);
    }

    public Utilisateur modifier(Utilisateur utilisateur){
        return utilisateurRepository.save(utilisateur);
    }

    public Boolean supprimerParId(int id){
        utilisateurRepository.deleteById(id);
        return true;
    }
}

