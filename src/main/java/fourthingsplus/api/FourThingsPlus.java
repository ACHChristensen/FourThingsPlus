package fourthingsplus.api;

import fourthingsplus.domain.shoppinglist.NoShoppingListExist;
import fourthingsplus.domain.shoppinglist.ShoppingList;
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

    public ShoppingList find(ShoppingList.Id i) throws NoShoppingListExist {
        return shoppingLists.find(i);
    }

    public ShoppingList createShoppingList(String name, String description) {
        return shoppingLists.create(
                Utils.encodeHtml(name),
                Utils.encodeHtml(description)
        );
    }
}
