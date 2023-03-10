package it.uniroma2.dicii.bd.controller;
import it.uniroma2.dicii.bd.exception.DAOException;
import it.uniroma2.dicii.bd.model.dao.*;
import it.uniroma2.dicii.bd.model.domain.*;
import it.uniroma2.dicii.bd.view.UserView;
import java.io.IOException;
import java.sql.SQLException;

public class UtenteController implements ControllerSession{
    private User user;
    private Ad ad = null;

    @Override
    public void start(Credentials credentials) {
        try {
            ConnectionFactory.changeRole(Role.UTENTE);
            ObtainUserProcedureDAO obtainUser = ObtainUserProcedureDAO.getInstance();
            user = obtainUser.execute(credentials.getUsername());
        } catch(SQLException | DAOException e) {
            throw new RuntimeException(e);
        }
        chooseOperation();
    }
    private void chooseOperation(){
        while(true) {
            int choice;
            try {
                choice = UserView.showMenu();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            switch (choice) {
                case 1 -> newAd();
                case 2 -> listAd();
                case 3 -> viewAd();
                case 4 -> writeMessage();
                case 5 -> viewMessages();
                case 6 -> System.exit(0);
                default -> throw new RuntimeException("Invalid choice");
            }
        }
    }

    private void viewMessages() {
        User seller = new User(UserView.listMessages());
        ViewMessagesProcedureDAO viewMessages = ViewMessagesProcedureDAO.getInstance();
        Conversation conversation = null;
        try {
            conversation = viewMessages.execute(user, seller);
        } catch (DAOException | SQLException e) {
            UserView.printError(e);
        }
        if(conversation != null){
            System.out.println(conversation);
        }

    }

    private void writeMessage() {
        WriteMessageProcedureDAO writeMessage = WriteMessageProcedureDAO.getInstance();
        Conversation conv = UserView.writeMessage(user);
        try {
            Message m = writeMessage.execute(user, conv.getSeller(), conv.getMessageLists().get(0));
        } catch (DAOException e) {
            UserView.printError(e);
        }
    }

    private void listAd() {
        AdList adList = null;
        try {
            ListAdProcedureDAO listAd = ListAdProcedureDAO.getInstance();
            adList = listAd.execute(user);
        } catch (DAOException e) {
            UserView.printError(e);
        }
        UserView.showListAd(adList);
    }

    private void newAd(){

        Category category = null;
        try {
            ad = UserView.createAd(user);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            ShowCategoriesProcedureDAO showCategories = ShowCategoriesProcedureDAO.getInstance();
            CategoryList categoryList = showCategories.execute();
            UserView.showCategory(categoryList);
            category = UserView.selectCategory(categoryList);
            ad.setCategory(category);
        } catch (IOException | DAOException e) {
            UserView.printError(e);
        }
        try {
            AddAdProcedureDAO addAd = AddAdProcedureDAO.getInstance();
            ad = addAd.execute(ad.getTitle(), ad.getAmount(), ad.getDescription(), user, category);
        } catch(DAOException e) {
            UserView.printError(e);
        }
        UserView.showAd(ad);
    }

    private void viewAd() {
        int id = UserView.selectAd();
        try {
            ViewAdProcedureDAO viewAd = ViewAdProcedureDAO.getInstance();
            ad = viewAd.execute(id);
        } catch (DAOException e) {
            UserView.printError(e);
            chooseOperation();
        }
        UserView.showAd(ad);
        while(true) {
            int choice;
            choice = UserView.second_menu();

            switch(choice) {
                case 1 -> listComments();
                case 2 -> writeComment();
                case 3 -> chooseOperation();
                default -> throw new RuntimeException("Invalid choice");
            }
        }
    }

    private void listComments() {
        CommentList commentList= null;
        try {
            ListCommentsProcedureDAO listComments = ListCommentsProcedureDAO.getInstance();
            commentList = listComments.execute();
        } catch (DAOException e) {
            UserView.printError(e);
        }
        System.out.println(commentList.toString());
    }

    private void writeComment() {
        Comment comment = UserView.writeComment();
        WriteCommentProcedureDAO writeComment = WriteCommentProcedureDAO.getInstance();
        try {
            writeComment.execute(comment);
        } catch (DAOException | SQLException e) {
            UserView.printError(e);
        }
    }


}
