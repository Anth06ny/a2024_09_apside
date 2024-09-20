package org.example.a2024_09_apside.services;

import org.aspectj.bridge.Message;
import org.example.a2024_09_apside.model.beans.MessageBean;
import org.example.a2024_09_apside.model.repository.MessageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class MessageServiceTest {

    @Mock
    MessageRepository messageRepository; // = Mockito.mock(MessageRepository.class)

    @InjectMocks
    private MessageService messageService;

//    @Test
//    public void testThenAnswer() {
//        when(messageRepository.save(any(MessageBean.class))).thenAnswer(invocation -> {
//            MessageBean message = invocation.getArgument(0);
//            // Simuler l'attribution d'un ID
//            message.setId(1L);
//            return message;
//        });
//
//        MessageBean messageBean = new MessageBean(null, "Alice", "Hello World");
//        messageService.createMessage(messageBean);
//
//        // Vérifier que l'ID a été attribué
//        assertNotNull(messageBean.getId());
//    }

    @Test
    void addMessage() throws Exception {
        MessageBean message = new MessageBean(0, "Toto", "Coucou");

        messageService.addMessage(message);

        verify(messageRepository, times(1)).save(message);

        // Vérifier que l'exception est levée lors de l'appel
        assertThrows(Exception.class, () -> messageService.addMessage(new MessageBean(0, "Toto", "")));


        MessageBean messageDeTest = new MessageBean(5L, "toto", "coucou");
        when(messageRepository.findById(5L)).thenReturn(Optional.of(messageDeTest));

        MessageBean messageInBase = messageService.findById(5L);

        assertNotNull(messageInBase, "Message non trouvé");
        assertEquals(messageDeTest, messageInBase, "Les attributs sont différents");
        assertSame(messageDeTest, messageInBase, "C'est le même message");

    }

    @BeforeEach
    void cleanDataBase(){
        messageService.clean();
    }

    @Test
    void get10Last() throws Exception {

        ArrayList<MessageBean> messages = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            MessageBean message = new MessageBean(0, "Toto" + i, "Coucou" +i);
            messages.add( message);
            messageService.addMessage(message);
        }

        when(messageRepository.findFirst10ByOrderByIdDesc()).thenReturn(messages);

        var list = messageService.get10Last();
        assertEquals(5, list.size());

        messages.clear();
        for (int i = 5; i < 15; i++) {
            MessageBean message = new MessageBean(0, "Toto" + i, "Coucou" +i);
            messages.add(0, message);
            messageService.addMessage(message);
        }

        //when(messageRepository.findFirst10ByOrderByIdDesc()).thenReturn(messages.subList(5, 15));

        list = messageService.get10Last();
        assertEquals(10, list.size());
        assertEquals("Coucou14", list.get(0).getMessage());
    }
}