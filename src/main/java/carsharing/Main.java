package carsharing;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String sep = File.separator;
    static final String DB_URL_DIRECTORY = "jdbc:h2:." + sep + "src" + sep + "carsharing" + sep + "db" + sep;


    static final String USER = "sa";
    static final String PASS = "";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try {

            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL_DIRECTORY + (args.length >= 2 ?
                    "-databaseFileName".equals(args[0].trim()) ?
                            args[1].trim() : "carsharing" : "carsharing"));
            conn.setAutoCommit(true);


            stmt = conn.createStatement();
            String sql = "CREATE TABLE COMPANY (id INT, " +
                    "NAME VARCHAR(20))";
            stmt.executeUpdate(sql);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();

        }
    }
}