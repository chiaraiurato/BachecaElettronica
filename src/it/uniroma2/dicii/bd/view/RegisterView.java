package it.uniroma2.dicii.bd.view;

import it.uniroma2.dicii.bd.model.domain.Credentials;
import it.uniroma2.dicii.bd.model.domain.Role;
import it.uniroma2.dicii.bd.model.domain.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RegisterView {
    public static Credentials register() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Inserisci un username: ");
        String username = reader.readLine();
        System.out.print("Scegli una password: ");
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
        System.out.print("Tipo recapito preferito (telefono/cellulare/email): ");
        String typeAddress = reader.readLine();
        System.out.print("Recapito : ");
        String address = reader.readLine();
        return new User(name, surname, dateOfBirth, residentialAddress, billingAddress, typeAddress, address );
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
