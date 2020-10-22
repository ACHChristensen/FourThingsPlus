package fourthingsplus.infrastructure;

import fourthingsplus.domain.list.NoShoppingListExist;
import fourthingsplus.domain.list.ShoppingList;
import fourthingsplus.domain.list.ShoppingListRepository;

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
            throw new NoShoppingListExist();
        }
    }

    @Override
    public ShoppingList create(String name) {
        return null;
    }
}
