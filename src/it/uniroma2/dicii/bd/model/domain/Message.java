package it.uniroma2.dicii.bd.model.domain;

import java.sql.Time;
import java.util.Date;

public class Message {
    private int id;
    private int idConversation;
    private Date date;
    private Time hour;
    private String text;

    public Message(Date date, Time hour, String text) {
        this.date = date;
        this. hour = hour;
        this.text = text;
    }
    public Message(String text) {
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
    public String getText() {
        return text;
    }
    public void setIdConversation(int idConversation) {
        this.idConversation = idConversation;
    }

}
