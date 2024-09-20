package org.example.a2024_09_apside.controllers;

import org.example.a2024_09_apside.config.WebSocketConfig;
import org.example.a2024_09_apside.model.beans.MessageBean;
import org.example.a2024_09_apside.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/ws")
public class WebSocketController {

    @Autowired
    private MessageService messageService;

    //Outils pour poster des messages
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @EventListener
    public void handleWebSocketSubscribeListener(SessionSubscribeEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        if ((WebSocketConfig.CHANNEL_NAME).equals(headerAccessor.getDestination())) {
            messagingTemplate.convertAndSend(WebSocketConfig.CHANNEL_NAME , messageService.get10Last());
        }
    }

    @MessageMapping("/chat") // Chemin complet : /ws/chat
    public void receiveMessage(MessageBean message) throws Exception {
        System.out.println("/ws/chat " + message.getPseudo() + " : " + message.getMessage());

        messageService.addMessage(message);

        // Envoyer la liste des messages sur le channel
        messagingTemplate.convertAndSend(WebSocketConfig.CHANNEL_NAME , messageService.get10Last());
    }


}
