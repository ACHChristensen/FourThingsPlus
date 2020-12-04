package fourthingsplus.api;

import fourthingsplus.domain.shoppinglist.NoShoppingListExist;
import fourthingsplus.domain.shoppinglist.ShoppingList;
import fourthingsplus.domain.shoppinglist.ShoppingListFactory;
import fourthingsplus.domain.shoppinglist.ShoppingListRepository;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

public class FourThingsPlus {
    private static final Logger log = getLogger(FourThingsPlus.class);
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
        log.info("Accessing shopping list {}", i);
        return shoppingLists.find(i);
    }

    public ShoppingListFactory createShoppingList() {
        log.info("Trying to create shopping list");
        return shoppingLists.create();
    }
}
