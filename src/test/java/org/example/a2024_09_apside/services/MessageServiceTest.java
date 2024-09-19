package org.example.a2024_09_apside.services;

import org.example.a2024_09_apside.model.beans.MessageBean;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MessageServiceTest {

    @Autowired
    private MessageService messageService;

    @Test
    void addMessage() throws Exception {
        MessageBean message = new MessageBean(0, "Toto", "Coucou");

        messageService.addMessage(message);

        assertTrue(message.getId() > 0, "L'id n'a pas été modifié");

        MessageBean messageInBase = messageService.findById(message.getId());

        assertNotNull(messageInBase, "Message non trouvé");
        assertEquals(message, messageInBase, "Les attributs sont différents");
        assertNotSame(message, messageInBase, "C'est le même message");

    }

    @BeforeEach
    void cleanDataBase(){
        messageService.clean();
    }

    @Test
    void get10Last() throws Exception {

        for (int i = 0; i < 5; i++) {
            MessageBean message = new MessageBean(0, "Toto" + i, "Coucou" +i);
            messageService.addMessage(message);
        }
        var list = messageService.get10Last();
        assertEquals(5, list.size());

        for (int i = 5; i < 15; i++) {
            MessageBean message = new MessageBean(0, "Toto" + i, "Coucou" +i);
            messageService.addMessage(message);
        }

        list = messageService.get10Last();
        assertEquals(10, list.size());
        assertEquals("Coucou14", list.get(0).getMessage());
    }
}