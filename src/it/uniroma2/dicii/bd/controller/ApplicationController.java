package it.uniroma2.dicii.bd.controller;

import it.uniroma2.dicii.bd.model.domain.Credentials;
import it.uniroma2.dicii.bd.model.domain.User;
import it.uniroma2.dicii.bd.view.ApplicationView;
public class ApplicationController implements Controller {
    Credentials cred;
    User user;
    @Override
    public void start() {
        while (true) {
            int choice;
            choice = ApplicationView.showApplication();
            switch (choice) {
                case 1 -> registration();
                case 2 -> login();
                default -> throw new RuntimeException("Invalid choice");
            }
        }
    }

    private void registration() {
        RegistrationController registrationController = new RegistrationController();
        registrationController.start();
        user = registrationController.getUser();
        cred = registrationController.getCred();
        new UtenteController().start(cred);
    }

    private void login() {
        LoginController loginController = new LoginController();
        loginController.start();
        cred = loginController.getCred();

        if (cred.getRole() == null) {
            throw new RuntimeException("Invalid credentials");
        }

        switch (cred.getRole()) {

            case UTENTE -> new UtenteController().start(cred);
            case AMMINISTRATORE -> new AmministratoreController().start(cred);
            default -> throw new RuntimeException("Invalid credentials");
        }
    }
}
