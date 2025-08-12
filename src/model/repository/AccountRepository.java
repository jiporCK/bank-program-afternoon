package model.repository;

import model.Account;
import model.db.DbConnection;
import model.dto.AccountResponse;

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

}
