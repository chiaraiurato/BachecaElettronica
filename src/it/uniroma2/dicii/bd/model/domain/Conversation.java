package it.uniroma2.dicii.bd.model.domain;

import java.util.ArrayList;
import java.util.List;

public class Conversation {
    private final List<Message> messageList = new ArrayList<>();
    private User buyer;
    private User seller;
    public void addMessages(Message m) {
        this.messageList.add(m);
    }
    public List<Message> getMessageLists() {
        return messageList;
    }
    public Conversation(User buyer, User seller, Message message) {
        this.buyer = buyer;
        this.seller = seller;
        this.messageList.add(message);
    }
    public User getBuyer() {
        return buyer;
    }
    public User getSeller() {
        return seller;
    }
    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }
}
