package org.example.a2024_09_apside.controllers;

import org.example.a2024_09_apside.model.beans.MessageBean;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
@RequestMapping("api")
public class TchatRestController {



    private ArrayList<MessageBean> listMessage = new ArrayList<>();

    //http://localhost:8080/api/saveMessage
//Json Attendu : {"pseudo": "toto", "message": "Coucou}
    @PostMapping("/saveMessage")
    public void saveMessage(@RequestBody MessageBean messageBean) {
        System.out.println("/saveMessage : " + messageBean);

        listMessage.add(messageBean);
    }

    //http://localhost:8080/api/allMessages
    @GetMapping("/allMessages")
    public ArrayList<MessageBean> allMessages() {
        System.out.println("/allMessages");

        return listMessage;
    }

}
