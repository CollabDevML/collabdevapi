package com.groupe1.collabdev_api.services;

import com.groupe1.collabdev_api.dto.ProjectsCount;
import com.groupe1.collabdev_api.dto.response_dto.ResponseStats;
import com.groupe1.collabdev_api.dto.response_dto.UsersCount;
import com.groupe1.collabdev_api.entities.Administrateur;
import com.groupe1.collabdev_api.entities.Projet;
import com.groupe1.collabdev_api.entities.enums.Role;
import com.groupe1.collabdev_api.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministrateurService {

    @Autowired
    private AdministrateurRepository administrateurRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private ProjetRepository projetRepository;

    @Autowired
    private IdeeProjetRepository ideeProjetRepository;

    @Autowired
    private BadgeRepository badgeRepository;

    @Value("${spring.mail.username}")
    private String email;

    @Value("${application.password}")
    private String password;

    //Pour la creation de Super-Admin Par le systeme :

    public void superAdmin() {
        List<Administrateur> adminList = administrateurRepository.findAll();
        if (adminList.isEmpty()) {

            Administrateur admin = new Administrateur();
            admin.setEmail(email);
            admin.setPrenom("Kadiatou");
            admin.setNom("TALL");
            admin.setMotDePasse(BCrypt.hashpw(password, BCrypt.gensalt()));
            admin.setRole(Role.SUPER_ADMIN);
            administrateurRepository.save(admin);
        }
    }

    //Pour la mise a jour d'un administrateur :

    public ResponseEntity<?> updateAdmin(int id, Administrateur admin) {

        if (id == 0)
            return ResponseEntity.badRequest().body("Veuillez spécifier l'id");

        Administrateur existant = administrateurRepository.findById(id).orElse(null);
        if (existant == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Administrateur introuvable");

        existant.setEmail(admin.getEmail() != null ? admin.getEmail() : existant.getEmail());
        existant.setRole(admin.getRole() != null ? admin.getRole() : existant.getRole());
        if (admin.getMotDePasse() != null && !admin.getMotDePasse().isEmpty())
            existant.setMotDePasse(BCrypt.hashpw(admin.getMotDePasse(), BCrypt.gensalt()));

        Administrateur modifie = administrateurRepository.save(existant);
        return ResponseEntity.ok(modifie.toResponse());
    }

    public Administrateur chercherParId(int id) {
        return administrateurRepository.findById(id).orElse(null);
    }

    public Administrateur chercherParEmail(String email) {
        return administrateurRepository.findByEmail(email).orElse(null);
    }

    public List<Administrateur> chercherTous() {
        if (administrateurRepository.findAll().isEmpty()) {
            return null;
        }
        return administrateurRepository.findAll();
    }

    public Administrateur ajouter(Administrateur administrateur) {
        return administrateurRepository.save(administrateur);
    }

    public Administrateur modifier(Administrateur administrateur) {
        return administrateurRepository.save(administrateur);
    }

    public Boolean supprimerParId(int id) {
        administrateurRepository.deleteById(id);
        return true;
    }

    public ResponseStats getStats() {
        long contributeurs = utilisateurRepository.findUtilisateursByRole(Role.CONTRIBUTEUR).size();
        long gestionnaires = utilisateurRepository.findUtilisateursByRole(Role.GESTIONNAIRE).size();
        long porteursProjet = utilisateurRepository.findUtilisateursByRole(Role.PORTEUR_PROJET).size();

        UsersCount usersCount = new UsersCount(contributeurs, gestionnaires, porteursProjet);

        long finished = 0;
        long stoped = 0;
        long inProgress = 0;

        List<Projet> projets = projetRepository.findAll();
        for (Projet projet : projets) {
            if(projet.isEtat()) {
                if (projet.isEstFini()) {
                    finished++;
                } else {
                    inProgress++;
                }
            } else {
                stoped++;
            }
        }

        ProjectsCount projectsCount = new ProjectsCount(inProgress, stoped, finished);

        long adminsCount = administrateurRepository.count();

        long ideasCount = ideeProjetRepository.count();

        long badgesCount = badgeRepository.count();

        return new ResponseStats(
                adminsCount,
                usersCount,
                ideasCount,
                badgesCount,
                projectsCount
        );
    }
}
