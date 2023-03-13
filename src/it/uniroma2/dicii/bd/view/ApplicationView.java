package it.uniroma2.dicii.bd.view;

import java.util.Scanner;

public class ApplicationView {
    public static int showApplication(){
        System.out.println("**********************************");
        System.out.println("* BACHECA ELETTRONICA DI ANNUNCI *");
        System.out.println("**********************************\n");
        System.out.println("1) Registration\n");
        System.out.println("2) Login\n");

        Scanner input = new Scanner(System.in);
        int choice;
        while (true) {
            System.out.print("Inserisci la scelta: ");
            choice = input.nextInt();
            if (choice >= 1 && choice <= 2) {
                break;
            }
            System.out.println("Opzione invalida");
        }

        return choice;
    }
}
