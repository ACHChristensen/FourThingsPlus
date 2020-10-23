package fourthingsplus.infrastructure;

import fourthingsplus.domain.shoppinglist.NoShoppingListExist;
import fourthingsplus.domain.shoppinglist.ShoppingList;
import fourthingsplus.domain.shoppinglist.ShoppingListRepository;

import java.sql.Connection;
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
    public ShoppingList find(int id) throws NoShoppingListExist {
        try (Connection conn = db.connect()) {
            return null;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new NoShoppingListExist();
        }
    }

    @Override
    public ShoppingList create(String name) {
        return null;
    }
}
