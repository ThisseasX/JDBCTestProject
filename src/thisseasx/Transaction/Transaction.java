package thisseasx.Transaction;

import thisseasx.ResultSetPrinter.ResultSetPrinter;

import java.sql.*;

public class Transaction {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/epicdb?useSSL=false";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "123";
    private static Connection con;

    public static void main(String[] args) {
        try {
            con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            con.setAutoCommit(false);
            insertTransaction("Tom2", 12);
            updateTransaction("Dimou", "ThissTest");
            insertTransactionFail(23, "Test");
            insertTransaction("Jerry2", 8);

            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                con.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }

        try {
            showTableData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void showTableData() throws SQLException {
        String sql = "SELECT * FROM transactions";

        try (PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            ResultSetPrinter.printResultSet(rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void updateTransaction(String where, String set) {
        String sql = "UPDATE transactions SET name = ? WHERE name = ?";

        try (PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {

            ps.setString(1, where);
            ps.setString(2, set);

            System.out.println(ps.executeUpdate());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertTransactionFail(int name, String age) throws SQLException {
        throw new SQLException();
    }

    private static void insertTransaction(String name, int age) {
        String sql = "INSERT INTO transactions values(?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {

            ps.setString(1, name);
            ps.setInt(2, age);
            System.out.println(ps.executeUpdate());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
