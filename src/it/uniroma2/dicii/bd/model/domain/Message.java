package it.uniroma2.dicii.bd.model.domain;

import java.sql.Time;
import java.util.Date;

public class Message {
    int id;
    int idConversation;
    Date date;
    Time hour;
    String text;
    public Message(Date date, Time hour, String text) {
        this.date = date;
        this.hour = hour;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdConversation() {
        return idConversation;
    }

    public void setIdConversation(int idConversation) {
        this.idConversation = idConversation;
    }

}
