package org.example.a2024_09_apside.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {


    //Channel que les clients viendront écouter et dans lequel on publiera
    //Il peut y avoir plusieurs channels
    public static final String CHANNEL_NAME = "/ws/myChannel";


    //Connexion
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws/chat-app").withSockJS();
    }

    // Configurer les channels (broker) de messages que les clients viendront écouter
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker(CHANNEL_NAME);
        //Définir les préfixes des destinations
        registry.setApplicationDestinationPrefixes("/ws");
    }
}
