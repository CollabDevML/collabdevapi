package com.groupe1.collabdev_api.entities;

import com.groupe1.collabdev_api.dto.response_dto.ResponseObtentionBadge;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "obtentions_badge")
public class ObtentionBadge {

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
}
