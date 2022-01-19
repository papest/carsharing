package carsharing;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;



public class DBService {
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String sep = File.separator;
    static final String DB_URL_DIRECTORY = "jdbc:h2:." + sep + "src" + sep + "carsharing" + sep + "db" + sep;
    private Connection conn;
    private Statement stmt;
    private String[] args;

    public DBService(String[] args) {
        this.args = args;
        getH2Connection(args);

    }

    public Connection getConn() {

        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public Statement getStmt() {
        return stmt;
    }

    public void setStmt(Statement stmt) {
        this.stmt = stmt;
    }

    public String[] getArgs() {
        return args;
    }

    public void setArgs(String[] args) {
        this.args = args;
    }

    Connection getH2Connection(String[] args) {
        try {

            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL_DIRECTORY + (args.length >= 2 ?
                    "-databaseFileName".equals(args[0].trim()) ?
                            args[1].trim() : "carsharing" : "carsharing"));
            conn.setAutoCommit(true);


            stmt = conn.createStatement();
            String sql = "CREATE TABLE COMPANY (id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "name VARCHAR(20) UNIQUE NOT NULL)";
            stmt.executeUpdate(sql);
            //conn.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();

        }
        return conn;
    }

}

