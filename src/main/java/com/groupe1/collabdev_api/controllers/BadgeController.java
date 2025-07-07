package com.groupe1.collabdev_api.controllers;

import com.groupe1.collabdev_api.entities.Badge;
import com.groupe1.collabdev_api.services.BadgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/admin/badge")
public class BadgeController {

    @Autowired
    private BadgeService badgeService;

    //Pour afficher tous les badges :
    @GetMapping
    public List<Badge> getBadge(){
        return badgeService.afficheBadge();
    }

    //Pour l'ajout des badges dans le systemes :
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> addBadge(
            @RequestParam("titre") String titre,
            @RequestParam("fichier") MultipartFile chemin
    ) throws IOException {
        return  badgeService.ajouteBadge(titre,chemin);
    }

    //Pour la modification d'un badge :
    @PutMapping(value = "/{id}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateBadge(
            @PathVariable("id") int id,
            @RequestParam(value = "titre",required = false) String titre,
            @RequestParam(value = "fichier",required = false) MultipartFile chemin
    ) throws IOException {
        return badgeService.modifieBadge(id,titre,chemin);
    }

    //Pour la suppression de badge :
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBadge(@PathVariable int id){
        return badgeService.deleteBadge(id);
    }

}
