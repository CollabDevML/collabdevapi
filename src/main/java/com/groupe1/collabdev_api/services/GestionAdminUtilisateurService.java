package com.groupe1.collabdev_api.services;

import com.groupe1.collabdev_api.entities.GestionAdminUtilisateur;
import com.groupe1.collabdev_api.repositories.GestionAdminUtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GestionAdminUtilisateurService {

    @Autowired
    private GestionAdminUtilisateurRepository gestionAdminUtilisateurRepository;

    public GestionAdminUtilisateur chercherParId(int id){
        return gestionAdminUtilisateurRepository.findById(id).orElse(null);
    }

    public List<GestionAdminUtilisateur> chercherTous(){
        return gestionAdminUtilisateurRepository.findAll();
    }

    public GestionAdminUtilisateur ajouter(GestionAdminUtilisateur gestionAdminUtilisateur){
        return gestionAdminUtilisateurRepository.save(gestionAdminUtilisateur);
    }

    public GestionAdminUtilisateur modifier(GestionAdminUtilisateur gestionAdminUtilisateur){
        return gestionAdminUtilisateurRepository.save(gestionAdminUtilisateur);
    }

    public Boolean supprimerParId(int id){
        gestionAdminUtilisateurRepository.deleteById(id);
        return true;
    }
}
