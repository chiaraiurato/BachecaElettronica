package it.uniroma2.dicii.bd.controller;

import it.uniroma2.dicii.bd.exception.DAOException;
import it.uniroma2.dicii.bd.model.dao.*;

import it.uniroma2.dicii.bd.model.domain.*;
import it.uniroma2.dicii.bd.view.UserView;

import java.io.IOException;
import java.sql.SQLException;

public class UtenteController implements Controller{
    User user = new User("m.rossi", "mario", "rossi", "15-04-2000", "via c.marchesi" );
    Ad ad = null;
    AdList adList = null;
    CategoryList categoryList = null;

    @Override
    public void start() {
        try {
            ConnectionFactory.changeRole(Role.UTENTE);
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }


        while(true) {
            int choice;
            try {
                choice = UserView.showMenu();
            } catch(IOException e) {
                throw new RuntimeException(e);
            }

            switch(choice) {
                case 1 -> newAd();
                case 2 -> listAd();
                case 3 -> viewAd();
                case 4 -> writeMessage();
                case 5 -> System.exit(0);
                default -> throw new RuntimeException("Invalid choice");
            }
        }
    }

    private void writeMessage() {

    }

    private void listAd() {
        try {
            ListAdProcedureDAO listAd = ListAdProcedureDAO.getInstance();
            adList = listAd.execute(user);
        } catch (DAOException e) {
            throw new RuntimeException(e);
        }
        UserView.showListAd(adList);
    }

    private void newAd(){

        Category category;
        try {
            ad = UserView.createAd(user);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            ShowCategoriesProcedureDAO showCategories = ShowCategoriesProcedureDAO.getInstance();
            categoryList = showCategories.execute();
            UserView.showCategory(categoryList);
            category = UserView.selectCategory(categoryList);
            ad.setCategory(category);
        } catch (IOException | DAOException e) {
            throw new RuntimeException(e);
        }
        try {
            AddAdProcedureDAO addAd = AddAdProcedureDAO.getInstance();
            ad = addAd.execute(ad.getTitle(), ad.getAmount(), ad.getDescription(), user, category);
        } catch(DAOException e) {
            throw new RuntimeException(e);
        }
        UserView.showAd(ad);
    }

    private void viewAd() {
        int id = UserView.selectAd();
        try {
            ViewAdProcedureDAO viewAd = ViewAdProcedureDAO.getInstance();
            ad = viewAd.execute(id, user);
        } catch (DAOException e) {
            throw new RuntimeException(e);
        }
        UserView.showAd(ad);

    }


}
