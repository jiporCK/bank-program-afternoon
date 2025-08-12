package model.repository;

import model.Account;
import model.db.DbConnection;
import model.dto.AccountResponse;
import model.dto.TransactionRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountRepository {

//    private final Connection connection;

//    public AccountRepository(Connection connection) {
//        this.connection = connection;
//    }

    public void createAccount(Account account) throws SQLException {
        Connection connection = DbConnection.getInstance();
        String sql = """
                insert into accounts (owner_name, balance)
                values (?, ?)
                """;
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, account.getOwnerName());
        ps.setDouble(2, account.getBalance());

        int rowsAffected = ps.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Inserted successfully");
        }
        connection.close();
        ps.close();
    }

    public AccountResponse findById(Integer id) throws SQLException {
        if (!existsById(id)) {
            throw new SQLException("ID does not exist");
        }

        try (Connection conn = DbConnection.getInstance()) {
            String sql = """
                    select * from accounts
                    where account_id = ?
                    """;

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, id);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return new AccountResponse(
                                rs.getInt("account_id"),
                                rs.getString("owner_name"),
                                rs.getDouble("balance")
                        );
                    }
                }
            }
        }
        return null;
    }

    public boolean existsById(Integer id) throws SQLException {
        try (Connection conn = DbConnection.getInstance()) {
            String sql = "select 1 from accounts where account_id = ?";

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, id);

                try (ResultSet rs = ps.executeQuery()) {
                    return rs.next();
                }
            }
        }
    }

    public void transferMoney(TransactionRequest request) throws SQLException {
        try (Connection conn = DbConnection.getInstance()) {
            conn.setAutoCommit(false); // Start transaction process

            try {
                // Deduct money from sender account
                String sql = """
                        update accounts
                        set balance = balance - ?
                        where account_id = ?
                        """;
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setDouble(1, request.amount());
                    ps.setInt(2, request.senderId());
                    int rowsAffected = ps.executeUpdate();
                    if (rowsAffected == 0) {
                        throw new SQLException("Error sending money");
                    }
                }

                // Deduct money from sender account
                String sql2 = """
                        update accounts
                        set balance = balance + ?
                        where account_id = ?
                        """;
                try (PreparedStatement ps = conn.prepareStatement(sql2)) {
                    ps.setDouble(1, request.amount());
                    ps.setInt(2, request.receiverId());
                    int rowsAffected = ps.executeUpdate();
                    if (rowsAffected == 0) {
                        throw new SQLException("Error receiving money");
                    }
                }

                // Save transaction record
                String sql3 = """
                        insert into transactions (from_account, to_account, amount)
                        values (?, ?, ?)
                        """;
                try (PreparedStatement ps = conn.prepareStatement(sql3)) {
                    ps.setInt(1, request.senderId());
                    ps.setInt(2, request.receiverId());
                    ps.setDouble(3, request.amount());
                    int rowsAffected = ps.executeUpdate();
                    if (rowsAffected == 0) {
                        throw new SQLException("Error saving transaction record");
                    }
                }
                conn.commit();

            } catch (SQLException e) {
                conn.rollback();
                System.out.println(e.getMessage());
            } finally {
                conn.setAutoCommit(true);
            }

        }
    }


}
