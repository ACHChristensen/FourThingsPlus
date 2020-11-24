package fourthingsplus.api;

import fourthingsplus.domain.shoppinglist.NoShoppingListExist;
import fourthingsplus.domain.shoppinglist.ShoppingList;
import fourthingsplus.domain.shoppinglist.ShoppingListFactory;
import fourthingsplus.domain.shoppinglist.ShoppingListRepository;

public class FourThingsPlus {
    private static final String VERSION = "0.1";

    private final ShoppingListRepository shoppingLists;

    public FourThingsPlus(ShoppingListRepository shoppingLists) {
        this.shoppingLists = shoppingLists;
    }

    public String getVersion() {
        return VERSION;
    }

    /**
     * Find a shopping list id
     *
     * @param i the id of the shopping list
     * @return The shopping this if it exist
     * @throws NoShoppingListExist
     */
    public ShoppingList find(ShoppingList.Id i) throws NoShoppingListExist {
        return shoppingLists.find(i);
    }

    public ShoppingListFactory createShoppingList() {
        return shoppingLists.create();
    }
}
