package it.uniroma2.dicii.bd.controller;

import it.uniroma2.dicii.bd.exception.DAOException;
import it.uniroma2.dicii.bd.model.dao.RegisterProcedureDAO;
import it.uniroma2.dicii.bd.model.domain.Credentials;
import it.uniroma2.dicii.bd.model.domain.User;
import it.uniroma2.dicii.bd.view.RegisterView;

import java.io.IOException;

public class RegistrationController implements Controller {
    private Credentials cred = null;
    private User user = null;

    @Override
    public void start() {
        Boolean check;
        try {
            cred = RegisterView.register();
            user = RegisterView.insertInfo();
        } catch(IOException e) {
            throw new RuntimeException(e);
        }

        try {
            RegisterProcedureDAO register = RegisterProcedureDAO.getInstance();
            check = register.execute(cred, user);
        } catch(DAOException e) {
            throw new RuntimeException(e);
        }
        if(check){
            System.out.println("Registrazione avvenuta con successo!");
        }
    }

    public Credentials getCred() {
        return cred;
    }
    public User getUser(){
        return user;
    }
}
