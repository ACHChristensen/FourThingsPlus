package fourthingsplus.intergration;

import fourthingsplus.api.FourThingsPlus;
import fourthingsplus.domain.shoppinglist.NoShoppingListExist;
import fourthingsplus.domain.shoppinglist.ShoppingList;
import fourthingsplus.domain.shoppinglist.ShoppingListFactory;
import fourthingsplus.domain.validation.ValidationException;
import fourthingsplus.infrastructure.DBShoppingListRepository;
import fourthingsplus.infrastructure.Database;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@Tag("integration-test")
class MainTest {

    FourThingsPlus api;

    /**
     * Before you run this script create a user 'fourthingsplustest' and grant access to the database:
     *
     * <pre>
     * DROP USER IF EXISTS fourthingsplustest@localhost;
     * CREATE USER fourthingsplustest@localhost;
     * GRANT ALL PRIVILEGES ON fourthingsplustest.* TO fourthingsplustest@localhost;
     * </pre>
     */
    static void resetTestDatabase() {
        String URL = "jdbc:mysql://localhost:3306/?serverTimezone=CET";
        String USER = "fourthingsplustest";

        InputStream stream = MainTest.class.getClassLoader().getResourceAsStream("init.sql");
        if (stream == null) throw new RuntimeException("init.sql");
        try (Connection conn = DriverManager.getConnection(URL, USER, null)) {
            conn.setAutoCommit(false);
            ScriptRunner runner = new ScriptRunner(conn);
            runner.setStopOnError(true);
            runner.runScript(new BufferedReader(new InputStreamReader(stream)));
            conn.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("Done running migration");
    }

    @BeforeEach
    void setupAPI() {
        resetTestDatabase();

        String url = "jdbc:mysql://localhost:3306/fourthingsplustest?serverTimezone=CET";
        String user = "fourthingsplustest";

        Database db = new Database(url, user);
        db.runMigrations();

        DBShoppingListRepository listRepo = new DBShoppingListRepository(db);
        api = new FourThingsPlus(listRepo);
    }

    /**
     * <b>As</b> a user
     * <b>I want to</b> be able to create and save a shoppinglist
     * <b>so that</b> I can retrieve the shopping list later.
     */
    @Test
    void userStory1() throws ValidationException, NoShoppingListExist {
        ShoppingListFactory factory = api.createShoppingList();
        factory.setName("My Shoopping List");
        factory.setDescription("A short description");
        ShoppingList list = factory.validateAndCommit();

        // Times go on

        ShoppingList list2 = api.find(list.getId());

        // Test

        assertEquals(list.getId(), list2.getId());
        assertEquals(list.getName(), list2.getName());
        assertEquals(list.getDescription(), list2.getDescription());
    }
}
