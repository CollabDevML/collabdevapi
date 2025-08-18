package com.groupe1.collabdev_api.entities;

import com.groupe1.collabdev_api.dto.response_dto.ResponseObtentionBadge;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@Entity
@Table(name = "obtentions_badge")
public class ObtentionBadge {
    public ObtentionBadge(int id, LocalDateTime dateObtention, Contributeur contributeur, Badge badge) {
        this.id = id;
        this.dateObtention = dateObtention;
        this.contributeur = contributeur;
        this.badge = badge;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private LocalDateTime dateObtention = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "id_contributeur", nullable = false)
    private Contributeur contributeur;

    @ManyToOne
    @JoinColumn(name = "id_badge", nullable = false)
    private Badge badge;

    public ResponseObtentionBadge toResponse() {
        return new ResponseObtentionBadge(
                this.id,
                this.contributeur.getUtilisateur().getPrenom()
                        + " "
                        + this.contributeur.getUtilisateur().getNom(),
                this.badge.getTitre(),
                this.dateObtention
        );
    }

    public ObtentionBadge() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDateObtention() {
        return dateObtention;
    }

    public void setDateObtention(LocalDateTime dateObtention) {
        this.dateObtention = dateObtention;
    }

    public Contributeur getContributeur() {
        return contributeur;
    }

    public void setContributeur(Contributeur contributeur) {
        this.contributeur = contributeur;
    }

    public Badge getBadge() {
        return badge;
    }

    public void setBadge(Badge badge) {
        this.badge = badge;
    }
}
