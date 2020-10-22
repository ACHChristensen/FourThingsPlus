package fourthingsplus.infrastructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private final String URL = "jdbc:mysql://localhost:3306/fourthingsplus?localTimezone=GMT+2";
    private final String USER = "fourthingsplus";

    public Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, null);
    }

}
