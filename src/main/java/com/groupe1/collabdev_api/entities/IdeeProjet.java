package com.groupe1.collabdev_api.entities;

import com.groupe1.collabdev_api.dto.response_dto.ResponseCommentaireIdeeProjet;
import com.groupe1.collabdev_api.dto.response_dto.ResponseIdeeProjet;
import com.groupe1.collabdev_api.dto.response_dto.ResponseIdeeProjet2;
import com.groupe1.collabdev_api.dto.response_dto.ResponseUser;
import com.groupe1.collabdev_api.entities.enums.DomaineIdeeProjet;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter

@Table(name = "idees_projet")
public class IdeeProjet {
    public IdeeProjet() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<DomaineIdeeProjet> getDomaine() {
        return domaine;
    }

    public void setDomaine(List<DomaineIdeeProjet> domaine) {
        this.domaine = domaine;
    }

    public String getUriCDC() {
        return uriCDC;
    }

    public void setUriCDC(String uriCDC) {
        this.uriCDC = uriCDC;
    }

    public int getNombreDeSoutien() {
        return nombreDeSoutien;
    }

    public void setNombreDeSoutien(int nombreDeSoutien) {
        this.nombreDeSoutien = nombreDeSoutien;
    }

    public LocalDateTime getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(LocalDateTime datePublication) {
        this.datePublication = datePublication;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Projet getProjet() {
        return projet;
    }

    public void setProjet(Projet projet) {
        this.projet = projet;
    }

    public List<CommentaireIdeeProjet> getCommentairesIdeeProjet() {
        return commentairesIdeeProjet;
    }

    public void setCommentairesIdeeProjet(List<CommentaireIdeeProjet> commentairesIdeeProjet) {
        this.commentairesIdeeProjet = commentairesIdeeProjet;
    }

    public IdeeProjet(int id, String titre, String description, List<DomaineIdeeProjet> domaine, String uriCDC, int nombreDeSoutien, LocalDateTime datePublication, Utilisateur utilisateur, Projet projet, List<CommentaireIdeeProjet> commentairesIdeeProjet) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.domaine = domaine;
        this.uriCDC = uriCDC;
        this.nombreDeSoutien = nombreDeSoutien;
        this.datePublication = datePublication;
        this.utilisateur = utilisateur;
        this.projet = projet;
        this.commentairesIdeeProjet = commentairesIdeeProjet;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String titre;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @ElementCollection
    @CollectionTable(name = "domaines_idees_projet")
    private List<DomaineIdeeProjet> domaine;

    private String uriCDC;

    @Column(nullable = false)
    private int nombreDeSoutien = 0;

    @Column(nullable = false)
    private LocalDateTime datePublication = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "id_utilisateur")
    private Utilisateur utilisateur;

    @Transient
    @OneToOne(mappedBy = "ideeProjet")
    private Projet projet;

    @OneToMany(mappedBy = "ideeProjet")
    private List<CommentaireIdeeProjet> commentairesIdeeProjet = new ArrayList<>();

    public ResponseIdeeProjet toResponse() {
        return new ResponseIdeeProjet(
                id,
                titre,
                description,
                domaine,
                uriCDC,
                nombreDeSoutien,
                datePublication,
                utilisateur.getId()
        );
    }

    public ResponseIdeeProjet2 toResponse2() {
        List<ResponseCommentaireIdeeProjet> commentaireIdeeProjets = new ArrayList<>();
        for (CommentaireIdeeProjet commentaireIdeeProjet : commentairesIdeeProjet) {
            commentaireIdeeProjets.add(commentaireIdeeProjet.toResponse());
        }
        return new ResponseIdeeProjet2(
                id,
                titre,
                description,
                domaine,
                uriCDC,
                nombreDeSoutien,
                datePublication,
                new ResponseUser(
                        utilisateur.getPrenom(),
                        utilisateur.getNom(),
                        utilisateur.getRole()
                ),
                commentaireIdeeProjets
        );
    }
}
