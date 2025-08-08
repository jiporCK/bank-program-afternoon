package view;

import model.Account;

import java.util.Scanner;

public class AccountView {
    private final static Scanner SCANNER = new Scanner(System.in);

    public Account showAccountCreation() {
        System.out.print("Enter your name: ");
        String name = SCANNER.nextLine();

        System.out.print("Enter your balance: ");
        Double balance = Double.parseDouble(SCANNER.nextLine());

        return new Account(name, balance);
    }

}
