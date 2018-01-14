package thisseasx;

import java.sql.*;

public class Main {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/epicdb?useSSL=false";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "123";

    public static void main(String[] args) {
        showTableData("superview");
    }

    private static void showTableData(String table) {
        String sql = ("SELECT * FROM " + table);

        try (Connection con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement ps = con.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();
            /*while (rs.next()) {
                System.out.printf("%s %s %s %s %s %s %s%n",
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7)
                );
            }*/
            ResultSetPrinter.printResultSet(rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
