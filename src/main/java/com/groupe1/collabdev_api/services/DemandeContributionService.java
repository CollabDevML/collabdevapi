package com.groupe1.collabdev_api.services;

import com.groupe1.collabdev_api.entities.DemandeContribution;
import com.groupe1.collabdev_api.repositories.DemandeContributionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemandeContributionService {

    @Autowired
    private DemandeContributionRepository demandeContributionRepository;

    public DemandeContribution chercherParId(int id){
        return demandeContributionRepository.findById(id).orElse(null);
    }

    public List<DemandeContribution> chercherTous(){
        return demandeContributionRepository.findAll();
    }

    public DemandeContribution ajouter(DemandeContribution demandeContribution){
        return demandeContributionRepository.save(demandeContribution);
    }

    public DemandeContribution modifier(DemandeContribution demandeContribution){
        return demandeContributionRepository.save(demandeContribution);
    }

    public DemandeContribution modifier(int id, DemandeContribution demandeContribution)
    {
        demandeContribution.setId(id);
        return demandeContributionRepository.save(demandeContribution);
    }

    public Boolean supprimerParId(int id){
        demandeContributionRepository.deleteById(id);
        return true;
    }

    //lister demande d'un contributeur
    public List<DemandeContribution> chercherParContributeur(int idContributeur)
    {
        return demandeContributionRepository.findByContributeur_Id(idContributeur);
    }

    //lister demande par projet
    public List<DemandeContribution> chercherParProjet(int idProjet)
    {
        return demandeContributionRepository.findByProjet_Id(idProjet);
    }

    //afficher la liste des demandes acceptées / refué d'un projet
    public List<DemandeContribution> chercherParEstAccepte(int idContributeur, int idProjet, boolean estAccepte)
    {
        if(estAccepte)
        {
            return demandeContributionRepository.findByContributeur_IdAndProjet_IdAndEstAccepteTrue(
                    idContributeur, idProjet);
        }
        else {
            return demandeContributionRepository.findByContributeur_IdAndProjet_IdAndEstAccepteFalse(
                    idContributeur, idProjet
            );
        }
    }

    public DemandeContribution accepterDemandeContribution(int idDemandeContribution)
    {
        DemandeContribution demandeContribution = chercherParId(idDemandeContribution);
        if (demandeContribution == null)
        {
            return null;
        }
        demandeContribution.setEstAccepte(true);
        return demandeContributionRepository.save(demandeContribution);
    }
    public DemandeContribution refuserDemandeContribution(int idDemandeContribution)
    {
        DemandeContribution demandeContribution = chercherParId(idDemandeContribution);
        if (demandeContribution == null)
        {
            return null;
        }
        demandeContribution.setEstAccepte(false);
        return demandeContributionRepository.save(demandeContribution);
    }
    public List<DemandeContribution> chercherToutesLesDemandesAcceptes(int idContributeur)
    {
        return demandeContributionRepository.findByContributeur_IdAndEstAccepteTrue(
                idContributeur);
    }

    public List<DemandeContribution> chercherParContributeurEtParProjet(int idContributeur, int idProjet)
    {
        return demandeContributionRepository.findByContributeur_IdAndProjet_Id(
                idContributeur, idProjet);
    }
}

