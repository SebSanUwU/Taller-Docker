package edu.escuelaing.arem.ASE.app.service;

import edu.escuelaing.arem.ASE.app.repository.MessageRepository;
import edu.escuelaing.arem.ASE.app.repository.mesagge.Message;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessagesServiceMap implements MessagesService {
    private final MessageRepository messageRepository;
    public MessagesServiceMap(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }

    @Override
    public void save(Message message) {
        this.messageRepository.save(message);
    }

    @Override
    public Message findByLog(String log) {
        return this.messageRepository.findItemByLog(log);
    }

    @Override
    public List<Message> all() {
        return this.messageRepository.findAll();
    }
}
