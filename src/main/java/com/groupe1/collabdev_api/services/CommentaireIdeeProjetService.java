package com.groupe1.collabdev_api.services;

import com.groupe1.collabdev_api.entities.CommentaireIdeeProjet;
import com.groupe1.collabdev_api.repositories.CommentaireIdeeProjetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentaireIdeeProjetService {

    @Autowired
    private CommentaireIdeeProjetRepository commentaireIdeeProjetRepository;

    public CommentaireIdeeProjet chercherParId(int id) {
        return commentaireIdeeProjetRepository.findById(id).orElse(null);
    }

    public List<CommentaireIdeeProjet> chercherTous() {
        return commentaireIdeeProjetRepository.findAll();
    }

    public CommentaireIdeeProjet ajouter(CommentaireIdeeProjet commentaireIdeeProjet) {
        return commentaireIdeeProjetRepository.save(commentaireIdeeProjet);
    }

    public CommentaireIdeeProjet modifier(CommentaireIdeeProjet commentaireIdeeProjet) {
        return commentaireIdeeProjetRepository.save(commentaireIdeeProjet);
    }

    public Boolean supprimerParId(int id) {
        commentaireIdeeProjetRepository.deleteById(id);
        return true;
    }
}
