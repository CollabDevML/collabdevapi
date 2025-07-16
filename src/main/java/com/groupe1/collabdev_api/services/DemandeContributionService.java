package com.groupe1.collabdev_api.services;

import com.groupe1.collabdev_api.dto.DemandeContributionDto;
import com.groupe1.collabdev_api.entities.DemandeContribution;
import com.groupe1.collabdev_api.repositories.DemandeContributionRepository;
import com.groupe1.collabdev_api.utilities.MappingDemandeContribution;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DemandeContributionService {

    @Autowired
    private DemandeContributionRepository demandeContributionRepository;

    public DemandeContributionDto chercherParId(int id) {

        Optional<DemandeContribution> demandeContributionOptional = demandeContributionRepository.findById(id);
        return demandeContributionOptional.map(MappingDemandeContribution::ToDemandeDto).orElse(null);
    }

    public List<DemandeContributionDto> chercherTous() {

        List<DemandeContribution> demandeContributions = demandeContributionRepository.findAll();
        return MappingDemandeContribution.ToDemandeDtoToList(demandeContributions);
    }

    public DemandeContribution ajouter(DemandeContribution demandeContribution) throws RuntimeException {
        if (chercherParContributeurEtParProjet(demandeContribution.getContributeur().getId(),
                demandeContribution.getProjet().getId()).isEmpty()) {
            return demandeContributionRepository.save(demandeContribution);
        }
        throw new RuntimeException("La demande existe déjà!");
    }

    public DemandeContribution modifier(DemandeContribution demandeContribution) {
        return demandeContributionRepository.save(demandeContribution);
    }

    //modifier une demande de contribution
    public DemandeContribution modifier(int id, DemandeContribution demandeContribution) {
        demandeContribution.setId(id);
        return demandeContributionRepository.save(demandeContribution);
    }

    public Boolean supprimerParId(int id) {
        demandeContributionRepository.deleteById(id);
        return true;
    }

    //lister demande d'un contributeur
    public List<DemandeContributionDto> chercherParContributeur(int idContributeur) {
        List<DemandeContribution> demandeContributions = demandeContributionRepository
                .findByContributeur_Id(idContributeur);
        return MappingDemandeContribution.ToDemandeDtoToList(demandeContributions);
    }

    //lister demande par projet
    public List<DemandeContributionDto> chercherParProjet(int idProjet) {
        List<DemandeContribution> demandeContributions = demandeContributionRepository.findByProjet_Id(idProjet);
        return MappingDemandeContribution.ToDemandeDtoToList(demandeContributions);
    }

    //afficher la liste des demandes acceptées / refué d'un projet
    public List<DemandeContributionDto> chercherParEstAccepte(int idContributeur, int idProjet, boolean estAccepte) {
        List<DemandeContribution> demandeContributions;
        if (estAccepte) {
            demandeContributions = demandeContributionRepository.findByContributeur_IdAndProjet_IdAndEstAccepteeTrue(
                    idContributeur, idProjet);
        } else {
            demandeContributions = demandeContributionRepository.findByContributeur_IdAndProjet_IdAndEstAccepteeFalse(
                    idContributeur, idProjet
            );
        }
        return MappingDemandeContribution.ToDemandeDtoToList(demandeContributions);
    }

    public Optional<DemandeContribution> modifierEstAcceptee(int idDemandeContribution, boolean estAcceptee) throws RuntimeException {
        DemandeContribution demandeContribution = demandeContributionRepository
                .findById(idDemandeContribution).orElseThrow(
                        () -> new EntityNotFoundException("La demande est introuvable!")
                );
        if (demandeContribution.isEstAcceptee()) {
            throw new RuntimeException("Cette demande a déjà été acceptée!");
        }
        if (estAcceptee) {
            demandeContribution.setEstAcceptee(true);
            return Optional.of(demandeContributionRepository.save(demandeContribution));
        }
        demandeContributionRepository.deleteById(idDemandeContribution);
        return Optional.empty();
    }

    public DemandeContribution refuserDemandeContribution(int idDemandeContribution) {
        DemandeContribution demandeContribution = demandeContributionRepository
                .findById(idDemandeContribution).orElse(null);
        if (demandeContribution == null) {
            return null;
        }
        demandeContribution.setEstAcceptee(false);
        return demandeContributionRepository.save(demandeContribution);
    }

    public List<DemandeContributionDto> chercherToutesLesDemandesAcceptes(int idContributeur) {
        List<DemandeContribution> demandeContribution = demandeContributionRepository.findByContributeur_IdAndEstAccepteeTrue(
                idContributeur);
        return MappingDemandeContribution.ToDemandeDtoToList(demandeContribution);
    }

    public Optional<DemandeContribution> chercherParContributeurEtParProjet(int idContributeur, int idProjet) {
        return demandeContributionRepository.findByContributeur_IdAndProjet_Id(
                idContributeur, idProjet);
    }

    public List<DemandeContribution> chercherParIdContributeurEtc(
            int idContributeur,
            boolean estAcceptee
    ) {
        return demandeContributionRepository.findByContributeurIdAndEstAcceptee(
                idContributeur,
                estAcceptee
        );
    }

    @Transactional
    public int supprimerParContributeurEtParProjet(
            int idContributeur, int idProjet
    ) {
        return demandeContributionRepository.deleteByContributeur_IdAndProjet_IdAndEstAccepteeTrue(idContributeur, idProjet);
    }
}

