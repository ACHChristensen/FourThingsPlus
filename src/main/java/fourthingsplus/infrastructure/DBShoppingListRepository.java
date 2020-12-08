package fourthingsplus.infrastructure;

import fourthingsplus.domain.shoppinglist.NoShoppingListExist;
import fourthingsplus.domain.shoppinglist.ShoppingList;
import fourthingsplus.domain.shoppinglist.ShoppingListFactory;
import fourthingsplus.domain.shoppinglist.ShoppingListRepository;

import java.sql.*;

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
            try {
                conn.setAutoCommit(false);

                String insert = "INSERT INTO shoppinglist (name) VALUES ('hello')";
                try (var smt = conn.prepareStatement(insert)) {
                    smt.executeUpdate();
                }

                String sql = "SELECT * FROM shoppinglist WHERE id =?";
                try (var smt = conn.prepareStatement(sql)) {
                    smt.setInt(1, id.asInt());
                    smt.executeQuery();
                    ResultSet set = smt.getResultSet();
                    if (set.next()) {
                        var val = parseShoppingList(set);
                        conn.commit();
                        return val;
                    } else {
                        conn.rollback();
                        throw new NoShoppingListExist();
                    }
                }
            } catch (SQLException e) {
                conn.rollback();
                throw new DBRuntimeException(e);
            } finally {
                conn.setAutoCommit(true);
            }
        } catch (SQLException throwables) {
            throw new DBRuntimeException(throwables);
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
                    try (var smt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                        smt.setString(1, getName());
                        smt.setString(2, getDescription());
                        smt.executeUpdate();
                        ResultSet set = smt.getGeneratedKeys();
                        if (set.next()) {
                            newid = set.getInt(1);
                        } else {
                            throw new DBRuntimeException("Unexpected error");
                        }
                    }
                } catch (SQLException e) {
                    throw new DBRuntimeException(e);
                }
                try {
                    return find(ShoppingList.idFromInt(newid));
                } catch (NoShoppingListExist e) {
                    throw new DBRuntimeException(e);
                }
            }
        };
    }
}
