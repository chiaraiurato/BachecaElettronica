package it.uniroma2.dicii.bd.view;

import it.uniroma2.dicii.bd.model.domain.Credentials;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LoginView {
    public static Credentials authenticate() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("\uD83D\uDC64 Username: ");
        String username = reader.readLine();
        System.out.print("\uD83D\uDD11 Password: ");
        String password = reader.readLine();

        return new Credentials(username, password, null);
    }
}
