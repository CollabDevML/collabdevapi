package com.groupe1.collabdev_api.services;

import com.groupe1.collabdev_api.entities.Gestionnaire;
import com.groupe1.collabdev_api.dto.response_dto.ResponseGestionnaire;
import com.groupe1.collabdev_api.repositories.GestionnaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GestionnaireService {

    @Autowired
    private GestionnaireRepository gestionnaireRepository;

    public Gestionnaire chercherParId(int id){
        return gestionnaireRepository.findById(id).orElse(null);
    }

    public List<Gestionnaire> chercherTous(){
        return gestionnaireRepository.findAll();
    }

    public List<ResponseGestionnaire> chercherTousParEstValide(boolean estValide) {
        List<Gestionnaire> gestionnaires = gestionnaireRepository.findAllByEstValide(estValide);
        List<ResponseGestionnaire> responseGestionnaires = new ArrayList<>();
        for (Gestionnaire gestionnaire : gestionnaires){
            responseGestionnaires.add(
                    new ResponseGestionnaire(
                            gestionnaire.getUtilisateur().getId(),
                            gestionnaire.getUtilisateur().getPrenom(),
                            gestionnaire.getUtilisateur().getNom(),
                            gestionnaire.getUtilisateur().getEmail(),
                            gestionnaire.getUtilisateur().getMotDePasse(),
                            gestionnaire.getUtilisateur().getGenre(),
                            gestionnaire.getUriCv(),
                            gestionnaire.isEstValide()
                    )
            );
        }
        return responseGestionnaires;
    }

    public Gestionnaire ajouter(Gestionnaire gestionnaire){
        return gestionnaireRepository.save(gestionnaire);
    }

    public Gestionnaire modifier(Gestionnaire gestionnaire){
        return gestionnaireRepository.save(gestionnaire);
    }

    public Boolean supprimerParId(int id){
        gestionnaireRepository.deleteById(id);
        return true;
    }
}

