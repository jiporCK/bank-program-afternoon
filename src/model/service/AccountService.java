package model.service;

import model.Account;
import model.dto.AccountResponse;
import model.dto.TransactionRequest;
import model.dto.TransactionResponse;
import model.repository.AccountRepository;

import java.sql.SQLException;
import java.util.List;

public class AccountService {

    private final AccountRepository repository;

    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }

    public void createAccount(Account account) {
        if (account.getOwnerName().isBlank()) {
            throw new RuntimeException("Owner name is required");
        }
        if (account.getBalance() < 0) {
            throw new RuntimeException("Balance must be greater than 0");
        }
        try {
            repository.createAccount(account);
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    public AccountResponse findById(Integer id) {
        try {
            return repository.findById(id);
        } catch (SQLException e) {
            System.out.println("‼\uFE0F Error: " + e.getMessage());
        }
        return null;
    }

    public void transferMoney(TransactionRequest request) {
        if (request.amount() < 0) {
            throw new RuntimeException("Amount must be greater than 0");
        }
        try {
            repository.transferMoney(request);
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public List<TransactionResponse> getAllTransactions() {
        try {
            return repository.getAllTransactions();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

}
