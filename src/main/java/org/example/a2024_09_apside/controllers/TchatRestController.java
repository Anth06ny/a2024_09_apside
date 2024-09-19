package org.example.a2024_09_apside.controllers;

import org.example.a2024_09_apside.model.beans.MessageBean;
import org.example.a2024_09_apside.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("api")
public class TchatRestController {


    @Autowired
    private MessageService messageService;

    //http://localhost:8080/api/saveMessage
//Json Attendu : {"pseudo": "toto", "message": "Coucou}
    @PostMapping("/saveMessage")
    public void saveMessage(@RequestBody MessageBean messageBean) throws Exception {
        System.out.println("/saveMessage : " + messageBean);

        messageService.addMessage(messageBean);
    }

    //http://localhost:8080/api/allMessages
    @GetMapping("/allMessages")
    public List<MessageBean> allMessages() {
        System.out.println("/allMessages");

        return messageService.getAll();
    }

}
