package business.dao.account;
import business.model.Account;
import business.model.Account.AccountStatus;
import business.config.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAOImp implements AccountDAO {

    @Override
    public Account getByUsername(String username) {
        String query = "SELECT * FROM accounts WHERE username = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = ConnectionDB.openConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();
            if (rs.next()) {
                Account account = new Account();
                account.setUsername(rs.getString("username"));
                account.setPassword(rs.getString("password"));
                account.setStatus(AccountStatus.valueOf(rs.getString("status")));
                return account;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, ps, rs);
        }
        return null;
    }

    @Override
    public List<Account> getAll() {
        List<Account> list = new ArrayList<>();
        String query = "SELECT * FROM accounts";
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            conn = ConnectionDB.openConnection();
            st = conn.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                Account account = new Account();
                account.setUsername(rs.getString("username"));
                account.setPassword(rs.getString("password"));
                account.setStatus(AccountStatus.valueOf(rs.getString("status")));
                list.add(account);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, st, rs);
        }
        return list;
    }

    @Override
    public boolean insert(Account account) {
        String query = "INSERT INTO accounts(username, password, status) VALUES(?, ?, ?)";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = ConnectionDB.openConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, account.getUsername());
            ps.setString(2, account.getPassword());
            ps.setString(3, account.getStatus().name());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, ps, null);
        }
        return false;
    }

    @Override
    public boolean update(Account account) {
        String query = "UPDATE accounts SET password = ?, status = ? WHERE username = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = ConnectionDB.openConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, account.getPassword());
            ps.setString(2, account.getStatus().name());
            ps.setString(3, account.getUsername());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, ps, null);
        }
        return false;
    }

    @Override
    public boolean delete(String username) {
        String query = "DELETE FROM accounts WHERE username = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = ConnectionDB.openConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, username);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, ps, null);
        }
        return false;
    }
}
