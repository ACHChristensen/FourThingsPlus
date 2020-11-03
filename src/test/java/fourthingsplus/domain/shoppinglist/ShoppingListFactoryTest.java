package fourthingsplus.domain.shoppinglist;

import fourthingsplus.domain.validation.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingListFactoryTest {

    TestShoppingListFactory factory;

    @BeforeEach
    void setupFactory() {
        factory = new TestShoppingListFactory();
    }

    @Test
    void shouldCreateAFactory() throws ValidationException {
        // setup
        factory.setName("World");
        factory.setDescription("Hello");

        // Action
        ShoppingList list = factory.validateAndCommit();

        // Test
        assertTrue(factory.wasCommitted);
        assertEquals(new ShoppingList(ShoppingList.idFromInt(0), "World", "Hello"), list);
    }

    @Test
    void shouldFindProblemInNameAndDescription() {
        factory.setName("");
        factory.setDescription(null);

        // Action
        ValidationException e = assertThrows(ValidationException.class, () -> factory.validateAndCommit());

        // Test
        assertFalse(factory.wasCommitted);
        assertEquals(2, e.getProblems().size());
    }

    private class TestShoppingListFactory extends ShoppingListFactory {
        boolean wasCommitted = false;

        @Override
        protected ShoppingList commit() {
            wasCommitted = true;
            return new ShoppingList(ShoppingList.idFromInt(0), getName(), getDescription());
        }
    }
}