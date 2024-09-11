package edu.escuelaing.arem.ASE.app.repository.mesagge;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.beans.ConstructorProperties;
import java.time.LocalDateTime;


@Document("message")
public class Message {
    @Id
    private String id;
    private String log;
    private String message;
    private LocalDateTime date;
    public Message(){
    }
    @ConstructorProperties({"id", "log", "message"})
    public Message(String id,String log, String message) {
        this.id = id;
        this.log = log;
        this.message = message;
        this.date = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id='" + id + '\'' +
                ", message='" + message + '\'' +
                ", date=" + date +
                '}';
    }
}
