package thisseasx.SimpleSelect;

import thisseasx.ResultSetPrinter.ResultSetPrinter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SimpleSelect {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/sales?useSSL=false";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "123";

    public static void main(String[] args) {
        String sql = "SELECT * FROM sales";

        try (Connection con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement ps = con.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();
//            failPrint(rs); // The bad way to print a ResultSet.
            ResultSetPrinter.printResultSet(rs); // The good way to print a ResultSet.

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void failPrint(ResultSet rs) throws SQLException {
        while (rs.next()) {
            int columnCount = rs.getMetaData().getColumnCount();

            List<String> list = new ArrayList<>();
            for (int i = 1; i <= columnCount; i++) list.add(rs.getString(i));

            list.forEach(x -> System.out.print(x + " "));
            System.out.println();
        }
    }
}
