package it.uniroma2.dicii.bd.view;

import it.uniroma2.dicii.bd.model.domain.*;
import it.uniroma2.dicii.bd.model.utils.TablePrinter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class UserView {

    public static int showMenu() throws IOException {
        System.out.println("*********************************");
        System.out.println("*           DASHBOARD          *");
        System.out.println("*********************************\n");
        System.out.println("*** Cosa potrei fare per te? ***\n");
        System.out.println("1️ Crea un annuncio");
        System.out.println("2️ Lista gli annunci ");
        System.out.println("3️ Visualizza un annuncio");
        System.out.println("4️ Scrivi un messaggio");
        System.out.println("5️ Visualizza una conversazione");
        System.out.println("6️ Visualizza annunci seguiti");
        System.out.println("7️ Esci");


        Scanner input = new Scanner(System.in);
        int choice = 0;
        while (true) {
            System.out.print("Inserisci la scelta: ");
            choice = input.nextInt();
            if (choice >= 1 && choice <= 7) {
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
        String categories = categoryList.printTreeCategories();
        System.out.println(categories);
    }

    public static Category selectCategory() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String category;
        System.out.println("Si prega di selezionare una categoria: ");
        category = reader.readLine();
        return new Category(category);

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
        System.out.println("1️ Indica l'oggetto come venduto");
        System.out.println("2️ Visualizza commenti");
        System.out.println("3️ Scrivi un commento");
        System.out.println("4️ Segui un annuncio");
        System.out.println("5️ Visualizza nota");
        System.out.println("6️ Crea nota");
        System.out.println("7️ Ritorna al menu precedente ");

        Scanner input = new Scanner(System.in);
        int choice;
        while (true) {
            System.out.print("Inserisci la scelta: ");
            choice = input.nextInt();
            if (choice >= 1 && choice <= 7) {
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

    public static Note writeNote() {
        Scanner input = new Scanner(System.in);
        System.out.println("Inserire nota: ");
        String note = input.nextLine();
        return new Note( note);
    }
    public static boolean printNotificationAndDelete(List<Notification> notifications){
        for(int i=0; i<notifications.size(); i++){
            System.out.println(notifications.get(i).getDate() + " \uD83D\uDD14" + notifications.get(i).getText());
        }
        Scanner input = new Scanner(System.in);
        System.out.println("Vuoi eliminare tutte le notifiche? (y/n): ");
        String x = input.nextLine();
        if(x.equals("y")){
            return true;
        }
        return false;
    }

}
