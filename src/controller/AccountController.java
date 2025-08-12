package controller;

import model.Account;
import model.service.AccountService;
import view.AccountView;

public class AccountController {

    private final AccountView view;
    private final AccountService service;

    public AccountController(AccountView view, AccountService service) {
        this.view = view;
        this.service = service;
    }

    public void createAccount() {
        Account newAccount = view.showAccountCreation();
        service.createAccount(newAccount);
    }

    public void findAccountById() {
        Integer id = view.showEnterId();
        view.showAccountDetail(
                service.findById(id)
        );
    }

}
