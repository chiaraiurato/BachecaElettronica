package it.uniroma2.dicii.bd.view;

import it.uniroma2.dicii.bd.model.domain.*;
import it.uniroma2.dicii.bd.model.utils.TablePrinter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.Scanner;

public class UserView {

    public static int showMenu() throws IOException {
        System.out.println("*********************************");
        System.out.println("*           DASHBOARD          *");
        System.out.println("*********************************\n");
        System.out.println("*** Cosa potrei fare per te? ***\n");
        System.out.println("1) Crea un annuncio");
        System.out.println("2) Lista gli annunci ");
        System.out.println("3) Visualizza un annuncio");
        System.out.println("4) Scrivi un messaggio");
        System.out.println("5) Visualizza una conversazione");
        System.out.println("6) Esci");


        Scanner input = new Scanner(System.in);
        int choice = 0;
        while (true) {
            System.out.print("Inserisci la scelta: ");
            choice = input.nextInt();
            if (choice >= 1 && choice <= 6) {
                break;
            }
            System.out.println("Opzione invalida");
        }

        return choice;
    }
    public static Ad createAd(User user) throws IOException {
        Scanner input = new Scanner(System.in);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Titolo: ");
        String title = reader.readLine();
        System.out.println("Importo: ");
        float amount = input.nextFloat();
        System.out.println("Descrizione: ");
        String description = reader.readLine();
        return new Ad(title, amount, description, user);
    }

    public static void showCategory(CategoryList categoryList){
        System.out.println("--- CATEGORIE ---");
        String categories = categoryList.toString();
        System.out.println(categories);
    }
    public static void printError(Exception e){
        System.out.println(e.getMessage());
    }
    public static Category selectCategory(CategoryList categoryList) throws IOException {
        int index = 0;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int i;
        String category;
        while (true) {
            System.out.println("Si prega di selezionare una categoria: ");
            category = reader.readLine();
            for (i=0; i< categoryList.getCategories().size(); i++) {
                if (Objects.equals(category, categoryList.getCategories().get(index++).getName())) {
                    return new Category(categoryList.getCategories().get(index-1).getIdCategory(), category);
                }
            }
        }
    }
    public static  int selectAd(){
        Scanner input = new Scanner(System.in);
        System.out.println("Inserire codice annuncio: ");
        return input.nextInt();
    }
    public static void showAd(Ad ad) {
        TablePrinter tablePrinter = new TablePrinter();
        tablePrinter.setShowVerticalLines(true);
        if (ad == null) {
            System.out.println("\nNessun annuncio inserito! ");
        } else {
            tablePrinter.setHeaders("Titolo", "Importo", "Descrizione", "Stato", "Username", "Categoria" );

            tablePrinter.addRow(ad.getTitle(), ad.getAmount().toString(),
                    ad.getDescription(), ad.getStatus().toString(),  ad.getUser().getUsername(), ad.getCategory().getName());
            tablePrinter.print();

        }
    }
    public static void showListAd(AdList adList) {
        TablePrinter tablePrinter = new TablePrinter();
        tablePrinter.setShowVerticalLines(true);
        if (adList == null) {
            System.out.println("\nNessun annuncio inserito! ");
        } else {
            System.out.println("\n Annuncio inserito correttamente!");
            tablePrinter.setHeaders("Codice","Titolo", "Venditore");
            for(int i=0; i<adList.getSize(); i++){
                tablePrinter.addRow(String.valueOf(adList.getAds().get(i).getIdAd()), adList.getAds().get(i).getTitle(),
                        adList.getAds().get(i).getUser().getUsername());
            }

            tablePrinter.print();

        }
    }
    public static String listMessages(){
        Scanner input = new Scanner(System.in);
        System.out.println("Inserire username: ");
        return input.nextLine();
    }
    public static Conversation writeMessage(User user) {
        Scanner input = new Scanner(System.in);
        System.out.println("Inserire username: ");
        String seller = input.nextLine();
        System.out.println("Inserire messaggio: ");
        String msg = input.nextLine();
        return new Conversation( user, new User(seller), new Message(msg));
    }

    public static int second_menu() {
        System.out.println("1) Visualizza commenti");
        System.out.println("2) Scrivi un commento");
        System.out.println("3) Ritorna al menu");

        Scanner input = new Scanner(System.in);
        int choice;
        while (true) {
            System.out.print("Inserisci la scelta: ");
            choice = input.nextInt();
            if (choice >= 1 && choice <= 3) {
                break;
            }
            System.out.println("Opzione invalida");
        }

        return choice;
    }

    public static Comment writeComment() {
        Scanner input = new Scanner(System.in);
        System.out.println("Inserire commento: ");
        String cmt = input.nextLine();
        return new Comment( cmt);
    }


}
