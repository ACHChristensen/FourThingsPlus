package fourthingsplus.infrastructure;

import fourthingsplus.domain.shoppinglist.NoShoppingListExist;
import fourthingsplus.domain.shoppinglist.ShoppingList;
import fourthingsplus.domain.shoppinglist.ShoppingListFactory;
import fourthingsplus.domain.shoppinglist.ShoppingListRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBShoppingListRepository implements ShoppingListRepository {
    private final Database db;

    public DBShoppingListRepository(Database db) {
        this.db = db;
    }

    @Override
    public Iterable<ShoppingList> findAll() {
        return null;
    }

    @Override
    public ShoppingList find(ShoppingList.Id id) throws NoShoppingListExist {
        try (Connection conn = db.connect()) {
            String sql = "SELECT * FROM shoppinglist WHERE id =?";
            var smt = conn.prepareStatement(sql);
            smt.setInt(1, id.asInt());
            smt.executeQuery();
            ResultSet set = smt.getResultSet();
            if (set.next()) {
                return parseShoppingList(set);
            } else {
                throw new NoShoppingListExist();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new NoShoppingListExist();
        }
    }

    private ShoppingList parseShoppingList(ResultSet set) throws SQLException {
        return new ShoppingList(
                ShoppingList.idFromInt(set.getInt("shoppinglist.id")),
                set.getString("shoppinglist.name"),
                set.getString("shoppinglist.description"));
    }

    @Override
    public ShoppingListFactory create() {
        return new ShoppingListFactory() {
            @Override
            protected ShoppingList commit() {
                int newid;
                try (Connection conn = db.connect()) {
                    String sql = "INSERT INTO shoppinglist (name, description) VALUES (?, ?)";
                    var smt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                    smt.setString(1, getName());
                    smt.setString(2, getDescription());
                    smt.executeUpdate();
                    ResultSet set = smt.getGeneratedKeys();
                    if (set.next()) {
                        newid = set.getInt(1);
                    } else {
                        throw new RuntimeException("Unexpected error");
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                try {
                    return find(ShoppingList.idFromInt(newid));
                } catch (NoShoppingListExist e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }
}
