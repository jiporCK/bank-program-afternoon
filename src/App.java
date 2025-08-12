import controller.AccountController;
import model.db.DbConnection;
import model.repository.AccountRepository;
import model.service.AccountService;
import view.AccountView;

import java.util.Scanner;

public class App {

    private final static AccountRepository accountRepository = new AccountRepository();
    private final static AccountService accountService = new AccountService(accountRepository);
    private final static AccountView accountView = new AccountView();
    private final static AccountController accountController = new AccountController(
            accountView, accountService
    );

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("""
                    1. Create account
                    2. Find account by id
                    3. Transfer money
                    4. Show Transaction details
                    0. Exit""");
                System.out.print("Enter an option: ");
                int op = Integer.parseInt(scanner.nextLine());

                if (op == 0) break;

                switch (op) {
                    case 1 -> accountController.createAccount();
                    case 2 -> accountController.findAccountById();
                    case 3 -> accountController.transferMoney();
                    case 4 -> accountController.showTransactionRecords();
                    default -> System.out.println("Invalid option!");
                }
            } catch (NullPointerException e) {
                System.out.println("‼\uFE0F Error: " + e.getMessage());
            }

        }

    }

}
