package view;

import model.Account;
import model.dto.AccountResponse;
import model.dto.TransactionRequest;
import model.dto.TransactionResponse;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.Table;

import java.util.List;
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

    public TransactionRequest showTransactionRequest() {
        System.out.print("Enter sender id: ");
        int senderId = Integer.parseInt(SCANNER.nextLine());
        System.out.print("Enter receiver id: ");
        int receiverId = Integer.parseInt(SCANNER.nextLine());
        System.out.print("Enter amount: ");
        double amount = Double.parseDouble(SCANNER.nextLine());

        return new TransactionRequest(senderId, receiverId, amount);
    }

    public void showTransactionRecords(List<TransactionResponse> responses) {
        Table table = new Table(
                5,
                BorderStyle.UNICODE_BOX_DOUBLE_BORDER
        );
        table.addCell("ID   ");
        table.addCell("Sender Name   ");
        table.addCell("Receiver Name   ");
        table.addCell("Amount   ");
        table.addCell("Timestamp   ");
        responses.forEach(t -> {
            table.addCell(String.valueOf(t.id()));
            table.addCell(t.senderName());
            table.addCell(t.receiverName());
            table.addCell(String.valueOf(t.amount()));
            table.addCell(t.date());
        });
        System.out.println(table.render());
    }

}
