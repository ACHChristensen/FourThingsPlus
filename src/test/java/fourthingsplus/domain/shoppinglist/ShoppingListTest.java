package fourthingsplus.domain.shoppinglist;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ShoppingListTest {

    @Test
    void idFromString_shouldParseValidId() throws InvalidShoppingListId {
        ShoppingList.Id id = ShoppingList.idFromString("102");
        assertEquals(ShoppingList.idFromInt(102), id);
    }


    @Test
    void idFromString_shouldThrowExceptionOnBadInput() {
        assertThrows(InvalidShoppingListId.class, () -> {
            ShoppingList.idFromString("");
        });

        assertThrows(InvalidShoppingListId.class, () -> {
            ShoppingList.idFromString("not_a_number");
        });
    }

    @Test
    void equals_sameIdShouldBeEqual() {
        ShoppingList a = new ShoppingList(ShoppingList.idFromInt(1), "heloo", "desca");
        ShoppingList b = new ShoppingList(ShoppingList.idFromInt(1), "hsdeloo", "asd23");
        assertEquals(a, b);
    }
}