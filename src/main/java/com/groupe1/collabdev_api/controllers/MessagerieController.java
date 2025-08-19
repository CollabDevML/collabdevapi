package com.groupe1.collabdev_api.controllers;

import com.groupe1.collabdev_api.entities.Messagerie;
import com.groupe1.collabdev_api.repositories.MessagerieRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/message")
@AllArgsConstructor
public class MessagerieController {

    private MessagerieRepository messagerieRepository;

    @GetMapping("/{idProjet}")
    public List<Messagerie> getMessageByProjet(@PathVariable int idProjet){
        return messagerieRepository.findTop20ByProjetIdOrderByTimestampDesc(idProjet);
    }
}
