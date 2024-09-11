package edu.escuelaing.arem.ASE.app.controller;


import edu.escuelaing.arem.ASE.app.exception.MessagesNotFoundException;
import edu.escuelaing.arem.ASE.app.repository.mesagge.Message;
import edu.escuelaing.arem.ASE.app.service.MessagesService;
import edu.escuelaing.arem.ASE.app.service.MessagesServiceMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/v1/messages/")
public class MessagesController {
    private final MessagesService messagesService;

    public MessagesController(@Autowired MessagesServiceMap messagesService) {
        this.messagesService = messagesService;
    }
    @PostMapping
    public ResponseEntity<Object> createLog(@RequestParam("log") String log,@RequestParam("message") String message){
        String messageId = String.valueOf(UUID.randomUUID().hashCode());
        messagesService.save(new Message(messageId,log,message));
        URI createdMessageUri = URI.create("/v1/messages/");
        return ResponseEntity.created(createdMessageUri).body(log);
    }

    @GetMapping
    public ResponseEntity<List<Message>> getAllMessages() {
        List<Message> messages = messagesService.all();

        // Si la lista tiene más de 10 mensajes, obtenemos los últimos 10
        if (messages.size() > 10) {
            messages = messages.subList(messages.size() - 10, messages.size());
        }

        return ResponseEntity.ok(messages);
    }


    @GetMapping("{log}")
    public ResponseEntity<Message> findByLog(@PathVariable("log") String log){
        Optional<Message> optionalMessage = Optional.ofNullable(messagesService.findByLog(log));
        if (optionalMessage.isPresent()){
            Message message = optionalMessage.get();
            return ResponseEntity.ok(message);
        } else {
            throw new MessagesNotFoundException(log);
        }
    }
}
