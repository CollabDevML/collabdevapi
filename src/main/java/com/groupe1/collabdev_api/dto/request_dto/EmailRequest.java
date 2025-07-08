package com.groupe1.collabdev_api.dto.request_dto;

import lombok.Setter;

public class EmailRequest {
    private String dest;
    @Setter
    private String subject;
    @Setter
    private String text;

    public EmailRequest() {}

    //retourne l'adresse du destinataire
    public String getDest() {

        return dest;
    }
    public void setTo(String to) {

        this.dest = dest;
    }

    //retourne le sujet
    public String getSubject() {

        return subject;
    }

    //le corp du message
    public String getText() {

        return text;
    }
} 