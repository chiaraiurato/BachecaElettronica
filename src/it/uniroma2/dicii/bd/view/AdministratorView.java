package it.uniroma2.dicii.bd.view;

import it.uniroma2.dicii.bd.model.domain.Report;
import it.uniroma2.dicii.bd.model.domain.User;
import it.uniroma2.dicii.bd.model.utils.TablePrinter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class AdministratorView {
    public static int showMenu(){
        System.out.println("*********************************");
        System.out.println("*       ADMIN DASHBOARD         *");
        System.out.println("*********************************\n");
        System.out.println("*** Cosa potrei fare per te? ***\n");
        System.out.println("1️ Genera report");
        System.out.println("2️ Visualizza report");
        System.out.println("3️ Aggiungi categoria");
        System.out.println("4️ Esci");


        Scanner input = new Scanner(System.in);
        int choice = 0;
        while (true) {
            System.out.print("Inserisci una scelta: ");
            choice = input.nextInt();
            if (choice >= 1 && choice <= 4) {
                break;
            }
            System.out.println("Opzione invalida");
        }

        return choice;
    }
    public static User insertReport() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Username: ");
        String username = reader.readLine();
        return new User(username);
    }

    public static void showReport(Report report) {
        TablePrinter tablePrinter = new TablePrinter();
        tablePrinter.setShowVerticalLines(true);
        if (report == null) {
            System.out.println("\nNessun report inserito! ");
        } else {
            tablePrinter.setHeaders("Venditore", "Data", "Percentuale");

            tablePrinter.addRow(report.getUser().getUsername(), report.getDate().toString(), String.valueOf(report.getPercentage()));
            tablePrinter.print();

        }
    }
}
