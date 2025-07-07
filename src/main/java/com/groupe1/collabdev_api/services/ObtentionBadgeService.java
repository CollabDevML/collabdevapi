package com.groupe1.collabdev_api.services;

import com.groupe1.collabdev_api.entities.ObtentionBadge;
import com.groupe1.collabdev_api.repositories.ObtentionBadgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ObtentionBadgeService {

    @Autowired
    private ObtentionBadgeRepository obtentionBadgeRepository;

    public ObtentionBadge chercherParId(int id){
        return obtentionBadgeRepository.findById(id).orElse(null);
    }

    public List<ObtentionBadge> chercherTous(){
        return obtentionBadgeRepository.findAll();
    }

    public ObtentionBadge ajouter(ObtentionBadge obtentionBadge){
        return obtentionBadgeRepository.save(obtentionBadge);
    }

    public ObtentionBadge modifier(ObtentionBadge obtentionBadge){
        return obtentionBadgeRepository.save(obtentionBadge);
    }

    public Boolean supprimerParId(int id){
        obtentionBadgeRepository.deleteById(id);
        return true;
    }
}
