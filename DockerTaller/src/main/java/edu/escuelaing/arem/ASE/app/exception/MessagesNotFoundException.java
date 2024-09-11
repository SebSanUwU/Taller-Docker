package edu.escuelaing.arem.ASE.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class MessagesNotFoundException extends ResponseStatusException {
    public MessagesNotFoundException(String log) {
        super(HttpStatus.NOT_FOUND, "message with log: " + log + " not found");
    }
}
