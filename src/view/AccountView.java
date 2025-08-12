package view;

import model.Account;
import model.dto.AccountResponse;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.Table;

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

    public Integer showEnterId() {
        System.out.print("Enter an id: ");
        return Integer.parseInt(SCANNER.nextLine());
    }

    public void showAccountDetail(AccountResponse response) {
        if (response == null) {
            throw new NullPointerException("Response is null");
        }
        Table table = new Table(
                2,
                BorderStyle.UNICODE_BOX_DOUBLE_BORDER
        );
        table.addCell("Account Detail   ", 2);
        table.addCell("ID   ");
        table.addCell(String.valueOf(response.id()));
        table.addCell("Owner Name   ");
        table.addCell(response.ownerName());
        table.addCell("Balance   ");
        table.addCell(String.valueOf(response.balance()));

        System.out.println(table.render());
    }
}
