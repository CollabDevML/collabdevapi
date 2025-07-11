package com.groupe1.collabdev_api.services;

import com.groupe1.collabdev_api.entities.CommentaireProjet;
import com.groupe1.collabdev_api.entities.Projet;
import com.groupe1.collabdev_api.entities.Utilisateur;
import com.groupe1.collabdev_api.repositories.CommentaireProjetRepository;
import com.groupe1.collabdev_api.repositories.ProjetRepository;
import com.groupe1.collabdev_api.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.groupe1.collabdev_api.controllers.response_entities.Commentaire;

import java.util.List;

@Service
public class CommentaireProjetService {

    @Autowired
    private CommentaireProjetRepository commentaireProjetRepository;
    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private ProjetRepository projetRepository;

    public CommentaireProjet chercherParId(int id){
        return commentaireProjetRepository.findById(id).orElse(null);
    }

    public List<CommentaireProjet> chercherTous(){
        return commentaireProjetRepository.findAll();
    }

    public CommentaireProjet ajouter(CommentaireProjet commentaireProjet){
        Utilisateur user=utilisateurRepository.findById(commentaireProjet.getUtilisateur().getId()).orElseThrow(
                () -> new RuntimeException("Ce utilisateur n'existe pas")
        );
        Projet projet=projetRepository.findById(commentaireProjet.getProjet().getId()).orElseThrow(
                () ->new RuntimeException("Ce projet n'existe pas")
        );

        CommentaireProjet commentaire=new CommentaireProjet();
        commentaire.setUtilisateur(user);
        commentaire.setProjet(projet);
        commentaire.setDateCommentaire(commentaireProjet.getDateCommentaire());
        commentaire.setContenu(commentaireProjet.getContenu());
        return commentaireProjetRepository.save(commentaire);
    }

    public CommentaireProjet modifier(int id,Commentaire commentaireProjet){
        CommentaireProjet commentaireProjetmod = commentaireProjetRepository.findById(id).
                orElseThrow(() -> new RuntimeException("commentaire non trouvé"));
        if (commentaireProjet.getUser()!=null){
            commentaireProjetmod.setUtilisateur(commentaireProjet.getUser());
        }
        if (commentaireProjet.getProjet()!=null){
            commentaireProjetmod.setProjet(commentaireProjet.getProjet());
        }
        if (commentaireProjet.getContenu()!=null){
            commentaireProjetmod.setContenu(commentaireProjet.getContenu());
        }
        return commentaireProjetRepository.save(commentaireProjetmod);


    }

    public Boolean supprimerParId(int id){
        commentaireProjetRepository.deleteById(id);
        return true;
    }
}

