package fourthingsplus.domain.shoppinglist;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ShoppingListIdTest {

    @Test
    void asInt_shouldBeConstructorInt() {
        assertEquals(102, ShoppingList.idFromInt(102).asInt());
    }
}
