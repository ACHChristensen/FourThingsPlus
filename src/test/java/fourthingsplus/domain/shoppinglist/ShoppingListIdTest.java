package fourthingsplus.domain.shoppinglist;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShoppingListIdTest {

    @Test
    void asInt_shouldBeConstructorInt() throws InvalidShoppingListId {
        assertEquals(ShoppingList.idFromInt(102).asInt(), 102);
    }
}
