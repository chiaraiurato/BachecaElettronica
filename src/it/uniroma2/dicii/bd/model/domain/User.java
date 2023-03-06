package it.uniroma2.dicii.bd.model.domain;

import java.util.Date;

public class User {
    private final String username;
    private final String name;
    private final String surname;
    //private final Date dateOfBirth;
    private final String dateOfBirth;
    private final String residentialAddress;
    private final String billingAddress= "";

    /*
    private final Enum TypePrefContact;

     */
    private final String prefContactDetails= "";


    public User(String username, String name, String surname, String dateOfBirth, String residentialAddress) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.residentialAddress = residentialAddress;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return this.name + " " + this.surname;
    }
}
