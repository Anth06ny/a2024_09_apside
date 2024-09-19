package org.example.a2024_09_apside.services;

import org.example.a2024_09_apside.model.beans.MessageBean;
import org.example.a2024_09_apside.model.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public List<MessageBean> get10Last() {
        return messageRepository.findFirst10ByOrderByIdDesc();
    }

    public MessageBean findById(Long id) {
        return messageRepository.findById(id).orElse(null);
    }

    public void addMessage(MessageBean messageBean) throws Exception {

        if (messageBean.getMessage().isBlank()) {
            throw new Exception("Message vide");
        }

        messageRepository.save(messageBean);
    }


    public List<MessageBean> getAll() {
        return messageRepository.findAll();
    }

    public void clean(){
        messageRepository.deleteAll();
    }
}
