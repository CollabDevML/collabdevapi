package com.groupe1.collabdev_api.services;

import com.groupe1.collabdev_api.entities.Administrateur;
import com.groupe1.collabdev_api.entities.GestionAdminUtilisateur;
import com.groupe1.collabdev_api.entities.Utilisateur;
import com.groupe1.collabdev_api.entities.enums.Role;
import com.groupe1.collabdev_api.entities.enums.TypeGestionUtilisateurs;
import com.groupe1.collabdev_api.repositories.AdministrateurRepository;
import com.groupe1.collabdev_api.repositories.GestionAdminUtilisateurRepository;
import com.groupe1.collabdev_api.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
@Service
public class GestionAdminUtilisateurService {

    @Autowired
    private GestionAdminUtilisateurRepository gestionAdminUtilisateurRepository;
    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private AdministrateurRepository administrateurRepository;

    public Boolean supprimerParId(int id,int idA){
        Administrateur admin = administrateurRepository.findById(idA).orElse(null);
        Utilisateur utilisateur = utilisateurRepository.findById(id).orElse(null);
        if (admin == null || utilisateur == null){
            return false;
        }
        GestionAdminUtilisateur gestionAdminUtilisateur = new GestionAdminUtilisateur();
        gestionAdminUtilisateur.setAdministrateur(admin);
        gestionAdminUtilisateur.setUtilisateur(utilisateur);
        gestionAdminUtilisateur.setDateGestion(LocalDate.now());
        gestionAdminUtilisateur.setTypeGestion(TypeGestionUtilisateurs.SUPPRIMER);
        gestionAdminUtilisateurRepository.save(gestionAdminUtilisateur);

        utilisateurRepository.deleteById(id);
        return true;
    }

    //Pour bloquer un utilisateur :
    public Utilisateur bloquerUtilisateur(int id,int idAdmin) {
        Administrateur admin = administrateurRepository.findById(idAdmin).orElse(null);
        Utilisateur user = utilisateurRepository.findById(id).orElse(null);
        if (admin == null || user == null){
            return user;
        }
        GestionAdminUtilisateur gestionAdminUtilisateur = new GestionAdminUtilisateur();
        gestionAdminUtilisateur.setAdministrateur(admin);

        utilisateurRepository.findById(id).ifPresent(utilisateur -> {
            utilisateur.setEtat(false);
            gestionAdminUtilisateur.setUtilisateur(utilisateur);
            gestionAdminUtilisateur.setDateGestion(LocalDate.now());
            gestionAdminUtilisateur.setTypeGestion(TypeGestionUtilisateurs.BLOQUER);


            gestionAdminUtilisateurRepository.save(gestionAdminUtilisateur);
            utilisateurRepository.save(utilisateur);
        });
        return user;
    }

    //Pour bloquer un groupe d'utilisateur :
    public boolean bloquerParRole(Role role, int idAdmin){
        Administrateur admin = administrateurRepository.findById(idAdmin).orElse(null);
        AtomicBoolean verfier = new AtomicBoolean(false);
        if (admin == null ){
            return false;
        }
        GestionAdminUtilisateur gestionAdminUtilisateur = new GestionAdminUtilisateur();
        gestionAdminUtilisateur.setAdministrateur(admin);
        utilisateurRepository.findAll().forEach(utilisateur -> {
            if(utilisateur.getRole().equals(role)){
                utilisateur.setEtat(false);

                gestionAdminUtilisateur.setUtilisateur(utilisateur);
                gestionAdminUtilisateur.setDateGestion(LocalDate.now());
                gestionAdminUtilisateur.setTypeGestion(TypeGestionUtilisateurs.BLOQUER);

                gestionAdminUtilisateurRepository.save(gestionAdminUtilisateur);
                utilisateurRepository.save(utilisateur);
            }
            verfier.set(true);
        });
        return verfier.get();
    }


    //Debloquer les utilisateurs en fonction des roles :
    public boolean debloquerUtilisateurRole(Role role,int idAdmin){
        AtomicBoolean verfier = new AtomicBoolean(false);

        Administrateur admin = administrateurRepository.findById(idAdmin).orElse(null);
        if (admin == null){
            return false;
        }
        GestionAdminUtilisateur gestionAdminUtilisateur = new GestionAdminUtilisateur();
        gestionAdminUtilisateur.setAdministrateur(admin);


        utilisateurRepository.findAll().forEach(utilisateur -> {
            if(utilisateur.getRole().equals(role)){
                utilisateur.setEtat(true);
                gestionAdminUtilisateur.setUtilisateur(utilisateur);
                gestionAdminUtilisateur.setDateGestion(LocalDate.now());
                gestionAdminUtilisateur.setTypeGestion(TypeGestionUtilisateurs.DEBLOQUER);

                gestionAdminUtilisateurRepository.save(gestionAdminUtilisateur);
                utilisateurRepository.save(utilisateur);
            }
            verfier.set(true);
        });
        return verfier.get();
    }

    //Pour debloquer un utilisateur :
    public Utilisateur debloquerUtilisateur(int id,int idAdm) {
        Administrateur admin = administrateurRepository.findById(idAdm).orElse(null);
        Utilisateur user = utilisateurRepository.findById(id).orElse(null);
        if (admin == null || user == null){
            return user;
        }
        GestionAdminUtilisateur gestionAdminUtilisateur = new GestionAdminUtilisateur();
        utilisateurRepository.findById(id).ifPresent(utilisateur -> {
            utilisateur.setEtat(true);
            gestionAdminUtilisateur.setAdministrateur(admin);
            gestionAdminUtilisateur.setUtilisateur(utilisateur);
            gestionAdminUtilisateur.setDateGestion(LocalDate.now());
            gestionAdminUtilisateur.setTypeGestion(TypeGestionUtilisateurs.DEBLOQUER);

            gestionAdminUtilisateurRepository.save(gestionAdminUtilisateur);
            utilisateurRepository.save(utilisateur);
        });
        return user;
    }

    //Pour debloquer tout les utilisateurs :
    public List<Utilisateur> debloquerTousUtilisateur(int idAdmin) {
        Administrateur admin = administrateurRepository.findById(idAdmin).orElse(null);
        if (admin == null){
            return null;
        }
        GestionAdminUtilisateur gestionAdminUtilisateur = new GestionAdminUtilisateur();
        gestionAdminUtilisateur.setAdministrateur(admin);
        utilisateurRepository.findAll().forEach(utilisateur -> {
            if (!utilisateur.isEtat()){
                utilisateur.setEtat(true);
                gestionAdminUtilisateur.setUtilisateur(utilisateur);
                gestionAdminUtilisateur.setDateGestion(LocalDate.now());

                gestionAdminUtilisateur.setTypeGestion(TypeGestionUtilisateurs.DEBLOQUER);
                utilisateurRepository.save(utilisateur);
            }
        });
        return utilisateurRepository.findAll();
    }

    public List<Utilisateur> chercherParRole(Role role) {
        return utilisateurRepository.findUtilisateursByRole(role);
    }

    public List<Utilisateur> chercherTous(){
        return utilisateurRepository.findAll();
    }
}
