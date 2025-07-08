package com.groupe1.collabdev_api.dto.request_dto;

public class EmailRequest {
    private String to;
    private String subject;
    private String text;

    public EmailRequest() {}

    //retourne l'adresse du destinataire
    public String getTo() {

        return to;
    }
    public void setTo(String to) {

        this.to = to;
    }

    //retourne le sujet
    public String getSubject() {

        return subject;
    }
    public void setSubject(String subject) {

        this.subject = subject;
    }
    //le corp du message
    public String getText() {

        return text;
    }
    public void setText(String text) {

        this.text = text;
    }
} 