package it.uniroma2.dicii.bd.view;

import java.util.Scanner;

public class AdministratorView {
    public static int showMenu(){
        System.out.println("*********************************");
        System.out.println("*       ADMIN DASHBOARD          *");
        System.out.println("*********************************\n");
        System.out.println("*** What should I do for you? ***\n");
        System.out.println("1) New category");
        System.out.println("2) Generate report");
        System.out.println("3) Remove announcement");
        System.out.println("4) Quit");


        Scanner input = new Scanner(System.in);
        int choice = 0;
        while (true) {
            System.out.print("Please enter your choice: ");
            choice = input.nextInt();
            if (choice >= 1 && choice <= 4) {
                break;
            }
            System.out.println("Invalid option");
        }

        return choice;
    }
}
