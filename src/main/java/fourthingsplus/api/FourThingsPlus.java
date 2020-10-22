package fourthingsplus.api;

import fourthingsplus.domain.list.ShoppingList;
import fourthingsplus.domain.list.ShoppingListRepository;

public class FourThingsPlus {
    private static final String VERSION = "0.1";

    private final ShoppingListRepository shoppingLists;

    public FourThingsPlus(ShoppingListRepository shoppingLists) {
        this.shoppingLists = shoppingLists;
    }

    public String getVersion() {
        return VERSION;
    }

    public ShoppingList create(String name) {
        return shoppingLists.create(name);
    }

}
