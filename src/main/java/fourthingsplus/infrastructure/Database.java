package fourthingsplus.infrastructure;

import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.*;
import java.sql.*;

public class Database {
    private final String url;
    private final String user;

    // Database VERSION
    private static final int VERSION = 1;

    public Database(String url, String user) {
        this.url = url == null ? "jdbc:mysql://localhost:3306/fourthingsplus?serverTimezone=CET" : url;
        this.user = user == null ? "fourthingsplus" : user;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new DBRuntimeException(e);
        }
    }

    public Database() {
        this(null, null);
    }

    public void runMigrations() {
        try {
            int currentVersion = getCurrentVersion();
            while (currentVersion < VERSION) {
                System.out.printf("Current DB VERSION %d is smaller than expected %d%n", currentVersion, VERSION);
                runMigration(currentVersion + 1);
                int newVersion = getCurrentVersion();
                if (newVersion > currentVersion) {
                    currentVersion = newVersion;
                    System.out.println("Updated database to version: " + newVersion);
                } else {
                    throw new DBRuntimeException("Something went wrong, version not increased: " + newVersion);
                }
            }
        } catch (SQLException | IOException e) {
            throw new DBRuntimeException(e);
        }
    }

    private void runMigration(int i) throws IOException, SQLException {
        String migrationFile = String.format("migrate/%d.sql", i);
        System.out.println("Running migration: " + migrationFile);
        InputStream stream = Database.class.getClassLoader().getResourceAsStream(migrationFile);
        if (stream == null) {
            System.out.println("Migration file, does not exist: " + migrationFile);
            throw new FileNotFoundException(migrationFile);
        }
        try (Connection conn = connect()) {
            conn.setAutoCommit(false);
            ScriptRunner runner = new ScriptRunner(conn);
            runner.setStopOnError(true);
            runner.runScript(new BufferedReader(new InputStreamReader(stream)));
            conn.commit();
        }
        System.out.println("Done running migration");
    }

    public int getCurrentVersion() {
        try (Connection conn = connect()) {
            try (Statement s = conn.createStatement()) {
                ResultSet rs = s.executeQuery("SELECT value FROM properties WHERE name = 'VERSION';");
                if (rs.next()) {
                    String column = rs.getString("value");
                    return Integer.parseInt(column);
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return -1;
    }

    public Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, null);
    }

}
