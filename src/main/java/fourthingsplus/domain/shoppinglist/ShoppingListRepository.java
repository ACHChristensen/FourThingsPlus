package fourthingsplus.domain.shoppinglist;

public interface ShoppingListRepository {
    Iterable<ShoppingList> findAll();

    ShoppingList find(ShoppingList.Id id) throws NoShoppingListExist;

    ShoppingList create(String name, String description);
}
