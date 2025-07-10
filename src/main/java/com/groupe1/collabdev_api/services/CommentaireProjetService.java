package com.groupe1.collabdev_api.services;

import com.groupe1.collabdev_api.entities.CommentaireProjet;
import com.groupe1.collabdev_api.repositories.CommentaireProjetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentaireProjetService {

    @Autowired
    private CommentaireProjetRepository commentaireProjetRepository;

    public CommentaireProjet chercherParId(int id){
        return commentaireProjetRepository.findById(id).orElse(null);
    }

    public List<CommentaireProjet> chercherTous(){
        return commentaireProjetRepository.findAll();
    }

    public CommentaireProjet ajouter(CommentaireProjet commentaireProjet){
        return commentaireProjetRepository.save(commentaireProjet);
    }

    public CommentaireProjet modifier(CommentaireProjet commentaireProjet){
        return commentaireProjetRepository.save(commentaireProjet);
    }

    public Boolean supprimerParId(int id){
        commentaireProjetRepository.deleteById(id);
        return true;
    }
}

