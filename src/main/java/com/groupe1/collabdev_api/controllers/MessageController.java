package com.groupe1.collabdev_api.controllers;


import com.groupe1.collabdev_api.dto.MessageDto;
import com.groupe1.collabdev_api.entities.Messagerie;
import com.groupe1.collabdev_api.repositories.MessagerieRepository;
import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller
@AllArgsConstructor
public class MessageController {

    private final SimpMessagingTemplate messagingTemplate;
    private final MessagerieRepository messagerieRepository;

    @MessageMapping("SendMessage/{projetId}")
    public  void sendMessage(@DestinationVariable int projetId, MessageDto message){

        Messagerie msg = new Messagerie();
        msg.setProjectId(projetId);
        msg.setSender(message.getSender());
        msg.setContent(message.getContent());
        msg.setSendAt(LocalDateTime.now());

        messagerieRepository.save(msg);

        messagingTemplate.convertAndSend("/chat" +projetId, msg);
    }
}
