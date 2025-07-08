package com.groupe1.collabdev_api.services;

import com.groupe1.collabdev_api.dto.ContributeurDto;
import com.groupe1.collabdev_api.entities.Contributeur;
import com.groupe1.collabdev_api.repositories.ContributeurRepository;
import com.groupe1.collabdev_api.utilities.MappingContributeur;
import com.groupe1.collabdev_api.utilities.MappingContribution;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ContributeurService {

    @Autowired
    private ContributeurRepository contributeurRepository;

    public ResponseEntity<?> chercherParId(int id){
        Contributeur contributeur = contributeurRepository.findById(id).orElse(null);
        if(contributeur == null)
        {
            return ResponseEntity.ok("contributeur non trouvé");
        }
        return ResponseEntity.ok(MappingContributeur.contributeurToDto(contributeur));

    }

    public List<Contributeur> chercherTous(){
        return contributeurRepository.findAll();
    }

    public  Contributeur ajouter(Contributeur contributeur){
        return contributeurRepository.save(contributeur);
    }

    //modifier un contributeur
    public Contributeur modifier(Contributeur contributeur){
        return contributeurRepository.save(contributeur);
    }

    //modifier un contributeur, ne marche pas
    public Contributeur modifier(int id, ContributeurDto dto) {
        Contributeur existant = contributeurRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Contributeur non trouvé avec l'id : " + id));

        // Mise à jour uniquement des champs non-nuls
        if (dto.getNiveau() != null) {
            existant.setNiveau(dto.getNiveau());
        }
        if (dto.getSpecialite() != null) {
            existant.setSpecialite(dto.getSpecialite());
        }
        if (dto.getType() != null) {
            existant.setType(dto.getType());
        }
        if (dto.getPieces() != 0) {
            existant.setPieces(dto.getPieces());
        }
        if (dto.getUriCv() != null) {
            existant.setUriCv(dto.getUriCv());
        }

        return contributeurRepository.save(existant);
    }


    public Boolean supprimerParId(int id){
        contributeurRepository.deleteById(id);
        return true;
    }

    public ContributeurDto chercherContributeurParId(int id)
    {
        Optional<Contributeur> optional = contributeurRepository.findById(id);
        return optional.map(MappingContributeur::contributeurToDto)
                .orElseThrow(()-> new RuntimeException("Contributeur non trouvé"));
    }

    public List<ContributeurDto> chercherTousLesContributeurs()
    {
        List<Contributeur> contributeurList = contributeurRepository.findAll();
        List<ContributeurDto> contributeurDtoList = new ArrayList<>();
        for(Contributeur contributeur : contributeurList)
        {
            contributeurDtoList.add(MappingContributeur.contributeurToDto(contributeur));
        }
        return contributeurDtoList;
    }

}
