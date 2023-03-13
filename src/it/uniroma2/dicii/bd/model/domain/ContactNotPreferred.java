package it.uniroma2.dicii.bd.model.domain;

public class ContactNotPreferred {
    Enum<TypeContact> typeContact;
    String contact;
    public ContactNotPreferred(Enum<TypeContact> typeContact, String contact) {
        this.typeContact = typeContact;
        this.contact = contact;
    }
    public Enum<TypeContact> getTypeContact() {
        return typeContact;
    }

    public void setTypeContact(Enum<TypeContact> typeContact) {
        this.typeContact = typeContact;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
