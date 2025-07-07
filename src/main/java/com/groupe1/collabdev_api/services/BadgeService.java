package com.groupe1.collabdev_api.services;

import com.groupe1.collabdev_api.entities.Badge;
import com.groupe1.collabdev_api.repositories.BadgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BadgeService {

    @Autowired
    private BadgeRepository badgeRepository;

    public Badge chercherParId(int id){
        return badgeRepository.findById(id).orElse(null);
    }

    public List<Badge> chercherTous(){
        return badgeRepository.findAll();
    }

    public Badge ajouter(Badge badge){
        return badgeRepository.save(badge);
    }

    public Badge modifier(Badge badge){
        return badgeRepository.save(badge);
    }

    public Boolean supprimerParId(int id){
        badgeRepository.deleteById(id);
        return true;
    }
}

