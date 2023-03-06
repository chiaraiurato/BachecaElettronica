package it.uniroma2.dicii.bd.model.domain;

import java.util.ArrayList;
import java.util.List;

public class Conversation {
    List<Message> messageList = new ArrayList<>();

    public void addMessages(Message m) {
        this.messageList.add(m);
    }
    public List<Message> getMessageLists() {
        return messageList;
    }

}
