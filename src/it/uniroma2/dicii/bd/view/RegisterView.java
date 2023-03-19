package it.uniroma2.dicii.bd.view;

import it.uniroma2.dicii.bd.model.domain.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RegisterView {
    public static Credentials register() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("\uD83D\uDC64 Inserisci un username: ");
        String username = reader.readLine();
        System.out.print("\uD83D\uDD11 Scegli una password: ");
        String password = reader.readLine();

        return new Credentials(username, password, Role.UTENTE);
    }

    public static User insertInfo() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Nome: ");
        String name = reader.readLine();
        System.out.print("Cognome: ");
        String surname = reader.readLine();
        System.out.print("Data di nascita (yyyy/mm/dd): ");
        java.sql.Date dateOfBirth = getDate();
        System.out.print("Indirizzo di residenza: ");
        String residentialAddress = reader.readLine();
        System.out.print("Indirizzo fatturazione (opzionale): ");
        String billingAddress = reader.readLine();
        int i=0;
        List<String> typeAddress = new ArrayList<>();
        List<String> address = new ArrayList<>();
        while(true) {
            System.out.println("'telefono' (xx xxxxxx)\t 'cellulare' (xxx-xxx-xxxx) 'email' (example@gmail.com))");
            System.out.print("Tipo recapito : ");
            typeAddress.add(i,reader.readLine());
            System.out.print("Recapito: ");
            address.add(i, reader.readLine());
            System.out.print("Vuoi indicare questo recapito come preferito? (y/n): ");
            String response = reader.readLine();
            i++;
            if (response.equals("y")) {
                if(i == 1) {
                    return new User(name, surname, dateOfBirth, residentialAddress, billingAddress, TypeContact.valueOf(typeAddress.get(0)), address.get(0));
                }
                else{
                    User user = new User(name, surname, dateOfBirth, residentialAddress, billingAddress, TypeContact.valueOf(typeAddress.get(i-1)), address.get(i-1));
                    List<ContactNotPreferred> contactNotPreferred = new ArrayList<>();
                    for(int k=0; k<i-1; k++){
                        contactNotPreferred.add(k, new ContactNotPreferred(TypeContact.valueOf(typeAddress.get(k)), address.get(k)));
                    }
                    user.setContactNotPreferred(contactNotPreferred);
                    return user;
                }
            } else if (!response.equals("n")) {
                System.out.println("\nRiprova");
            }
        }

    }

    private static java.sql.Date getDate(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Date date;
        java.sql.Date sqlBirthDate;
        String input;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        dateFormat.setLenient(false);

        while(true){
            try {
                input = reader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            try {
                date = dateFormat.parse(input);
                sqlBirthDate = new java.sql.Date(date.getTime());
                break;
            } catch (ParseException e) {
                System.out.print("Riprova, formato invalido: ");
            }
        }
        return sqlBirthDate;
    }
}
