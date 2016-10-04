package chapter10_jdbc;

import java.sql.*;

/**
 * @author aldvc
 * @date 01.10.2016.
 */
public class MyFirstDBConn {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:derby:zoo";
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("select name from animal")) {
            while (rs.next())
                System.out.println(rs.getString(1));
        }
    }


    void funct() throws SQLException {
        String sql = "select name from animal";
        try (Connection conn = DriverManager.getConnection("jdbc:derby:zoo");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            rs.next();
            rs.previous();
            rs.previous();
            rs.next();
            rs.next();
            rs.absolute(2);
            System.out.println(rs.getString(1));
        }
    }
}