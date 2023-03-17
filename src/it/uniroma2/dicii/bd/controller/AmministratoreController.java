package it.uniroma2.dicii.bd.controller;
import it.uniroma2.dicii.bd.exception.DAOException;
import it.uniroma2.dicii.bd.model.dao.ConnectionFactory;
import it.uniroma2.dicii.bd.model.dao.GenerateReportProcedureDAO;
import it.uniroma2.dicii.bd.model.domain.Credentials;
import it.uniroma2.dicii.bd.model.domain.Report;
import it.uniroma2.dicii.bd.model.domain.Role;
import it.uniroma2.dicii.bd.model.domain.User;
import it.uniroma2.dicii.bd.view.AdministratorView;

import java.io.IOException;
import java.sql.SQLException;

public class AmministratoreController implements ControllerSession {

    User administrator;
    User user;
    @Override
    public void start(Credentials credentials) {

        try {
            ConnectionFactory.changeRole(Role.AMMINISTRATORE);
            administrator = new User(credentials.getUsername());
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
        while(true) {
            int choice;
            choice = AdministratorView.showMenu();
            switch (choice) {
                case 1 -> generateReport();
                case 2 -> viewReport();
                case 3 -> addCategory();
                case 4 -> System.exit(0);
                default -> throw new RuntimeException("Invalid choice");
            }
        }
    }

    private void addCategory() {
    }

    private void viewReport() {
    }

    private void generateReport() {
        GenerateReportProcedureDAO generateReport = GenerateReportProcedureDAO.getInstance();
        Report report;
        try {
            user = AdministratorView.insertReport();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            report=generateReport.execute(user);
        } catch (DAOException e) {
            throw new RuntimeException(e);
        }
        AdministratorView.showReport(report);
    }
}
