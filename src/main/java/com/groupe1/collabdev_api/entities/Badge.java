package com.groupe1.collabdev_api.entities;

import com.groupe1.collabdev_api.dto.response_dto.ResponseBadge;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter

@Table(name = "badges")
public class Badge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public Badge() {
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

    public String getUriImage() {
        return uriImage;
    }

    public void setUriImage(String uriImage) {
        this.uriImage = uriImage;
    }

    public List<ObtentionBadge> getObtentionBadges() {
        return obtentionBadges;
    }

    public void setObtentionBadges(List<ObtentionBadge> obtentionBadges) {
        this.obtentionBadges = obtentionBadges;
    }

    public Badge(int id, String titre, String uriImage, List<ObtentionBadge> obtentionBadges) {
        this.id = id;
        this.titre = titre;
        this.uriImage = uriImage;
        this.obtentionBadges = obtentionBadges;
    }

    @Column(nullable = false, unique = true)
    private String titre;

    @Column(nullable = false, unique = true)
    private String uriImage;

    @OneToMany(mappedBy = "badge")
    private List<ObtentionBadge> obtentionBadges = new ArrayList<>();

    public ResponseBadge toResponse() {
        return new ResponseBadge(
                this.id,
                this.titre,
                this.uriImage
        );
    }
}
