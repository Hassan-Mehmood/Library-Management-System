package Connection;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectDb {

    private static Connection conn;
    private final static String DB_URL = "jdbc:derby://localhost:1527/Library Management";
    private final static String USER_NAME = "root";
    private final static String PASSWORD = "root";

    public static Connection connectDatabase()
    {
        try {
            conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
            if (conn != null) {
                System.out.println("Connectin Completed");
            }

        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        return conn;
    }

    public static void connectionClose()
    {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Connection Closed");

            } catch (SQLException ex) {
                Logger.getLogger(ConnectDb.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
