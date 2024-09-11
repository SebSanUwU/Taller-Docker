package edu.escuelaing.arem.ASE.app.service;

import edu.escuelaing.arem.ASE.app.repository.mesagge.Message;

import java.util.List;

public interface MessagesService {
    void save(Message message);
    Message findByLog(String log);
    List<Message> all();
}
