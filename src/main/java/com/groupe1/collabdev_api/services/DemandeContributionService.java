package com.groupe1.collabdev_api.services;

import com.groupe1.collabdev_api.dto.DemandeContributionDto;
import com.groupe1.collabdev_api.entities.Contribution;
import com.groupe1.collabdev_api.entities.DemandeContribution;
import com.groupe1.collabdev_api.repositories.DemandeContributionRepository;
import com.groupe1.collabdev_api.utilities.MappingContribution;
import com.groupe1.collabdev_api.utilities.MappingDemandeContribution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.Mapping;

import java.util.List;
import java.util.Optional;

@Service
public class DemandeContributionService {

    @Autowired
    private DemandeContributionRepository demandeContributionRepository;

    public DemandeContributionDto chercherParId(int id){

        Optional<DemandeContribution> demandeContributionOptional  =  demandeContributionRepository.findById(id);
        return demandeContributionOptional.map(MappingDemandeContribution::ToDemandeDto).orElse(null);
    }

    public List<DemandeContributionDto> chercherTous(){

        List<DemandeContribution> demandeContributions = demandeContributionRepository.findAll();
        return MappingDemandeContribution.ToDemandeDtoToList(demandeContributions);
    }

    public DemandeContribution ajouter(DemandeContribution demandeContribution){
        return demandeContributionRepository.save(demandeContribution);
    }

    public DemandeContribution modifier(DemandeContribution demandeContribution){
        return demandeContributionRepository.save(demandeContribution);
    }
    //modifier une demande de contribution
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
    public List<DemandeContributionDto> chercherParContributeur(int idContributeur)
    {
        List<DemandeContribution> demandeContributions =  demandeContributionRepository
                .findByContributeur_Id(idContributeur);
        return MappingDemandeContribution.ToDemandeDtoToList(demandeContributions);
    }

    //lister demande par projet
    public List<DemandeContributionDto> chercherParProjet(int idProjet)
    {
        List<DemandeContribution> demandeContributions = demandeContributionRepository.findByProjet_Id(idProjet);
        return MappingDemandeContribution.ToDemandeDtoToList(demandeContributions);
    }

    //afficher la liste des demandes acceptées / refué d'un projet
    public List<DemandeContributionDto> chercherParEstAccepte(int idContributeur, int idProjet, boolean estAccepte)
    {
        List<DemandeContribution> demandeContributions;
        if(estAccepte)
        {
            demandeContributions = demandeContributionRepository.findByContributeur_IdAndProjet_IdAndEstAccepteTrue(
                    idContributeur, idProjet);
        }
        else {
            demandeContributions = demandeContributionRepository.findByContributeur_IdAndProjet_IdAndEstAccepteFalse(
                    idContributeur, idProjet
            );
        }
        return MappingDemandeContribution.ToDemandeDtoToList(demandeContributions);
    }

    public DemandeContribution accepterDemandeContribution(int idDemandeContribution)
    {
        DemandeContribution demandeContribution = demandeContributionRepository
                .findById(idDemandeContribution).orElse(null);
        if (demandeContribution == null)
        {
            return null;
        }
        demandeContribution.setEstAccepte(true);
        return demandeContributionRepository.save(demandeContribution);
    }
    public DemandeContribution refuserDemandeContribution(int idDemandeContribution)
    {
        DemandeContribution demandeContribution = demandeContributionRepository
                .findById(idDemandeContribution).orElse(null);
        if (demandeContribution == null)
        {
            return null;
        }
        demandeContribution.setEstAccepte(false);
        return demandeContributionRepository.save(demandeContribution);
    }
    public List<DemandeContributionDto> chercherToutesLesDemandesAcceptes(int idContributeur)
    {
        List<DemandeContribution> demandeContribution = demandeContributionRepository.findByContributeur_IdAndEstAccepteTrue(
                idContributeur);
        return MappingDemandeContribution.ToDemandeDtoToList(demandeContribution);
    }

    public List<DemandeContributionDto> chercherParContributeurEtParProjet(int idContributeur, int idProjet)
    {
        List<DemandeContribution> demandeContributions = demandeContributionRepository.findByContributeur_IdAndProjet_Id(
                idContributeur, idProjet);
        return MappingDemandeContribution.ToDemandeDtoToList(demandeContributions);
    }
}

