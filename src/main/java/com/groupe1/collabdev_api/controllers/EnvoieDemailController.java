package com.groupe1.collabdev_api.controllers;

import com.groupe1.collabdev_api.services.EnvoieDemailService;
import com.groupe1.collabdev_api.dto.request_dto.EmailRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EnvoieDemailController {
    @Autowired
    private EnvoieDemailService envoieDemailService;

    @PostMapping("/send-email")
    public String sendEmail(@RequestBody EmailRequest request) {
        envoieDemailService.envoyerEmail(request.getTo(), request.getSubject(), request.getText());
        return "Email envoyé avec succès !";
    }
}
