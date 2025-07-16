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
@NoArgsConstructor
@Table(name = "badges")
public class Badge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

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
