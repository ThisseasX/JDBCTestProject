package thisseasx.UpdateableResultSet;

import thisseasx.ResultSetPrinter.ResultSetPrinter;

import java.sql.*;

public class UpdateableResultSet {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/sales?useSSL=false";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "123";

    public static void main(String[] args) {
        String sql = "SELECT * FROM products";

        try (Connection con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement ps = con.prepareStatement(
                     sql,
                     ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_UPDATABLE)) {

            ResultSet rs = ps.executeQuery();
            rs.absolute(2);
            rs.updateInt(1, 123);
            rs.updateString(2, "TEST ROW 2");
            rs.updateInt("Pprice", 222);
            rs.updateRow();
            ResultSetPrinter.printResultSet(rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
