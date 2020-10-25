package fourthingsplus.infrastructure;

import fourthingsplus.domain.shoppinglist.*;
import fourthingsplus.domain.validation.ValidationError;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBShoppingListRepository implements ShoppingListRepository {
    private final Database db;

    public DBShoppingListRepository(Database db) {
        this.db = db;
    }

    @Override
    public Iterable<ShoppingList> findAll() {
        try (Connection conn = db.connect()) {
            String sql = "SELECT * FROM shoppinglist";
            var smt = conn.prepareStatement(sql);
            smt.executeQuery();
            ResultSet set = smt.getResultSet();
            List<ShoppingList> lists = new ArrayList<ShoppingList>();
            while (set.next()) {
                lists.add(reconstituteShoppingList(set));
            }
            return lists;
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
    }

    @Override
    public ShoppingList find(ShoppingList.Id id) throws NoShoppingListExist {
        try (Connection conn = db.connect()) {
            String sql = "SELECT * FROM shoppinglist WHERE id =?";
            var smt = conn.prepareStatement(sql);
            smt.setInt(1, id.toInt());
            smt.executeQuery();
            ResultSet set = smt.getResultSet();
            if (set.next()) {
                return reconstituteShoppingList(set);
            } else {
                throw new NoShoppingListExist();
            }
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
    }

    private ShoppingList reconstituteShoppingList(ResultSet set) throws SQLException {
        return new ShoppingList(
                ShoppingList.idFromInt(set.getInt("shoppinglist.id")),
                set.getString("shoppinglist.name"),
                set.getString("shoppinglist.description")) {

            @Override
            public Iterable<ShoppingListItem> findAllItems() {
                try (Connection conn = db.connect()) {

                } catch (SQLException throwables) {
                    throw new RuntimeException(throwables);
                }
                return null;
            }

            @Override
            public ShoppingListItem.Id createItem(String description) {
                return null;
            }

            @Override
            public void findItem(ShoppingListItem.Id id) {

            }

            @Override
            public void updateItem(ShoppingListItem item) {

            }

            @Override
            public void deleteItem(ShoppingListItem.Id id) {

            }
        };
    }

    @Override
    public ShoppingListFactory create() {
        return new ShoppingListFactory() {
            @Override
            public ShoppingList.Id commit() {
                try (Connection conn = db.connect()) {
                    String sql = "INSERT INTO shoppinglist (name, description) VALUES (?, ?)";
                    var smt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                    smt.setString(1, getName());
                    smt.setString(2, getDescription());
                    smt.executeUpdate();
                    ResultSet set = smt.getGeneratedKeys();
                    if (set.next()) {
                        return ShoppingList.idFromInt(set.getInt(1));
                    } else {
                        throw new RuntimeException("Unexpected error");
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }
}
