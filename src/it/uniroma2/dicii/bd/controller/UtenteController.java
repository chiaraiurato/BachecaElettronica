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
                case 6 -> viewNotification();
                case 7 -> listFollowedAd();
                case 9 -> System.exit(0);
                default -> throw new RuntimeException("Invalid choice");
            }
        }
    }

    private void listFollowedAd() {
        ListFollowedAdProcedureDAO followAd = ListFollowedAdProcedureDAO.getInstance();
        AdList adList = null;
        try {
            adList = followAd.execute(user);
        } catch (DAOException e) {
            UserView.printError(e);
        }
        UserView.showListAd(adList);
    }

    private void viewNotification() {
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
            CategoryList categoryList;

            categoryList = showCategories.execute();
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
                case 1 -> listComments(id);
                case 2 -> writeComment(id);
                case 3 -> followAd(id);
                case 4 -> viewNote(id);
                case 5 -> createNote(id);
                case 6 -> chooseOperation();
                default -> throw new RuntimeException("Invalid choice");
            }
        }
    }

    private void createNote(int idAd) {
        WriteNoteProcedureDAO writeNote = WriteNoteProcedureDAO.getInstance();
        Note note = UserView.writeNote();
        try {
            writeNote.execute(note, idAd, user);
        } catch (DAOException | SQLException e) {
            UserView.printError(e);
        }
    }

    private void viewNote(int idAd) {
        NoteList noteList = null;
        try {
            ListNoteProcedureDAO listNote = ListNoteProcedureDAO.getInstance();
            noteList = listNote.execute(idAd);
        } catch (DAOException e) {
            UserView.printError(e);
        }
        if (noteList != null) {
            System.out.println(noteList);
        }else{
            System.out.println("\nNessuna nota");
        }
    }

    private void followAd(int idAd) {
        FollowAdProcedureDAO followAd = FollowAdProcedureDAO.getInstance();
        Boolean check = false;
        try {
            check = followAd.execute(user, idAd);
        } catch (DAOException e) {
            System.out.println(e.getMessage());
        }
        if(check)
            System.out.println("\nAnnuncio seguito correttamente!");
    }

    private void listComments(int idAd) {
        CommentList commentList = null;
        try {
            ListCommentsProcedureDAO listComments = ListCommentsProcedureDAO.getInstance();
            commentList = listComments.execute(idAd);
        } catch (DAOException e) {
            UserView.printError(e);
        }
        if (commentList != null) {
            System.out.println(commentList);
        }else{
            System.out.println("\nNessun commento");
        }

    }

    private void writeComment(int idAd) {
        Comment comment = UserView.writeComment();
        WriteCommentProcedureDAO writeComment = WriteCommentProcedureDAO.getInstance();
        try {
            writeComment.execute(comment, idAd);
        } catch (DAOException | SQLException e) {
            UserView.printError(e);
        }

    }


}
