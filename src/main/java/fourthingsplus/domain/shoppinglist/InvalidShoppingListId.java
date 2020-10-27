package fourthingsplus.domain.shoppinglist;

public class InvalidShoppingListId extends Exception {
    public InvalidShoppingListId(String string) {
        super("Could not parse: " + string);
    }
}
