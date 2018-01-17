package thisseasx.UpdateableResultSet;

import thisseasx.ResultSetPrinter.ResultSetPrinter;

import java.sql.*;
import java.util.Map;

public class UpdateableResultSet {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/epicdb?useSSL=false";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "123";

    public static void main(String[] args) {
        showTableData("products");
    }

    public static Map map;

    private static void showTableData(String table) {
        String sql = ("SELECT * FROM " + table);

        try (Connection con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {

            ResultSet rs = ps.executeQuery();
            rs.updateString(2, "TEST ROW 2");
            rs.updateInt("Pprice", 222);
            rs.updateRow();
            ResultSetPrinter.printResultSet(rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
